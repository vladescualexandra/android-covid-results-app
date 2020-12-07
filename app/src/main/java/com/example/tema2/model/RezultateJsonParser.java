package com.example.tema2.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RezultateJsonParser {

    public static Rezultate fromJSON(String json) {
        try {

            JSONObject covid = new JSONObject(json);
            JSONObject rezultateJSON = covid.getJSONObject(Rezultate.REZULTATE);

            int an = rezultateJSON.getInt(Rezultate.AN);
            int nrPacienti = rezultateJSON.getInt(Rezultate.TOTAL_PACIENTI);
            int nrCentre = rezultateJSON.getInt(Rezultate.TOTAL_CENTRE);

            List<Centru> centre = returneazaCentre(rezultateJSON.getJSONArray(Rezultate.CENTRE));


            return new Rezultate(an, nrPacienti, nrCentre, centre);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<Centru> returneazaCentre(JSONArray jsonArray) throws JSONException {
        List<Centru> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject centru = jsonArray.getJSONObject(i);

            String judet = centru.getString(Centru.JUDET);
            String oras = centru.getString(Centru.ORAS);
            String adresa = centru.getString(Centru.ADRESA);

            List<Pacient> pacienti = returneazaPacienti(centru.getJSONArray(Centru.PACIENTI));

            list.add(new Centru(judet, oras, adresa, pacienti));
        }
        return list;
    }

    private static ArrayList<Pacient> returneazaPacienti(JSONArray jsonArray) throws JSONException {
        List<Pacient> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject pacient = jsonArray.getJSONObject(i);

            String nume = pacient.getString(Pacient.NUME);
            boolean rezultat = pacient.getString(Pacient.REZULTAT).equals("pozitiv");
            String telefon = pacient.getString(Pacient.TELEFON);
            String cnp = pacient.getString(Pacient.CNP);

            list.add(new Pacient(nume, rezultat, telefon, cnp));

        }

        return new ArrayList<>(list);
    }


    public static List<Centru> centreFromJSON(String json) {
        try {
            JSONObject covid = new JSONObject(json);
            JSONObject rezultateJSON = covid.getJSONObject(Rezultate.REZULTATE);
            JSONArray centre = rezultateJSON.getJSONArray(Rezultate.CENTRE);

            return returneazaCentre(centre);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
