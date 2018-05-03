package com.raioncommunity.android.ob2017.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bradhawk on 10/1/2016.
 */

public class JadwalListResponse {
    @SerializedName("sukses")
    @Expose
    private boolean sukses;

    @SerializedName("list")
    @Expose
    private List<Jadwal> list;

    public boolean isSukses() {
        return sukses;
    }

    public void setSukses(boolean sukses) {
        this.sukses = sukses;
    }

    public List<Jadwal> getList() {
        return list;
    }

    public void setList(List<Jadwal> list) {
        this.list = list;
    }
}
