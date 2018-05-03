package com.raioncommunity.android.ob2017.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bradhawk on 9/30/2016.
 */

public class Medali {

    @SerializedName("fakultas")
    @Expose
    private String fakultas;

    @SerializedName("emas")
    @Expose
    private String emas;

    @SerializedName("perak")
    @Expose
    private String perak;

    @SerializedName("perunggu")
    @Expose
    private String perunggu;

    public String getFakultas() {
        return fakultas.toUpperCase();
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getEmas() {
        return emas;
    }

    public void setEmas(String emas) {
        this.emas = emas;
    }

    public String getPerak() {
        return perak;
    }

    public void setPerak(String perak) {
        this.perak = perak;
    }

    public String getPerunggu() {
        return perunggu;
    }

    public void setPerunggu(String perunggu) {
        this.perunggu = perunggu;
    }

    public String getTotalMedali() {
        int total = Integer.parseInt(emas) + Integer.parseInt(perak) + Integer.parseInt(perunggu);
        return String.valueOf(total);
    }
}
