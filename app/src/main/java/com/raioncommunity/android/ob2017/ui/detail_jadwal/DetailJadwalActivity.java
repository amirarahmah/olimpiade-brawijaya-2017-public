package com.raioncommunity.android.ob2017.ui.detail_jadwal;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.raioncommunity.android.ob2017.DummyScore;
import com.raioncommunity.android.ob2017.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailJadwalActivity extends AppCompatActivity {

    long date;

    private Date data;
    private String[] hari = {"", "Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};
    private String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli",
            "Agustus", "September", "Oktober", "November", "Desember"};

    Toolbar toolbar;
    RecyclerView recyclerView;
    DetailJadwalAdapter adapter;
    List<DummyScore> jadwalList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);

        if (getIntent().getExtras() != null) {
            date = getIntent().getExtras().getLong("DATE", -1);
        }

        data = new Date();
        data.setTime(date);

        Calendar c = Calendar.getInstance();
        c.setTime(data);
        String dataShow = "";
        dataShow += hari[c.get(Calendar.DAY_OF_WEEK)];
        dataShow += ", " + c.get(Calendar.DATE) + " " + bulan[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR);

        toolbar = (Toolbar) findViewById(R.id.detailJadwal_toolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(dataShow);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.detailJadwal_recyclerView);

        jadwalList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            jadwalList.add(new DummyScore("Basket", "GOR Pertamina", "09:30"));
        }

        adapter = new DetailJadwalAdapter(jadwalList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
