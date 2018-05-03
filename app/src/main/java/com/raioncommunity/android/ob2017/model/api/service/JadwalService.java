package com.raioncommunity.android.ob2017.model.api.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bradhawk on 10/1/2016.
 */

public interface JadwalService {

    public static final String STATUS_DONE = "selesai";
    public static final String STATUS_UPCOMING = "belum";

    @GET("publik/jadwal.php?=hariini")
    Call<ResponseBody> getJadwalHariIni();

    @GET("publik/jadwal.php?tanding=kemarin")
    Call<ResponseBody> getJadwalKemarin();

    @GET("publik/jadwal.php?tanding=besok")
    Call<ResponseBody> getJadwalBesok();

    @GET("publik/jadwal.php")
    Call<ResponseBody> getJadwalByIdFakultas(@Query("fid") int fakultasId);

    @GET("publik/jadwal.php")
    Call<ResponseBody> getJadwalByIdCaborAndStatus(@Query("cid") int caborId, @Query("status") String status);

    @GET("publik/jadwal.php")
    Call<ResponseBody> getJadwalByDate(@Query("waktu") String waktu);
}
