package com.raioncommunity.android.ob2017.model.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ranu on 13/10/2016.
 */

public class PetunjukPenonton {
    private int id;

    @SerializedName("venueNameRes")
    @Expose
    @StringRes
    private int venueNameRes;

    private String venueName;

    private int[] listCaborId;
    private List<CabangOlahraga> listCabor;
    private double lat;
    private double lon;


    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    @SerializedName("gambar")
    @Expose
    @DrawableRes
    private int gambar;

    public int[] getListCaborId() {
        return listCaborId;
    }

    public void setListCaborId(int[] listCaborId) {
        this.listCaborId = listCaborId;
    }

    public double getLat() {
        return lat;
    }

    public PetunjukPenonton(int id, @StringRes int venueNameRes, @DrawableRes int gambar, int[] listCaborId, double lat, double lon) {
        this.id = id;
        this.venueNameRes = venueNameRes;
        this.gambar = gambar;
        this.listCaborId = listCaborId;
        this.lat = lat;
        this.lon = lon;
    }

    public PetunjukPenonton(int id, @StringRes int venueNameRes, @DrawableRes int gambar, ArrayList<CabangOlahraga> cabangOlahragaArrayList, double lat, double lon) {
        this.id = id;
        this.venueNameRes = venueNameRes;
        this.gambar = gambar;
        this.listCaborId = listCaborId;
        this.lat = lat;
        this.lon = lon;
        this.listCabor = cabangOlahragaArrayList;
    }

    public void setLat(double lat) {

        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenueNameRes() {
        return venueNameRes;
    }

    public void setVenueNameRes(int venueNameRes) {
        this.venueNameRes = venueNameRes;
    }

    public List<CabangOlahraga> getListCabor() {
        return listCabor;
    }

    public void setListCabor(List<CabangOlahraga> listCabor) {
        this.listCabor = listCabor;
    }

    public void onClick(View view, String namaTempat) {
        Context context = view.getContext();
        Uri intentUri = Uri.parse("geo:<" + lat + ">,<" + lon + ">?q=<" + lat + ">,<" + lon + ">(" + namaTempat + ")");
        //Log.v("urinya",intentUri.toString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }

    }
}
