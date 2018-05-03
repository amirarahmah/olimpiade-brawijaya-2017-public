package com.raioncommunity.android.ob2017.ui.welcome.welcome_3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raioncommunity.android.ob2017.model.dummy.DummyContent;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.model.view.Fakultas;
import com.raioncommunity.android.ob2017.model.view.FakultasWelcome;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.util.Constant;
import com.raioncommunity.android.ob2017.util.DataList;
import com.raioncommunity.android.ob2017.util.GSONConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.raioncommunity.android.ob2017.debug.Tag.WELCOME_3_TAG;
import static com.raioncommunity.android.ob2017.util.Constant.SHARED_PREF_NAME;

/**
 * Created by amira on 6/25/2017.
 */

/**
 * Modified by arifinfirdaus
 */

public class Welcome3Fragment extends Fragment implements Welcome3FragmentCallback {

    public static Welcome3Fragment newInstance() {
        Bundle args = new Bundle();
        Welcome3Fragment fragment = new Welcome3Fragment();
        fragment.setArguments(args);
        return fragment;

    }

    RecyclerView mRecyclerView;
    List<FakultasWelcome> fakultasList;
    ArrayList<Fakultas> mFakultasArrayList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_welcome_page3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.page3_recyclerView);
        fakultasList = new ArrayList<>();

        fakultasList.add(new FakultasWelcome(R.string.fakultasFH));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFEB));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFIA));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFP));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFapet));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFT));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFK));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFPIK));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFMIPA));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFTP));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFISIP));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFIB));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFKH));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFKG));
        fakultasList.add(new FakultasWelcome(R.string.fakultasFILKOM));
        fakultasList.add(new FakultasWelcome(R.string.fakultasVokasi));
        fakultasList.add(new FakultasWelcome(R.string.fakultasKediri));

        initFakultas();
        setupRecyclerView(mRecyclerView);

    }

    private void initFakultas() {
        mFakultasArrayList = DataList.getFakultasArrayList();
        mFakultasArrayList.add(new Fakultas(1, R.drawable.ic_fh, R.string.fakultasFH, getContext().getResources().getString(R.string.fakultasFH)));
        mFakultasArrayList.add(new Fakultas(2, R.drawable.ic_feb, R.string.fakultasFEB, getContext().getResources().getString(R.string.fakultasFEB)));
        mFakultasArrayList.add(new Fakultas(3, R.drawable.ic_fia, R.string.fakultasFIA, getContext().getResources().getString(R.string.fakultasFIA)));
        mFakultasArrayList.add(new Fakultas(4, R.drawable.ic_fp, R.string.fakultasFP, getContext().getResources().getString(R.string.fakultasFP)));
        mFakultasArrayList.add(new Fakultas(5, R.drawable.ic_fapet, R.string.fakultasFapet, getContext().getResources().getString(R.string.fakultasFapet)));
        mFakultasArrayList.add(new Fakultas(6, R.drawable.ic_tekniik, R.string.fakultasFT, getContext().getResources().getString(R.string.fakultasFT)));
        mFakultasArrayList.add(new Fakultas(7, R.drawable.ic_fk, R.string.fakultasFK, getContext().getResources().getString(R.string.fakultasFK)));
        mFakultasArrayList.add(new Fakultas(8, R.drawable.ic_fpik, R.string.fakultasFPIK, getContext().getResources().getString(R.string.fakultasFPIK)));
        mFakultasArrayList.add(new Fakultas(9, R.drawable.ic_fmipa, R.string.fakultasFMIPA, getContext().getResources().getString(R.string.fakultasFMIPA)));
        mFakultasArrayList.add(new Fakultas(10, R.drawable.ic_ftp, R.string.fakultasFTP, getContext().getResources().getString(R.string.fakultasFTP)));
        mFakultasArrayList.add(new Fakultas(11, R.drawable.ic_fisip, R.string.fakultasFISIP, getContext().getResources().getString(R.string.fakultasFISIP)));
        mFakultasArrayList.add(new Fakultas(12, R.drawable.ic_fib, R.string.fakultasFIB, getContext().getResources().getString(R.string.fakultasFIB)));
        mFakultasArrayList.add(new Fakultas(13, R.drawable.ic_fkh, R.string.fakultasFKH, getContext().getResources().getString(R.string.fakultasFKH)));
        mFakultasArrayList.add(new Fakultas(14, R.drawable.ic_fkg, R.string.fakultasFKG, getContext().getResources().getString(R.string.fakultasFKG)));
        mFakultasArrayList.add(new Fakultas(15, R.drawable.ic_filkom3, R.string.fakultasFILKOM, getContext().getResources().getString(R.string.fakultasFILKOM)));
        mFakultasArrayList.add(new Fakultas(16, R.drawable.ic_vokasi2, R.string.fakultasVokasi, getContext().getResources().getString(R.string.fakultasVokasi)));
        mFakultasArrayList.add(new Fakultas(17, R.drawable.ic_ub_kediri, R.string.fakultasKediri, getContext().getResources().getString(R.string.fakultasKediri)));
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        Welcome3RecyclerViewAdapter adapter = new Welcome3RecyclerViewAdapter(
                getContext(),
                DummyContent.ITEMS,
                fakultasList,
                mFakultasArrayList,
                this
        );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Fakultas item, int position) {
        // save id ke shared pref
        int idFakultas = item.getId();
        writeToSharedPref(item);
    }

    private void writeToSharedPref(Fakultas fakultas) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                SHARED_PREF_NAME,
                Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPref.edit();

        String strSelectedfakultas = GSONConverter.fromObjectToString(fakultas);

        editor.putString(
                Constant.FAKULTAS_FAVORIT_KEY,
                strSelectedfakultas
        );
        editor.commit();

        Log.d(WELCOME_3_TAG,
                "writeToSharedPref: cabangOlahragaSetString: " + strSelectedfakultas.toString()
        );
    }


    // save ke shared pref : done
    private void writeToSharedPref(List<CabangOlahraga> selectedCaborFavoritList) {

        SharedPreferences sharedPref = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> cabangOlahragaSetString = GSONConverter.fromListToSetString(selectedCaborFavoritList);

        editor.putStringSet(Constant.FAKULTAS_FAVORIT_KEY, cabangOlahragaSetString);
        editor.commit();

    }
}
