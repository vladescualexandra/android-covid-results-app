package com.example.tema2;

import android.content.Intent;
import android.os.Bundle;

import com.example.tema2.model.Centru;
import com.example.tema2.model.CentruAdapter;
import com.example.tema2.model.Pacient;
import com.example.tema2.model.PacientAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    List<Centru> centre = new ArrayList<>();
    List<Pacient> pacienti = new ArrayList<>();

    Intent intent;
    ListView list;

    public static String KEY_CENTRE = "CENTRE";
    public static String KEY_PACIENTI = "PACIENTI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initComponents();

        if ((centre = (ArrayList<Centru>) intent.getSerializableExtra(KEY_CENTRE)) != null) {
            afiseazaCentre();
        } else {
            if ((pacienti = (ArrayList<Pacient>) intent.getSerializableExtra(KEY_PACIENTI)) != null) {
                afiseazaPacienti();
            }
        }

    }

    private void initComponents() {
        intent = getIntent();
        list = findViewById(R.id.vladescu_alexandra_bianca_list);
    }

    private void afiseazaCentre() {
        CentruAdapter adapter = new CentruAdapter(getApplicationContext(),
                R.layout.row_item_centru, centre, getLayoutInflater());
        list.setAdapter(adapter);

        list.setOnItemClickListener(deschideActivitatePacienti());
    }

    private void afiseazaPacienti() {
        PacientAdapter adapter = new PacientAdapter(getApplicationContext(),
                R.layout.row_item_pacient, pacienti, getLayoutInflater());
        list.setAdapter(adapter);
    }

    private AdapterView.OnItemClickListener deschideActivitatePacienti() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra(KEY_PACIENTI, (Serializable) centre.get(position).getPacienti());

                startActivity(intent);
            }
        };

    }
}