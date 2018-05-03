package com.raioncommunity.android.ob2017.ui.nav_menu.cabang_olahraga;

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
import android.widget.Toast;

import com.raioncommunity.android.ob2017.DummyScore;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.adapter.CardScoreDoneAdapter;
import com.raioncommunity.android.ob2017.model.api.RestClient;
import com.raioncommunity.android.ob2017.model.api.service.JadwalService;
import com.raioncommunity.android.ob2017.model.json.Jadwal;
import com.raioncommunity.android.ob2017.model.json.JadwalListResponse;
import com.raioncommunity.android.ob2017.util.JadwalConverter;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.raioncommunity.android.ob2017.debug.Tag.CABOR_RESULT_TAG;

/**
 * Created by amira on 7/6/2017.
 */

/*
* modified by arifinfirdaus
* */


public class ResultFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static ResultFragment newInstance() {

        Bundle args = new Bundle();
        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;

    }

    int caborId;

    RecyclerView mRecyclerView;
    CardScoreDoneAdapter adapter;
    List<DummyScore> scoreList;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ObservableList<Jadwal> mJadwalList;
    Call<ResponseBody> mCallback;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        caborId = getArguments().getInt(CabangOlahragaContainerFragment.CABOR_ID_KEY);

        Log.d(CABOR_RESULT_TAG, "onViewCreated: caborId :" + caborId);

        mJadwalList = new ObservableArrayList<>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.result_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.result_swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

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
        mCallback = RestClient.jadwalService.getJadwalByIdCaborAndStatus(caborId,
                JadwalService.STATUS_DONE);

        mCallback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(CABOR_RESULT_TAG, "onResponse: else: response.isSuccessful: " +
                        response.isSuccessful());
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JadwalListResponse jadwalResponse = JadwalConverter.fromJson(responseBody);
                        if (jadwalResponse != null && jadwalResponse.getList() != null) {
                            mJadwalList.clear();
                            mJadwalList.addAll(jadwalResponse.getList());
                            for (Jadwal jadwal : mJadwalList) {
                                Log.d(CABOR_RESULT_TAG, "onResponse: " + jadwal.getCabordet());
                            }
                            setupRecyclerView();
                            Log.d(CABOR_RESULT_TAG, "onResponse: done if");
                        } else {
                            // getBinding().resultText.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Tidak ada data.", Toast.LENGTH_SHORT).show();
                            Log.d(CABOR_RESULT_TAG, "onResponse: else");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(CABOR_RESULT_TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new CardScoreDoneAdapter(mJadwalList));
    }


    private void prepareDummyData() {
//        scoreList = new ArrayList<>();
//        for(int i = 0; i < 5; i++){
//            scoreList.add(new DummyScore(R.drawable.ic_action_futsal, "Futsal",
//                    "Putra", true, "Fakultas Ekonomi dan Bisnis", R.drawable.ic_feb, "2",
//                    "Fakultas Teknik", R.drawable.ic_tekniik, "3", "Lokasi Percobaan"));
//        }
//
//        adapter = new CardScoreDoneAdapter(scoreList);
    }

}
