package com.raioncommunity.android.ob2017.ui.nav_menu.berita;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.net.Uri;
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

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.api.RestClient;
import com.raioncommunity.android.ob2017.model.json.News;
import com.raioncommunity.android.ob2017.model.json.NewsBriefResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "news";
    public ObservableList<News> mNewsList;
    private SwipeRefreshLayout mBeritaSwipeToRefresh;
    private RecyclerView mRecyclerView;


    public BeritaFragment() {
    }

    public static BeritaFragment newInstance(String param1, String param2) {
        BeritaFragment fragment = new BeritaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    // MARK : - Activity Lifecycle

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_berita, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewsList = new ObservableArrayList<>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.berita_list);
        mBeritaSwipeToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.berita_swipeToRefresh);
        mBeritaSwipeToRefresh.setOnRefreshListener(this);

        fetchData();

    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    private void fetchData() {
        mBeritaSwipeToRefresh.setRefreshing(true);
        Log.d(TAG, "fetchData: ");
        RestClient.beritaService.getNewsBrief(1).enqueue(new Callback<NewsBriefResponse>() {
            @Override
            public void onResponse(Call<NewsBriefResponse> call, Response<NewsBriefResponse> response) {
                if (response.isSuccessful()) {
                    List<News> responseList = response.body().getList();
                    Log.d(TAG, "fetchData: onResponse: " + responseList.get(1).getJudul());
                    if (responseList != null) {
                        mNewsList.clear();
                        mNewsList.addAll(responseList);
                        Log.d(TAG, "fetchData: onResponse: if: mNewsList size: " + mNewsList.size());
                        setupRecyclerView(mRecyclerView);
                    } else {
                        Log.d(TAG, "fetchData: onResponse: else: " + responseList.toString());
                    }
                } else {
                    Log.d(TAG, "fetchData: response.isSuccessful: " + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<NewsBriefResponse> call, Throwable t) {
                Log.d(TAG, "fetchData: onFailure: " + t.getLocalizedMessage());
            }
        });
        mBeritaSwipeToRefresh.setRefreshing(false);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(
                new BeritaRecyclerViewAdapter(
                        mNewsList,
                        getContext()
                ));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
