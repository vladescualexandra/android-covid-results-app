package com.example.tema2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tema2.asyncTask.AsyncTaskRunner;
import com.example.tema2.asyncTask.Callback;
import com.example.tema2.model.Centru;
import com.example.tema2.model.Pacient;
import com.example.tema2.model.Rezultate;
import com.example.tema2.model.RezultateJsonParser;
import com.example.tema2.network.HttpManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_CENTRU_ADAUGAT = "CENTRU_ADAUGAT";
    List<Centru> centre;

    Button centreBtn;
    Button adaugaCentruBtn;

    public static final String URL_REZULTATE = "https://jsonkeeper.com/b/NHL7";
    private final AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();

    Intent intent;

    public static int REQUEST_CODE_CENTRU = 201;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        getResultsFromHttp();

    }

    private void initComponents() {
        centreBtn = findViewById(R.id.vladescu_alexandra_bianca_main_centre);
        adaugaCentruBtn = findViewById(R.id.vladescu_alexandra_bianca_main_adauga_centru);

        centreBtn.setOnClickListener(afiseazaCentre());
        adaugaCentruBtn.setOnClickListener(adaugaCentru());

        intent = getIntent();
        Centru centru = (Centru) intent.getSerializableExtra(KEY_CENTRU_ADAUGAT);
        if (centru != null) {
            centre.add(centru);
        }

    }

    private View.OnClickListener adaugaCentru() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CENTRU);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CENTRU
                && resultCode == RESULT_OK
                && data != null) {

            Centru centru = (Centru) data.getSerializableExtra(KEY_CENTRU_ADAUGAT);
            centre.add(centru);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener afiseazaCentre() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra(ListActivity.KEY_CENTRE, (Serializable) centre);
                startActivity(intent);
            }
        };
    }

    private void getResultsFromHttp() {
        Callable<String> asyncOperation = new HttpManager(URL_REZULTATE);
        Callback<String> mainThreadOperation = receiveResultsFromHttp();

        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveResultsFromHttp() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                centre = new ArrayList<>();
                centre = RezultateJsonParser.centreFromJSON(result);
            }
        };
    }
}