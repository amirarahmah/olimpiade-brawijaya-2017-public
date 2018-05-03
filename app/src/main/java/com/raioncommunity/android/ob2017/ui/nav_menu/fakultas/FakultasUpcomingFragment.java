package com.raioncommunity.android.ob2017.ui.nav_menu.fakultas;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.graphics.Color;
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
import com.raioncommunity.android.ob2017.adapter.CardScoreUpcomingAdapter;
import com.raioncommunity.android.ob2017.model.api.RestClient;
import com.raioncommunity.android.ob2017.model.api.StatusJadwal;
import com.raioncommunity.android.ob2017.model.json.Jadwal;
import com.raioncommunity.android.ob2017.model.json.JadwalListResponse;
import com.raioncommunity.android.ob2017.model.view.Fakultas;
import com.raioncommunity.android.ob2017.ui.nav_menu.cabang_olahraga.CabangOlahragaContainerFragment;
import com.raioncommunity.android.ob2017.util.Constant;
import com.raioncommunity.android.ob2017.util.GSONConverter;
import com.raioncommunity.android.ob2017.util.JadwalConverter;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.raioncommunity.android.ob2017.debug.Tag.FAK_RESULT_TAG;
import static com.raioncommunity.android.ob2017.debug.Tag.FAK_UPCOMING_TAG;
import static com.raioncommunity.android.ob2017.util.Constant.SHARED_PREF_NAME;

/**
 * Created by amira on 7/4/2017.
 */

/*
* modified by arifinfirdaus
* */

public class FakultasUpcomingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static FakultasUpcomingFragment newInstance() {

        Bundle args = new Bundle();
        FakultasUpcomingFragment fragment = new FakultasUpcomingFragment();
        fragment.setArguments(args);
        return fragment;

    }

    int caborId;

    private int mFakultasId;

    private RecyclerView mRecyclerView;
    private CardScoreUpcomingAdapter adapter;
    private List<DummyScore> scoreList;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ObservableList<Jadwal> mJadwalList;
    private Call<ResponseBody> mCallback;

    private Fakultas mSelectedFakultas;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // caborId = getArguments().getInt(CabangOlahragaContainerFragment.CABOR_ID_KEY);

        mFakultasId = getArguments().getInt(FakultasLainContainerFragment.FAKULTAS);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.upcoming_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.upcoming_swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        mJadwalList = new ObservableArrayList<>();

        fetchData();


    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
        Log.d(FAK_UPCOMING_TAG, "onResume");
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    private void fetchData() {
        Log.d(FAK_UPCOMING_TAG, "fetchData: ");
        mCallback = RestClient.jadwalService.getJadwalByIdFakultas(mFakultasId);
        mCallback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(FAK_UPCOMING_TAG, "fetchData: response.isSuccessful(): " + response.isSuccessful());
                    try {
                        String responseBody = response.body().string();
                        JadwalListResponse jadwalResponse = JadwalConverter.fromJson(responseBody);
                        if (jadwalResponse != null && jadwalResponse.getList() != null) {
                            mJadwalList.clear();
                            for (Jadwal j : jadwalResponse.getList()) {
                                if (j.getStatus().toLowerCase().contains(StatusJadwal.BELUM)) {
                                    mJadwalList.add(j);
                                } else {
                                    Log.d(FAK_UPCOMING_TAG, "fetchData: not containt Belum");
                                }
                            }
                            Log.d(FAK_UPCOMING_TAG, "fetchData: if: ");
                            Log.d(FAK_UPCOMING_TAG, "fetchData: if: mJadwalList.size: " + mJadwalList.size());
                            setupRecyclerView(mRecyclerView);
                        } else {
                            Log.d(FAK_UPCOMING_TAG, "fetchData: else: jadwalResponse != null && jadwalResponse.getList() != null");
                            // getBinding().upcomingText.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Tidak ada data.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d(FAK_UPCOMING_TAG, "fetchData: catch: " + e.getLocalizedMessage());
                    }
                } else {
                    Log.d(FAK_UPCOMING_TAG, "fetchData: response.isSuccessful(): " + response.isSuccessful());
                    Toast.makeText(getContext(), "Terjadi kesalahan. Mohon coba lagi. ", Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(FAK_UPCOMING_TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }


    private void loadSharedPrefFakultasSaya() {
        SharedPreferences sharedPref = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String strSharedPrefFakultasFavorit = sharedPref.getString(
                Constant.FAKULTAS_FAVORIT_KEY,
                null
        );

        mSelectedFakultas = GSONConverter.fromStringToObject(strSharedPrefFakultasFavorit, Fakultas.class);
        Log.d(FAK_UPCOMING_TAG, "loadSharedPrefFakultasSaya: mSelectedFakultas: " + GSONConverter.fromObjectToString(mSelectedFakultas));
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,
                        false)
        );
        adapter = new CardScoreUpcomingAdapter(mJadwalList);
        recyclerView.setAdapter(adapter);
    }

    private void prepareDummyData() {
//        scoreList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            scoreList.add(new DummyScore("09:00", "14/07/17", R.drawable.ic_action_futsal,
//                    "Futsal", "Putra", true, "GOR"));
//        }
//
//        adapter = new CardScoreUpcomingAdapter(scoreList);
    }


}
