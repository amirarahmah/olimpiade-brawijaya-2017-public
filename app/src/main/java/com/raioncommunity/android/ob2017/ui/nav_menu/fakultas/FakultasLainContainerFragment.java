package com.raioncommunity.android.ob2017.ui.nav_menu.fakultas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.raioncommunity.android.ob2017.R;

/**
 * Created by amira on 7/6/2017.
 */

public class FakultasLainContainerFragment extends Fragment {

    public static final String FAKULTAS = "FAKULTAS";

    public static FakultasLainContainerFragment newInstance(int fakultasId) {

        Bundle args = new Bundle();
        args.putInt(FAKULTAS, fakultasId);

        FakultasLainContainerFragment fragment = new FakultasLainContainerFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cabang_olahraga_container, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(getContext())
                .add(R.string.viewPagerResult, FakultasResultFragment.class, getArguments())
                .add(R.string.viewPagerUpcoming, FakultasUpcomingFragment.class, getArguments())
                .create());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.container_viewPager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.container_tabLayout);
        viewPagerTab.setViewPager(viewPager);

    }

}
