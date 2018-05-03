package com.raioncommunity.android.ob2017.ui.welcome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.animation.TranslateScaleAnimation;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.raioncommunity.android.ob2017.ui.main.MainActivity;
import com.raioncommunity.android.ob2017.ui.welcome.welcome_2.Welcome2Fragment;
import com.raioncommunity.android.ob2017.ui.welcome.welcome_3.Welcome3Fragment;


public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private FloatingActionButton btnNext;
    TranslateScaleAnimation animation;

    private static int splashInterval = 1500 * 2;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.welcome_viewPager);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        InkPageIndicator inkPageIndicator = (InkPageIndicator) findViewById(R.id.welcome_indicator);
        inkPageIndicator.setViewPager(viewPager);

        RelativeLayout welcomeSource = (RelativeLayout) findViewById(R.id.welcome_source);
        View welcomeDestination = findViewById(R.id.welcome_destination);

        animation = new TranslateScaleAnimation(welcomeSource, welcomeDestination);

        btnNext = (FloatingActionButton) findViewById(R.id.welcome_fab);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareNavigationToMainActivity();
            }
        });

    }


    private void prepareNavigationToMainActivity() {
        showProgressDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                this.finish();
            }

            private void finish() {
                hideProgressDialog();
                navigateToMainActivity();
                Toast.makeText(WelcomeActivity.this, "Data tersimpan. Anda dapat mengubah dalam menu settings.", Toast.LENGTH_SHORT).show();
            }
        }, splashInterval);
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            animation.update();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        float currentTime = position + positionOffset;
        if (currentTime > 1f) {
            currentTime = 1f;
        }
        if (animation != null) animation.setCurrentTimeInAllAnimators(currentTime);
    }

    @Override
    public void onPageSelected(int position) {
        if (position > 1) {
            position = 1;
        }
        if (animation != null) animation.setCurrentTimeInAllAnimators(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class MyViewPagerAdapter extends FragmentPagerAdapter {

        public static final int NUM_ITEMS = 3;

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Welcome1Fragment.newInstance();
                case 1:
                    return Welcome2Fragment.newInstance();
                case 2:
                    return Welcome3Fragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

}
