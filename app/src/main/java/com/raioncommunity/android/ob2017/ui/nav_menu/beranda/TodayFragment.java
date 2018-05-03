package com.raioncommunity.android.ob2017.ui.nav_menu.beranda;

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
import android.widget.TextView;

import com.raioncommunity.android.ob2017.DummyScore;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.adapter.CardScoreAdapter;
import com.raioncommunity.android.ob2017.model.api.RestClient;
import com.raioncommunity.android.ob2017.model.json.Jadwal;
import com.raioncommunity.android.ob2017.model.json.JadwalListResponse;
import com.raioncommunity.android.ob2017.util.JadwalConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.raioncommunity.android.ob2017.debug.Tag.TODAY_FETCH_TAG;


public class TodayFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static TodayFragment newInstance() {

        Bundle args = new Bundle();
        TodayFragment fragment = new TodayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerView mRecyclerView;
    public CardScoreAdapter mAdapter;
    public SwipeRefreshLayout mSwipeRefresh;
    public ObservableList<Jadwal> mJadwalList;
    private TextView tv_tidak_ada_data_today_fragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mJadwalList = new ObservableArrayList<>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.today_recyclerView);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.today_swipeRefresh);
        mSwipeRefresh.setOnRefreshListener(this);

        tv_tidak_ada_data_today_fragment = (TextView) view.findViewById(R.id.tv_tidak_ada_data_today_fragment);


        fetchData();


    }

    // MARK: - wipeRefreshLayout.OnRefreshListener
    @Override
    public void onRefresh() {
        fetchData();

    }

    private void fetchData() {
        mSwipeRefresh.setRefreshing(true);
        RestClient.jadwalService.getJadwalHariIni().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JadwalListResponse jadwalResponse = JadwalConverter.fromJson(responseBody);

                        if (jadwalResponse != null && jadwalResponse.getList() != null) {
                            mJadwalList.clear();
                            for (Jadwal j : jadwalResponse.getList()) {
                                if (j.isLive()) {
                                    mJadwalList.add(j);
                                }
                            }
                            for (Jadwal j : jadwalResponse.getList()) {
                                if (!j.isLive()) {
                                    mJadwalList.add(j);
                                }
                            }
                            setupRecyclerView();
                            tv_tidak_ada_data_today_fragment.setVisibility(View.INVISIBLE);
                        } else {
                            JSONObject json = new JSONObject(responseBody);  //your response
                            try {
                                String pesan = json.getString("Pesan");    //result is key for which you need to retrieve data
                                // Toast.makeText(getContext(), "Hari ini : " + pesan, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tv_tidak_ada_data_today_fragment.setVisibility(View.VISIBLE);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (mSwipeRefresh.isRefreshing()) {
                    mSwipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TODAY_FETCH_TAG, "onFailure: " + t.getLocalizedMessage());
                if (mSwipeRefresh.isRefreshing()) {
                    mSwipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false)
        );
        mRecyclerView.setAdapter(mAdapter);
    }

    private void prepareDummyData() {
        List<DummyScore> scoreList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scoreList.add(
                    new DummyScore(
                            R.drawable.ic_action_futsal, "Futsal", "Putra", true, "Fakultas Ekonomi dan Bisnis",
                            R.drawable.ic_feb, "2", "Fakultas Teknik",
                            R.drawable.ic_tekniik, "3", "Lokasi Percobaan"
                    )
            );
        }
        mAdapter = new CardScoreAdapter(scoreList);
    }

}
