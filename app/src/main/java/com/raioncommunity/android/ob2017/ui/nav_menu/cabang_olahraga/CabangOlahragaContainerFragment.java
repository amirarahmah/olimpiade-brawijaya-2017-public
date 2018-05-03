package com.raioncommunity.android.ob2017.ui.nav_menu.cabang_olahraga;

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

public class CabangOlahragaContainerFragment extends Fragment {

    public static final String CABOR_ID_KEY = "CaborId";
    public static final String CABOR_NAME_KEY = "CaborName";

    public static CabangOlahragaContainerFragment newInstance(int caborId, String caborName) {

        Bundle args = new Bundle();
        args.putInt(CABOR_ID_KEY, caborId);
        args.putString(CABOR_NAME_KEY, caborName);

        CabangOlahragaContainerFragment fragment = new CabangOlahragaContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cabang_olahraga_container, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(getContext())
                .add(R.string.viewPagerResult, ResultFragment.class, getArguments())
                .add(R.string.viewPagerUpcoming, UpcomingFragment.class, getArguments())
                .create());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.container_viewPager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.container_tabLayout);
        viewPagerTab.setViewPager(viewPager);

    }
}
