package com.raioncommunity.android.ob2017.model.api.service;


import com.raioncommunity.android.ob2017.model.json.MedaliListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bradhawk on 9/30/2016.
 */

public interface MedaliService {

    @GET("publik/medali.php")
    Call<MedaliListResponse> getMedaliList();

}
