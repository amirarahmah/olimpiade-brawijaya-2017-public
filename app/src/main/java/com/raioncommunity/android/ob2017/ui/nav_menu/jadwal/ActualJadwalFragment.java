package com.raioncommunity.android.ob2017.ui.nav_menu.jadwal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.ui.detail_jadwal.DetailJadwalActivity;
import com.raioncommunity.android.ob2017.model.view.DateToView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by amira on 7/2/2017.
 */

public class ActualJadwalFragment extends Fragment implements DateAdapter.ClickListener{
    public static ActualJadwalFragment newInstance(){

        Bundle args = new Bundle();
        ActualJadwalFragment fragment = new ActualJadwalFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public List<DateToView> dateList;
    public RecyclerView recyclerView;
    public DateAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actual_jadwal, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateList = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        Date tomorrow = c.getTime();
        dateList.add(new DateToView(tomorrow));
        int tomorrowDate = c.get(Calendar.DATE);
        for(int i = tomorrowDate; i <= 20; i++) {
            c.add(Calendar.DATE, 1);
            dateList.add(new DateToView(c.getTime()));
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.actualJadwal_recyclerView);
        adapter = new DateAdapter(getContext(), dateList);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void itemClicked(View view, int position) {
        DateToView date = dateList.get(position);
        Intent intent = new Intent(getContext(), DetailJadwalActivity.class);
        intent.putExtra("DATE", date.getData().getTime());
        startActivity(intent);
    }
}
