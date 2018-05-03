package com.raioncommunity.android.ob2017.ui.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raioncommunity.android.ob2017.R;

/**
 * Created by amira on 6/25/2017.
 */

public class Welcome1Fragment extends Fragment {

    public static Welcome1Fragment newInstance(){

        Bundle args = new Bundle();
        Welcome1Fragment fragment = new Welcome1Fragment();
        fragment.setArguments(args);
        return fragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_welcome_page1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}