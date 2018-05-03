package com.raioncommunity.android.ob2017.model.api.service;

import com.raioncommunity.android.ob2017.model.json.News;
import com.raioncommunity.android.ob2017.model.json.NewsBriefResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bradhawk on 9/30/2016.
 */

public interface BeritaService {

    @GET("publik/news.php")
    Call<NewsBriefResponse> getNewsBrief(@Query("page") int page);

    @GET("publik/news.php")
    Call<News> getNews(@Query("idn") int newsId);
}
