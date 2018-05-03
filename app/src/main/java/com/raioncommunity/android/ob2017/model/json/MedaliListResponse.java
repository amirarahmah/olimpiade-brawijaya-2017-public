package com.raioncommunity.android.ob2017.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bradhawk on 9/30/2016.
 */

public class MedaliListResponse {

    @SerializedName("sukses")
    @Expose
    private boolean sukses;

    @SerializedName("medali")
    @Expose
    private List<Medali> medali;

    public boolean isSukses() {
        return sukses;
    }

    public void setSukses(boolean sukses) {
        this.sukses = sukses;
    }

    public List<Medali> getMedali() {
        return medali;
    }
}
