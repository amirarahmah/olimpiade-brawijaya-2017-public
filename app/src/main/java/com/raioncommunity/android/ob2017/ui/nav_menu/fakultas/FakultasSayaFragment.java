package com.raioncommunity.android.ob2017.ui.nav_menu.fakultas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.view.Fakultas;
import com.raioncommunity.android.ob2017.util.Constant;
import com.raioncommunity.android.ob2017.util.GSONConverter;

import static com.raioncommunity.android.ob2017.debug.Tag.FAK_RESULT_TAG;
import static com.raioncommunity.android.ob2017.util.Constant.SHARED_PREF_NAME;

/**
 * Created by amira on 7/4/2017.
 */

public class FakultasSayaFragment extends Fragment {

    public static final String fakultasKey = "FAKULTAS";

    public static FakultasSayaFragment newInstance() {

        Bundle args = new Bundle();
        FakultasSayaFragment fragment = new FakultasSayaFragment();
        fragment.setArguments(args);
        return fragment;

    }

    private int mFakultasId;
    private Fakultas mSelectedFakultas;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fakultas_saya, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadSharedPrefFakultasSaya();

        mFakultasId = mSelectedFakultas.getId();

        Bundle args = new Bundle();
        args.putInt(fakultasKey, mFakultasId);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(getContext())
                .add(R.string.viewPagerResult, FakultasResultFragment.class, args)
                .add(R.string.viewPagerUpcoming, FakultasUpcomingFragment.class, args)
                .create());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fakultasSaya_viewPager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.fakultasSaya_tabLayout);
        viewPagerTab.setViewPager(viewPager);

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
