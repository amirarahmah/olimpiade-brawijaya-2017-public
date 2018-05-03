package com.raioncommunity.android.ob2017.model.view;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by bradhawk on 10/9/2016.
 */

public class DateToView {
    private Date data;
    private String[] hari = {"", "Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};
    private String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli",
    "Agustus", "September", "Oktober", "November", "Desember"};

    public DateToView(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPrintedDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        String dataShow = "";
        dataShow += hari[c.get(Calendar.DAY_OF_WEEK)];
        dataShow += ", " + c.get(Calendar.DATE) + " " + bulan[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR);

        return dataShow;
    }
}
