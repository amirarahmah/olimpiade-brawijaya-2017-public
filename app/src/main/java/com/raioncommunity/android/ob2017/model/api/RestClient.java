package com.raioncommunity.android.ob2017.model.api;


import com.raioncommunity.android.ob2017.model.api.service.BeritaService;
import com.raioncommunity.android.ob2017.model.api.service.JadwalService;
import com.raioncommunity.android.ob2017.model.api.service.MedaliService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bradhawk on 9/30/2016.
 */


public class RestClient {

    private static Retrofit retrofit;

    public static BeritaService beritaService;
    public static MedaliService medaliService;
    public static JadwalService jadwalService;


    public static void initialize() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://olimpiade.ub.ac.id/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        beritaService = retrofit.create(BeritaService.class);
        medaliService = retrofit.create(MedaliService.class);
        jadwalService = retrofit.create(JadwalService.class);
    }

}
