package com.raioncommunity.android.ob2017.ui.nav_menu.beranda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.raioncommunity.android.ob2017.R;

/**
 * Created by amira on 7/2/2017.
 */

public class BerandaFragment extends Fragment {

    public static BerandaFragment newInstance(){

        Bundle args = new Bundle();
        BerandaFragment fragment = new BerandaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beranda, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(getContext())
                .add(R.string.viewPagerYesterday, YesterdayFragment.class)
                .add(R.string.viewPagerToday, TodayFragment.class)
                .add(R.string.viewPagerTomorrow, TomorrowFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.jadwal_viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.tabLayout);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

}
