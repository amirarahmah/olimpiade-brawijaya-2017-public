package com.raioncommunity.android.ob2017.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bradhawk on 9/29/2016.
 */

public class NewsBriefResponse {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("totalpage")
    @Expose
    private int totalpage;

    @SerializedName("sukses")
    @Expose
    private boolean sukses;

    @SerializedName("list")
    @Expose
    private List<News> list;

    public boolean isSukses() {
        return sukses;
    }

    public void setSukses(boolean sukses) {
        this.sukses = sukses;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<News> getList() {
        return list;
    }
}
