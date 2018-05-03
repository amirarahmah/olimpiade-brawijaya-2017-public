package com.raioncommunity.android.ob2017.util;

import android.util.Log;

import com.raioncommunity.android.ob2017.model.json.Jadwal;
import com.raioncommunity.android.ob2017.model.json.JadwalListResponse;
import com.raioncommunity.android.ob2017.model.json.jenis.Bagan;
import com.raioncommunity.android.ob2017.model.json.jenis.Langsung;
import com.raioncommunity.android.ob2017.model.json.jenis.Score;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradhawk on 10/1/2016.
 */

public class JadwalConverter {

    private static final String TAG = "JadwalConverter";

    public static JadwalListResponse fromJson(String json) {
        JadwalListResponse response = new JadwalListResponse();
        try {
            JSONObject object = new JSONObject(json);
            Log.d(TAG, "fromJson: " + object.toString());
            boolean sukses = object.getBoolean("sukses");
            if (sukses) {

                List<Jadwal> jadwalList = new ArrayList<>();
                JSONArray array = object.getJSONArray("list");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject jadwalJson = array.getJSONObject(i);

                    int id = jadwalJson.getInt("id");
                    String status = jadwalJson.getString("status");
                    int nextid = jadwalJson.getInt("nextid");
                    String tempat = jadwalJson.getString("tempat");
                    String alamat = jadwalJson.getString("alamat");
                    String starttime = jadwalJson.getString("starttime");
                    String endtime = jadwalJson.getString("endtime");
                    String cabang = jadwalJson.getString("cabang");
                    String cabordet = jadwalJson.getString("cabordet");
                    String lomba = jadwalJson.getString("lomba");

                    Jadwal jadwalObj = new Jadwal();
                    jadwalObj.setId(id);
                    jadwalObj.setStatus(status);
                    jadwalObj.setNextid(nextid);
                    jadwalObj.setTempat(tempat);
                    jadwalObj.setAlamat(alamat);
                    jadwalObj.setStarttime(starttime);
                    jadwalObj.setEndtime(endtime);
                    jadwalObj.setCabang(cabang);
                    jadwalObj.setCabordet(cabordet);
                    jadwalObj.setLomba(lomba);

                    if (lomba.equalsIgnoreCase("Bagan")) {
                        JSONObject bagan = jadwalJson.getJSONObject("data");
                        JSONArray scores = bagan.getJSONArray("score");
                        List<Score> scoreList = new ArrayList<>();
                        for (int j = 0; j < scores.length(); j++) {
                            JSONObject scoreJson = scores.getJSONObject(j);
                            Score score = new Score();
                            score.setScore1(scoreJson.getString("score1"));
                            score.setScore2(scoreJson.getString("score2"));
                            scoreList.add(score);
                        }
                        Bagan baganObj = new Bagan();
                        baganObj.setScore(scoreList);
                        baganObj.setTeam1(bagan.getString("team1"));
                        baganObj.setTeam2(bagan.getString("team2"));

                        jadwalObj.setData(baganObj);
                    } else {
                        JSONObject sekaliMenang = jadwalJson.getJSONObject("data");
                        Langsung langsung = new Langsung();
                        langsung.setSatu(sekaliMenang.getString("satu"));
                        langsung.setDua(sekaliMenang.getString("dua"));
                        langsung.setTiga(sekaliMenang.getString("tiga"));

                        jadwalObj.setData(langsung);
                    }

                    jadwalList.add(jadwalObj);
                }
                response.setList(jadwalList);
            }
            response.setSukses(sukses);
        } catch (JSONException e) {
            Log.d(TAG, "fromJson: " + e.getLocalizedMessage());
            return null;
        }
        return response;
    }

}
