package com.raioncommunity.android.ob2017.model.json;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.json.jenis.Bagan;
import com.raioncommunity.android.ob2017.model.json.jenis.Langsung;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.model.view.Fakultas;
import com.raioncommunity.android.ob2017.util.DataList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bradhawk on 10/1/2016.
 */

public class Jadwal {

    SimpleDateFormat df;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("nextid")
    @Expose
    private int nextid;

    @SerializedName("tempat")
    @Expose
    private String tempat;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("starttime")
    @Expose
    private String starttime;

    @SerializedName("endtime")
    @Expose
    private String endtime;

    @SerializedName("cabang")
    @Expose
    private String cabang;

    @SerializedName("cabordet")
    @Expose
    private String cabordet;

    @SerializedName("lomba")
    @Expose
    private String lomba;

    @SerializedName("data")
    @Expose
    private Object data;

    public Jadwal() {
        this.df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNextid() {
        return nextid;
    }

    public void setNextid(int nextid) {
        this.nextid = nextid;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getCabordet() {
        return cabordet;
    }

    public void setCabordet(String cabordet) {
        this.cabordet = cabordet;
    }

    public String getLomba() {
        return lomba;
    }

    public void setLomba(String lomba) {
        this.lomba = lomba;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCaborIcon() {
        CabangOlahraga cabor = DataList.getCabangOlahragaObject(getCabang());
        if (cabor != null) return cabor.getCaborIconRes();
        return R.drawable.ic_tobedetermined_icon;
    }

    // DATA RELATED

    public boolean getIsBagan() {
        return lomba.equalsIgnoreCase("Bagan");
    }

    public String getTeam1Nama() {
        Bagan bagan;
        if (data instanceof Bagan) {
            bagan = (Bagan) data;
            return bagan.getTeam1();
        }
        return "";
    }

    public String getTeam2Nama() {
        Bagan bagan;
        if (data instanceof Bagan) {
            bagan = (Bagan) data;
            return bagan.getTeam2();
        }
        return "";
    }

    public int getTeam1Resource() {
        Bagan bagan;
        if (data instanceof Bagan) {
            bagan = (Bagan) data;
            Fakultas fak = DataList.getFakultasObject(bagan.getTeam1());
            if (fak != null) return fak.getFakultasIconRes();
        }
        return R.drawable.ic_tobedetermined_icon;
    }

    public int getTeam2Resource() {
        Bagan bagan;
        if (data instanceof Bagan) {
            bagan = (Bagan) data;
            Fakultas fak = DataList.getFakultasObject(bagan.getTeam2());
            if (fak != null) return fak.getFakultasIconRes();
        }
        return R.drawable.ic_tobedetermined_icon;
    }

    public String getTeam1Score() {
        String score = "-";
        if (getCabang().equalsIgnoreCase("bulu tangkis") || getCabang().equalsIgnoreCase("voli")) {
            if (data instanceof Bagan) {
                Bagan bagan = (Bagan) data;
                score = "";
                for (int i = 0; i < bagan.getScore().size(); i++) {
                    score += bagan.getScore().get(i).getScore1();
                    if (i != bagan.getScore().size() - 1) score += "\n";
                }
                if (score.equals("")) score = "-";
            }
        } else {
            if (data instanceof Bagan) {
                Bagan bagan = (Bagan) data;
                score = bagan.getScore().get(0).getScore1();
                if (score.equals("")) score = "-";
            }
        }
        return score;
    }

    public String getTeam2Score() {
        String score = "-";
        if (getCabang().equalsIgnoreCase("bulu tangkis") || getCabang().equalsIgnoreCase("voli")) {
            if (data instanceof Bagan) {
                Bagan bagan = (Bagan) data;
                score = "";
                for (int i = 0; i < bagan.getScore().size(); i++) {
                    score += bagan.getScore().get(i).getScore2();
                    if (i != bagan.getScore().size() - 1) score += "\n";
                }
                if (score.equals("")) score = "-";
            }
        } else {
            if (data instanceof Bagan) {
                Bagan bagan = (Bagan) data;
                score = bagan.getScore().get(0).getScore2();
                if (score.equals("")) score = "-";
            }
        }
        return score;
    }

    public String getTeam1ScoreUnformat() {
        String score = "-";
        if (getCabang().equalsIgnoreCase("bulu tangkis") || getCabang().equalsIgnoreCase("voli")) {
            if (data instanceof Bagan) {
                Bagan bagan = (Bagan) data;
                score = "";
                for (int i = 0; i < bagan.getScore().size(); i++) {
                    score += bagan.getScore().get(i).getScore1();
                    if (i != bagan.getScore().size() - 1) score += "/";
                }
                if (score.equals("")) score = "-";
            }
        } else {
            score = getTeam1Score();
        }
        return score;
    }

    public String getTeam2ScoreUnformat() {
        String score = "-";
        if (getCabang().equalsIgnoreCase("bulu tangkis") || getCabang().equalsIgnoreCase("voli")) {
            if (data instanceof Bagan) {
                Bagan bagan = (Bagan) data;
                score = "";
                for (int i = 0; i < bagan.getScore().size(); i++) {
                    score += bagan.getScore().get(i).getScore2();
                    if (i != bagan.getScore().size() - 1) score += "/";
                }
                if (score.equals("")) score = "-";
            }
        } else {
            score = getTeam2Score();
        }
        return score;
    }

    public String getJuara1Name() {
        String name = "-";
        if (data instanceof Langsung) {
            Langsung langsung = (Langsung) data;
            name = langsung.getSatu();
            if (name.equalsIgnoreCase("null")) name = "-";
        }
        return name;
    }

    public String getJuara2Name() {
        String name = "-";
        if (data instanceof Langsung) {
            Langsung langsung = (Langsung) data;
            name = langsung.getDua();
            if (name.equalsIgnoreCase("null")) name = "-";
        }
        return name;
    }

    public String getJuara3Name() {
        String name = "-";
        if (data instanceof Langsung) {
            Langsung langsung = (Langsung) data;
            name = langsung.getTiga();
            if (name.equalsIgnoreCase("null")) name = "-";
        }
        return name;
    }

    public int getJuara1Resource() {
        String name;
        if (data instanceof Langsung) {
            Langsung langsung = (Langsung) data;
            name = langsung.getSatu();
            Fakultas fak = DataList.getFakultasObject(name);
            if (fak != null) return fak.getFakultasIconRes();
        }
        return R.drawable.ic_tobedetermined_icon;
    }

    public int getJuara2Resource() {
        String name;
        if (data instanceof Langsung) {
            Langsung langsung = (Langsung) data;
            name = langsung.getDua();
            Fakultas fak = DataList.getFakultasObject(name);
            if (fak != null) return fak.getFakultasIconRes();
        }
        return R.drawable.ic_tobedetermined_icon;
    }

    public int getJuara3Resource() {
        String name;
        if (data instanceof Langsung) {
            Langsung langsung = (Langsung) data;
            name = langsung.getTiga();
            Fakultas fak = DataList.getFakultasObject(name);
            if (fak != null) return fak.getFakultasIconRes();
        }
        return R.drawable.ic_tobedetermined_icon;
    }

    public String getJamMain() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String waktu = "";
        try {
            Date date = df.parse(getStarttime());
            waktu = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return waktu;
    }

    public String getTanggalMain() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String waktu = "";
        try {
            Date date = df.parse(getStarttime());
            waktu = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return waktu;
    }

    public boolean isLive() {
        return status.toLowerCase().contains("live");
    }

    public void onShareClick(View view) {
        Context context = view.getContext();

        String shareBody = "";

        if (status.toLowerCase().contains("live")) {
            String team1 = getTeam1Nama();
            String team2 = getTeam2Nama();
            String score1 = getTeam1ScoreUnformat();
            String score2 = getTeam2ScoreUnformat();
            shareBody = String.format("Sedang berlangsung perlombaan %s antara %s lawan %s dengan skor %s - %s, untuk detail lebih lanjut " +
                    "download aplikasi Olimpiade Brawijaya 2016 https://play.google.com/store/apps/details?id=com.raioncommunity.ob2016 " +
                    "dan kunjungi website di http://olimpiade.ub.ac.id/", getCabang(), team1, team2, score1, score2);
        } else if (status.toLowerCase().contains("belum")) {
            if (getIsBagan()) {
                String team1 = getTeam1Nama();
                String team2 = getTeam2Nama();
                shareBody = String.format("Akan berlangsung perlombaan %s antara %s lawan %s, untuk detail lebih lanjut " +
                        "download aplikasi Olimpiade Brawijaya 2016 https://play.google.com/store/apps/details?id=com.raioncommunity.ob2016 " +
                        "dan kunjungi website di http://olimpiade.ub.ac.id/", getCabang(), team1, team2);
            } else {
                shareBody = String.format("Akan berlangsung perlombaan kategori %s, untuk detail lebih lanjut " +
                        "download aplikasi Olimpiade Brawijaya 2016 https://play.google.com/store/apps/details?id=com.raioncommunity.ob2016 " +
                        "dan kunjungi website di http://olimpiade.ub.ac.id/", getCabang());
            }
        } else {
            if (getIsBagan()) {
                String team1 = getTeam1Nama();
                String team2 = getTeam2Nama();
                String score1 = getTeam1ScoreUnformat();
                String score2 = getTeam2ScoreUnformat();
                shareBody = String.format("Sudah berakhir perlombaan %s antara %s lawan %s dengan skor %s - %s, untuk detail lebih lanjut " +
                        "download aplikasi Olimpiade Brawijaya 2016 https://play.google.com/store/apps/details?id=com.raioncommunity.ob2016 " +
                        "dan kunjungi website di http://olimpiade.ub.ac.id/", getCabang(), team1, team2, score1, score2);
            } else {
                shareBody = String.format("Sudah selesai perlombaan kategori %s dengan Juara 1 - %s, Juara 2 - %s, Juara 3 - %s, untuk detail lebih lanjut " +
                        "download aplikasi Olimpiade Brawijaya 2016 https://play.google.com/store/apps/details?id=com.raioncommunity.ob2016 " +
                        "dan kunjungi website di http://olimpiade.ub.ac.id/", getCabang(), getJuara1Name(), getJuara2Name(), getJuara3Name());
            }
        }

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
