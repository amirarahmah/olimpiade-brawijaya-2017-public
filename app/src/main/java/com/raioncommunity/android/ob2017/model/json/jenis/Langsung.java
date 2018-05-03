package com.raioncommunity.android.ob2017.model.json.jenis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bradhawk on 10/1/2016.
 */

public class Langsung {

    @SerializedName("satu")
    @Expose
    private String satu;

    @SerializedName("dua")
    @Expose
    private String dua;

    @SerializedName("tiga")
    @Expose
    private String tiga;

    public String getSatu() {
        return satu;
    }

    public void setSatu(String satu) {
        this.satu = satu;
    }

    public String getDua() {
        return dua;
    }

    public void setDua(String dua) {
        this.dua = dua;
    }

    public String getTiga() {
        return tiga;
    }

    public void setTiga(String tiga) {
        this.tiga = tiga;
    }
}
