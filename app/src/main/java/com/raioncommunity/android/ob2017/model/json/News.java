package com.raioncommunity.android.ob2017.model.json;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raioncommunity.android.ob2017.ui.berita_detail.BeritaDetailActivity;
import com.raioncommunity.android.ob2017.util.Constant;

/**
 * Created by bradhawk on 9/29/2016.
 */

public class News {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("judul")
    @Expose
    private String judul;

    @SerializedName("isi")
    @Expose
    private String isi;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    @SerializedName("youtube")
    @Expose
    private String youtube;

    @SerializedName("dibuat")
    @Expose
    private String dibuat;

    @SerializedName("count")
    @Expose
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getDibuat() {
        return dibuat;
    }

    public void setDibuat(String dibuat) {
        this.dibuat = dibuat;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void onShare(View view) {
        Context context = view.getContext();
    }

    public void onReadMore(View view) {
        Context context = view.getContext();
        Intent readMore = new Intent(context, BeritaDetailActivity.class);
        readMore.putExtra(Constant.NEWS_ID_REQUEST_ARGS, this.id);
        context.startActivity(readMore);
    }
}
