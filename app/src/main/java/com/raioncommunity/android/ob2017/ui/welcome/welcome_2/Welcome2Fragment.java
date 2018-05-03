package com.raioncommunity.android.ob2017.ui.welcome.welcome_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.model.dummy.DummyContent;
import com.raioncommunity.android.ob2017.model.view.CabangOlahraga;
import com.raioncommunity.android.ob2017.model.view.CaborWelcome;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.util.Constant;
import com.raioncommunity.android.ob2017.util.GSONConverter;
import com.raioncommunity.android.ob2017.util.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.raioncommunity.android.ob2017.debug.Tag.GSON_CONVERTER_TAG;
import static com.raioncommunity.android.ob2017.debug.Tag.Welcome2CallbackTag;
import static com.raioncommunity.android.ob2017.debug.Tag.Welcome2IndicatorTag;
import static com.raioncommunity.android.ob2017.util.Constant.SHARED_PREF_NAME;


/**
 * Created by amira on 6/25/2017.
 */

/**
 * Modified by arifinfirdaus
 */

public class Welcome2Fragment extends Fragment implements Welcome2FragmentCallback {

    public static Welcome2Fragment newInstance() {

        Bundle args = new Bundle();
        Welcome2Fragment fragment = new Welcome2Fragment();
        fragment.setArguments(args);
        return fragment;

    }

    private RecyclerView mRecyclerView;
    private List<CaborWelcome> mCaborListOld;

    private Welcome2RecyclerViewAdapter mWelcome2Adapter;
    // private List<CabangOlahraga> mSelectedCaborFavoritList;
    private List<CabangOlahraga> mSelectedCaborFavoritList;

    private List<CabangOlahraga> mCaborList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_welcome_page2, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//        Set<String> defaultValueStringSet = new HashSet<>();
//        Set<String> favoritStringSet = sharedPref.getStringSet(Constant.CABOR_FAVORIT_KEY, defaultValueStringSet);
//
//        mSelectedCaborFavoritList = GSONConverter.fromSetStringToList(favoritStringSet, CabangOlahraga.class);

        if (mSelectedCaborFavoritList == null) {
            mSelectedCaborFavoritList = new ArrayList<>();
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.page2_recyclerView);
        mCaborListOld = new ArrayList<>();

        prepareCaborData();

        setupRecyclerView(mRecyclerView);

        loadCurrentUserCaborList();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.addItemDecoration(new ItemOffsetDecoration(getContext(), R.dimen.itemOffset));
        mWelcome2Adapter = new Welcome2RecyclerViewAdapter(DummyContent.ITEMS, mCaborListOld, getContext(), this, mCaborList);
        recyclerView.setAdapter(mWelcome2Adapter);
    }

    @Override
    public void onStop() {
        super.onStop();

        // save cabang olahraga favorit
        writeToSharedPref(mSelectedCaborFavoritList);
    }

    // save ke shared pref : done
    private void writeToSharedPref(List<CabangOlahraga> selectedCaborFavoritList) {

        SharedPreferences sharedPref = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> cabangOlahragaSetString = GSONConverter.fromListToSetString(selectedCaborFavoritList);

        editor.putStringSet(Constant.CABOR_FAVORIT_KEY, cabangOlahragaSetString);
        editor.commit();

        Log.d(Welcome2CallbackTag, "writeToSharedPref: cabangOlahragaSetString: " + cabangOlahragaSetString.toString());
    }


    // MARK: - Welcome2FragmentCallback

    @Override
    public void onItemClick(CabangOlahraga item, int position) {
        // refresh ui

        mSelectedCaborFavoritList.add(item);
        mWelcome2Adapter.updateData(mSelectedCaborFavoritList);
        mWelcome2Adapter.notifyDataSetChanged();
//
        if (mSelectedCaborFavoritList == null) {
            Log.d(Welcome2CallbackTag, "onItemClick: mSelectedCaborFavoritList == null: ");
        }
        Log.d(Welcome2CallbackTag, "onItemClick: item: " + GSONConverter.fromObjectToString(item));
    }

    private void prepareCaborData() {

        mCaborList = new ArrayList<>();

        mCaborList.add(new CabangOlahraga(1, R.drawable.ic_action_atletik, R.string.caborAtletik, getResources().getString(R.string.caborAtletik), false));
        mCaborList.add(new CabangOlahraga(2, R.drawable.ic_action_bulu_tangkis, R.string.caborBuluTangkis, getResources().getString(R.string.caborBuluTangkis), false));
        mCaborList.add(new CabangOlahraga(3, R.drawable.ic_action_basket, R.string.caborBasket, getResources().getString(R.string.caborBasket), false));

        mCaborList.add(new CabangOlahraga(4, R.drawable.ic_action_bridge, R.string.caborBridge, getResources().getString(R.string.caborBridge), false));
        mCaborList.add(new CabangOlahraga(5, R.drawable.ic_action_catur, R.string.caborCatur, getResources().getString(R.string.caborCatur), false));
        mCaborList.add(new CabangOlahraga(6, R.drawable.ic_action_debat_indonesia, R.string.caborDebatIndonesia, getResources().getString(R.string.caborDebatIndonesia), false));

        mCaborList.add(new CabangOlahraga(7, R.drawable.ic_action_debat_inggris, R.string.caborDebatInggris, getResources().getString(R.string.caborDebatInggris), false));
        mCaborList.add(new CabangOlahraga(8, R.drawable.ic_action_desain_poster, R.string.caborDesainPoster, getResources().getString(R.string.caborDesainPoster), false));
        mCaborList.add(new CabangOlahraga(9, R.drawable.ic_action_fotografi, R.string.caborFestivalBand, getResources().getString(R.string.caborFestivalBand), false));

        mCaborList.add(new CabangOlahraga(10, R.drawable.ic_action_fotografi, R.string.caborFotografi, getResources().getString(R.string.caborFotografi), false));
        mCaborList.add(new CabangOlahraga(11, R.drawable.ic_action_futsal, R.string.caborFutsal, getResources().getString(R.string.caborFutsal), false));
        mCaborList.add(new CabangOlahraga(12, R.drawable.ic_action_karate, R.string.caborKarate, getResources().getString(R.string.caborKarate), false));

        mCaborList.add(new CabangOlahraga(13, R.drawable.ic_action_kempo, R.string.caborKempo, getResources().getString(R.string.caborKempo), false));
        mCaborList.add(new CabangOlahraga(14, R.drawable.ic_action_komik, R.string.caborKomik, getResources().getString(R.string.caborKomik), false));
        mCaborList.add(new CabangOlahraga(15, R.drawable.ic_action_menembak, R.string.caborMenembak, getResources().getString(R.string.caborMenembak), false));

        mCaborList.add(new CabangOlahraga(16, R.drawable.ic_action_menyanyi_dangdut, R.string.caborMenyanyiDangdut, getResources().getString(R.string.caborMenyanyiDangdut), false));
        mCaborList.add(new CabangOlahraga(17, R.drawable.ic_action_menyanyi_keroncong, R.string.caborMenyanyiKeroncong, getResources().getString(R.string.caborMenyanyiKeroncong), false));
        mCaborList.add(new CabangOlahraga(18, R.drawable.ic_action_menyanyi_pop, R.string.caborMenyanyiPop, getResources().getString(R.string.caborMenyanyiPop), false));

        mCaborList.add(new CabangOlahraga(19, R.drawable.ic_action_menyanyi_seriosa, R.string.caborMenyanyiSeriosa, getResources().getString(R.string.caborMenyanyiSeriosa), false));
        mCaborList.add(new CabangOlahraga(20, R.drawable.ic_action_newscasting, R.string.caborNewscasting, getResources().getString(R.string.caborNewscasting), false));
        mCaborList.add(new CabangOlahraga(21, R.drawable.ic_action_vocal_grup, R.string.caborPaduanSuara, getResources().getString(R.string.caborPaduanSuara), false)); // double sama id 34

        mCaborList.add(new CabangOlahraga(22, R.drawable.ic_action_panahan, R.string.caborPanahan, getResources().getString(R.string.caborPanahan), false));
        mCaborList.add(new CabangOlahraga(23, R.drawable.ic_action_pencak_silat, R.string.caborPencakSilat, getResources().getString(R.string.caborPencakSilat), false));
        mCaborList.add(new CabangOlahraga(24, R.drawable.ic_action_pidato, R.string.caborPidato, getResources().getString(R.string.caborPidato), false));

        mCaborList.add(new CabangOlahraga(25, R.drawable.ic_action_puisi, R.string.caborPuisi, getResources().getString(R.string.caborPuisi), false));
        mCaborList.add(new CabangOlahraga(26, R.drawable.ic_action_recycle, R.string.caborRecycle, getResources().getString(R.string.caborRecycle), false));
        mCaborList.add(new CabangOlahraga(27, R.drawable.ic_action_renang, R.string.caborRenang, getResources().getString(R.string.caborRenang), false));

        mCaborList.add(new CabangOlahraga(28, R.drawable.ic_action_sinematografi, R.string.caborSinematografi, getResources().getString(R.string.caborSinematografi), false));
        mCaborList.add(new CabangOlahraga(29, R.drawable.ic_action_monolog_stand_up_comedy, R.string.caborMonologStandUpComedy, getResources().getString(R.string.caborMonologStandUpComedy), false));
        mCaborList.add(new CabangOlahraga(30, R.drawable.ic_action_taekwondo, R.string.caborTaekwondo, getResources().getString(R.string.caborTaekwondo), false));

        mCaborList.add(new CabangOlahraga(31, R.drawable.ic_action_tennis_lapangan, R.string.caborTennisLapangan, getResources().getString(R.string.caborTennisLapangan), false));
        mCaborList.add(new CabangOlahraga(32, R.drawable.ic_action_tennis_meja, R.string.caborTennisMeja, getResources().getString(R.string.caborTennisMeja), false));
        mCaborList.add(new CabangOlahraga(33, R.drawable.ic_action_vocal_grup, R.string.caborVocalGrup, getResources().getString(R.string.caborVocalGrup), false));

        mCaborList.add(new CabangOlahraga(34, R.drawable.ic_action_voli, R.string.caborVoli, getResources().getString(R.string.caborVoli), false));
    }

    private void loadCurrentUserCaborList() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Set<String> defaultValueStringSet = new HashSet<>();
        Set<String> favoritStringSet = sharedPref.getStringSet(Constant.CABOR_FAVORIT_KEY, defaultValueStringSet);

        // update data list
        mSelectedCaborFavoritList = GSONConverter.fromSetStringToList(favoritStringSet, CabangOlahraga.class);
        mWelcome2Adapter.updateData(mSelectedCaborFavoritList);
        mWelcome2Adapter.notifyDataSetChanged();

        Log.d(
                Welcome2IndicatorTag,
                "loadCurrentUserCaborList: mSelectedCaborFavoritList: " + GSONConverter.fromListToSetString(mSelectedCaborFavoritList)
        );
    }
}
