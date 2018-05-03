package com.raioncommunity.android.ob2017.ui.nav_menu.beranda;

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

import static com.raioncommunity.android.ob2017.debug.Tag.TOMORROW_FETCH_TAG;

/**
 * Created by amira on 7/3/2017.
 */

/*
* Modified by arifinfirdaus
*/

public class TomorrowFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static TomorrowFragment newInstance() {

        Bundle args = new Bundle();
        TomorrowFragment fragment = new TomorrowFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public RecyclerView mRecyclerView;
    public CardScoreUpcomingAdapter mAdapter;
    public SwipeRefreshLayout mSwipeRefresh;
    public ObservableList<Jadwal> jadwalList;

    private TextView tv_tidak_ada_data_tomorrow_fragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tomorrow, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.tomorrow_recyclerView);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.today_swipeRefresh);
        mSwipeRefresh.setOnRefreshListener(this);

        tv_tidak_ada_data_tomorrow_fragment = (TextView) view.findViewById(R.id.tv_tidak_ada_data_tomorrow_fragment);
        tv_tidak_ada_data_tomorrow_fragment.setVisibility(View.VISIBLE);

        fetchData();


    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    private void fetchData() {
        mSwipeRefresh.setRefreshing(true);
        RestClient.jadwalService.getJadwalBesok().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JadwalListResponse jadwalResponse = JadwalConverter.fromJson(responseBody);

                        if (jadwalResponse != null && jadwalResponse.getList() != null) {
                            jadwalList.clear();
                            jadwalList.addAll(jadwalResponse.getList());
                            setupRecyclerView(mRecyclerView);
                            tv_tidak_ada_data_tomorrow_fragment.setVisibility(View.INVISIBLE);
                        } else {
                            JSONObject json = new JSONObject(responseBody);  //your response
                            try {
                                String pesan = json.getString("Pesan");    //result is key for which you need to retrieve data
                                Log.d(TOMORROW_FETCH_TAG, "onResponse: pesan: " + pesan);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tv_tidak_ada_data_tomorrow_fragment.setVisibility(View.VISIBLE);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (mSwipeRefresh.isRefreshing()) {
                        mSwipeRefresh.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TOMORROW_FETCH_TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CardScoreUpcomingAdapter(jadwalList));
    }


    private void perpareDummyData() {
        //        List<DummyScore> scoreList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            scoreList.add(new DummyScore("09:00", "14/07/17", R.drawable.ic_action_futsal,
//                    "Futsal", "Putra", true, "GOR"));
//        }
//
//        mAdapter = new CardScoreUpcomingAdapter(scoreList);
    }
}


