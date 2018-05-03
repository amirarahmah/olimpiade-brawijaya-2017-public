package com.raioncommunity.android.ob2017.ui.nav_menu.fakultas;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableArrayList;
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

import com.raioncommunity.android.ob2017.DummyScore;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.adapter.CardScoreDoneAdapter;
import com.raioncommunity.android.ob2017.model.api.RestClient;
import com.raioncommunity.android.ob2017.model.api.StatusJadwal;
import com.raioncommunity.android.ob2017.model.json.Jadwal;
import com.raioncommunity.android.ob2017.model.json.JadwalListResponse;
import com.raioncommunity.android.ob2017.model.view.Fakultas;
import com.raioncommunity.android.ob2017.util.Constant;
import com.raioncommunity.android.ob2017.util.GSONConverter;
import com.raioncommunity.android.ob2017.util.JadwalConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.raioncommunity.android.ob2017.debug.Tag.FAK_RESULT_TAG;
import static com.raioncommunity.android.ob2017.util.Constant.SHARED_PREF_NAME;

/**
 * Created by amira on 7/4/2017.
 */

/*
* modified by arifinfirdaus
* */

public class FakultasResultFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static FakultasResultFragment newInstance() {

        Bundle args = new Bundle();
        FakultasResultFragment fragment = new FakultasResultFragment();
        fragment.setArguments(args);
        return fragment;

    }

    private int mFakultasId;

    private RecyclerView mRecyclerView;
    private CardScoreDoneAdapter adapter;
    private List<DummyScore> scoreList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ObservableList<Jadwal> mJadwalList;
    private Call<ResponseBody> mCallback;

    private Fakultas mSelectedFakultas;


    // MARK: - Fragment life cycle

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFakultasId = getArguments().getInt(FakultasSayaFragment.fakultasKey);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.result_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.result_swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mJadwalList = new ObservableArrayList<>();

        fetchData();
    }


    @Override
    public void onResume() {
        super.onResume();
        fetchData();
        Log.d(FAK_RESULT_TAG, "onResume");
    }

    // MARK: - SwipeRefreshLayout.OnRefreshListener
    @Override
    public void onRefresh() {
        fetchData();
    }


    private void fetchData() {
        mCallback = RestClient.jadwalService.getJadwalByIdFakultas(mFakultasId);
        mCallback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JadwalListResponse jadwalResponse = JadwalConverter.fromJson(responseBody);
                        if (jadwalResponse != null && jadwalResponse.getList() != null) {
                            mJadwalList.clear();
                            for (Jadwal j : jadwalResponse.getList()) {
                                if (!j.getStatus().toLowerCase().contains(StatusJadwal.SELESAI)) {
                                    mJadwalList.add(j);
                                }
                            }
                            // Log.d(FAK_RESULT_TAG, "onResponse: mJadwalList: " + GSONConverter.fromListToSetString(mJadwalList));
                            setupRecyclerView(mRecyclerView);
                        } else {
                            // resultText.setVisibility(View.VISIBLE);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(FAK_RESULT_TAG, "onFailure: " + t.getLocalizedMessage());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,
                        false)
        );
        adapter = new CardScoreDoneAdapter(mJadwalList);
        recyclerView.setAdapter(adapter);
    }

    private void prepareDummyData() {
        scoreList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scoreList.add(new DummyScore(R.drawable.ic_action_futsal, "Futsal",
                    "Putra", true, "Fakultas Ekonomi dan Bisnis", R.drawable.ic_feb, "2",
                    "Fakultas Teknik", R.drawable.ic_tekniik, "3", "Lokasi Percobaan"));
        }
    }

    private void loadSharedPrefFakultasSaya() {
        SharedPreferences sharedPref = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String strSharedPrefFakultasFavorit = sharedPref.getString(
                Constant.FAKULTAS_FAVORIT_KEY,
                null
        );

        mSelectedFakultas = GSONConverter.fromStringToObject(strSharedPrefFakultasFavorit, Fakultas.class);
        Log.d(FAK_RESULT_TAG, "loadSharedPrefFakultasSaya: mSelectedFakultas: " + GSONConverter.fromObjectToString(mSelectedFakultas));
    }

}
