package com.raioncommunity.android.ob2017.ui.nav_menu.klasmen;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.api.RestClient;
import com.raioncommunity.android.ob2017.model.json.Medali;
import com.raioncommunity.android.ob2017.model.json.MedaliListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.raioncommunity.android.ob2017.debug.Tag.KLASMEN_TAG;


public class KlasmenFragment extends Fragment {

    public static KlasmenFragment newInstance() {

        Bundle args = new Bundle();
        KlasmenFragment fragment = new KlasmenFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public RecyclerView mRvKlasmen;
    public MedaliAdapter mKlasmenAdapter;
    private ObservableList<Medali> mKlasmenList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_klasmen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mKlasmenList = new ObservableArrayList<>();
        mRvKlasmen = (RecyclerView) view.findViewById(R.id.klasmen_recyclerView);

        fetchData();


    }

    private void fetchData() {
        RestClient.medaliService.getMedaliList().enqueue(new Callback<MedaliListResponse>() {
            @Override
            public void onResponse(Call<MedaliListResponse> call, Response<MedaliListResponse> response) {
                if (response.isSuccessful()) {
                    List<Medali> listResponse = response.body().getMedali();
                    if (listResponse != null) {
                        mKlasmenList.clear();
                        mKlasmenList.addAll(listResponse);
                        setupRecyclerView(mRvKlasmen);
                    }
                }
            }

            @Override
            public void onFailure(Call<MedaliListResponse> call, Throwable t) {
                Log.d(KLASMEN_TAG, "onFailure: ");
                Toast.makeText(getContext(), "Terjadi kesalahan. Mohon coba lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(
                new MedaliAdapter(
                        mKlasmenList,
                        getContext()
                ));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}
