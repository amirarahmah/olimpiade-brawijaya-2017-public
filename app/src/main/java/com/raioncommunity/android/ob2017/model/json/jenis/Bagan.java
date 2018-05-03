package com.raioncommunity.android.ob2017.model.json.jenis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bradhawk on 10/1/2016.
 */

public class Bagan {
    @SerializedName("score")
    @Expose
    private List<Score> score;

    @SerializedName("team1")
    @Expose
    private String team1;

    @SerializedName("team2")
    @Expose
    private String team2;

    public List<Score> getScore() {
        return score;
    }

    public void setScore(List<Score> score) {
        this.score = score;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }
}
