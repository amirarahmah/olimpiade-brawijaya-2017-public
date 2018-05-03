package com.raioncommunity.android.ob2017.ui.nav_menu.cabang_olahraga;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.DummyScore;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.adapter.CardScoreUpcomingAdapter;
import com.raioncommunity.android.ob2017.model.api.RestClient;
import com.raioncommunity.android.ob2017.model.api.service.JadwalService;
import com.raioncommunity.android.ob2017.model.json.Jadwal;
import com.raioncommunity.android.ob2017.model.json.JadwalListResponse;
import com.raioncommunity.android.ob2017.util.JadwalConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.raioncommunity.android.ob2017.debug.Tag.CABOR_UPCOMING_TAG;

/**
 * Created by amira on 7/6/2017.
 */

/*
* modified by arifinfirdaus
* */

public class UpcomingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static UpcomingFragment newInstance() {

        Bundle args = new Bundle();
        UpcomingFragment fragment = new UpcomingFragment();
        fragment.setArguments(args);
        return fragment;

    }

    private int caborId;

    private RecyclerView mRecyclerView;
    private CardScoreUpcomingAdapter mAdapter;
    private List<DummyScore> scoreList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mUpcomingText;

    private ObservableList<Jadwal> jadwalList;
    private Call<ResponseBody> callback;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        caborId = getArguments().getInt(CabangOlahragaContainerFragment.CABOR_ID_KEY);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.upcoming_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.upcoming_swipeRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        mUpcomingText = (TextView) view.findViewById(R.id.upcoming_text);
        mUpcomingText.setVisibility(View.INVISIBLE);

        fetchData();
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        mSwipeRefreshLayout.setRefreshing(true);
        callback = RestClient.jadwalService.getJadwalByIdCaborAndStatus(caborId, JadwalService.STATUS_UPCOMING);

        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JadwalListResponse jadwalResponse = JadwalConverter.fromJson(responseBody);
                        if (jadwalResponse != null && jadwalResponse.getList() != null) {
                            jadwalList.clear();
                            jadwalList.addAll(jadwalResponse.getList());
                            for (Jadwal jadwal : jadwalList) {
                                Log.d(CABOR_UPCOMING_TAG, "onResponse: " + jadwal.getCabordet());
                            }
                            setupRecyclerView();
                        } else {
                            mUpcomingText.setVisibility(View.VISIBLE);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(getContext(), "Terjadi kesalahan network.", Toast.LENGTH_SHORT).show();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(CABOR_UPCOMING_TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getContext(), "Terjadi kesalahan network.", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void prepareDummyData() {
        scoreList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scoreList.add(new DummyScore("09:00", "14/07/17", R.drawable.ic_action_futsal,
                    "Futsal", "Putra", true, "GOR"));
        }
    }

    private void setupRecyclerView() {
        mAdapter = new CardScoreUpcomingAdapter(jadwalList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

}
