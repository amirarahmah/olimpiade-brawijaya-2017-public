package com.raioncommunity.android.ob2017.model.json.jenis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bradhawk on 10/1/2016.
 */

public class Score {

    @SerializedName("score1")
    @Expose
    private String score1;

    @SerializedName("score2")
    @Expose
    private String score2;

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }
}
