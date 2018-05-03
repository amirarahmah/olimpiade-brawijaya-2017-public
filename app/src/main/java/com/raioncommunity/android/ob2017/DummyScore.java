package com.raioncommunity.android.ob2017;

/**
 * Created by amira on 7/7/2017.
 */

    public class DummyScore {

        public String jamMain;
        public String tanggalMain;
        public int caborIcon;
        public String cabang;
        public boolean live;
        public String caborDet;
        public boolean isBagan;
        public String team1Nama;
        public int team1Resource;
        public String team1Score;
        public String team2Nama;
        public int team2Resource;
        public String team2Score;
        public String juara1Name;
        public int juara1Resource;
        public String juara2Name;
        public int juara2Resource;
        public String juara3Name;
        public int juara3Resource;
        public String tempat;

        public DummyScore(int caborIcon, String cabang, String caborDet,
                          boolean isBagan, String team1Nama, int team1Resource, String team1Score,
                          String team2Nama, int team2Resource, String team2Score, String tempat) {

            this.caborIcon = caborIcon;
            this.cabang = cabang;
            this.caborDet = caborDet;
            this.isBagan = isBagan;
            this.team1Nama = team1Nama;
            this.team1Resource = team1Resource;
            this.team1Score = team1Score;
            this.team2Nama = team2Nama;
            this.team2Resource = team2Resource;
            this.team2Score = team2Score;
            this.tempat = tempat;
        }

    public DummyScore(String cabang, String tempat, String jamMain) {
        this.jamMain = jamMain;
        this.cabang = cabang;
        this.tempat = tempat;
    }

    public DummyScore(String jamMain, String tanggalMain, int caborIcon,
                      String cabang, String caborDet, boolean isBagan, String tempat) {
        this.jamMain = jamMain;
        this.tanggalMain = tanggalMain;
        this.caborIcon = caborIcon;
        this.cabang = cabang;
        this.caborDet = caborDet;
        this.isBagan = isBagan;
        this.tempat = tempat;
    }

    public int getCaborIcon() {
            return caborIcon;
        }

    public String getTanggalMain() {
        return tanggalMain;
    }

    public String getCabang() {
            return cabang;
        }

        public String getCaborDet() {
            return caborDet;
        }

        public boolean isBagan() {
            return isBagan;
        }

        public String getTeam1Nama() {
            return team1Nama;
        }

        public int getTeam1Resource() {
            return team1Resource;
        }

        public String getTeam1Score() {
            return team1Score;
        }

        public String getJamMain() {
            return jamMain;
        }

        public boolean isLive() {
            return live;
        }

        public String getJuara1Name() {
            return juara1Name;
        }

        public int getJuara1Resource() {
            return juara1Resource;
        }

        public String getJuara2Name() {
            return juara2Name;
        }

        public int getJuara2Resource() {
            return juara2Resource;
        }

        public String getJuara3Name() {
            return juara3Name;
        }

        public int getJuara3Resource() {
            return juara3Resource;
        }

        public String getTeam2Nama() {
            return team2Nama;
        }

        public int getTeam2Resource() {
            return team2Resource;
        }

        public String getTeam2Score() {
            return team2Score;
        }

        public String getTempat() {
            return tempat;
        }
    }

