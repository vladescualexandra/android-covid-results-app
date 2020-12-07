package com.example.tema2.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Rezultate {

    public static final String REZULTATE = "rezultate";
    public static final String AN = "an";
    public static final String TOTAL_PACIENTI = "totalPacienti";
    public static final String TOTAL_CENTRE = "totalCentre";
    public static final String CENTRE = "centre";

    private int an;
    private int nrPacienti;
    private int nrCentre;
    private List<Centru> centre;

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public Rezultate(int an,
                     int nrPacienti,
                     int nrCentre,
                     List<Centru> centre) {
        this.an = an;
        this.nrPacienti = nrPacienti;
        this.nrCentre = nrCentre;
        this.centre = centre;
    }

    public int getNrPacienti() {
        return nrPacienti;
    }

    public void setNrPacienti(int nrPacienti) {
        this.nrPacienti = nrPacienti;
    }

    public int getNrCentre() {
        return nrCentre;
    }

    public void setNrCentre(int nrCentre) {
        this.nrCentre = nrCentre;
    }

    public List<Centru> getCentre() {
        return centre;
    }

    public void setCentre(List<Centru> centre) {
        this.centre = centre;
    }

    @NonNull
    @Override
    public String toString() {
        String text = "";
        text += "An:" + an + "\n";
        text += "Nr pacienti: " + nrPacienti + "\n";
        text += "Nr centre: " + nrCentre + "\n";
        for (int i = 0; i < centre.size(); i++) {
            text += "Centru " + i + ": " + centre.get(i).getOras()
                    + " " + centre.get(i).getJudet()
                    + " " + centre.get(i).getOras()
                    + " " + centre.get(i).getAdresa();

            for (int j = 0; j < centre.get(j).getPacienti().size(); i++) {
                text += "\tPacient " + j + ": "
                        + centre.get(j).getPacienti().get(i).getNume()
                        + " " + centre.get(j).getPacienti().get(i).getRezultat()
                        + " " + centre.get(j).getPacienti().get(i).getTelefon()
                        + " " + centre.get(j).getPacienti().get(i).getCnp()
                        + "\n";
            }
        }
        return text;
    }
}
