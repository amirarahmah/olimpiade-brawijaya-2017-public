package com.raioncommunity.android.ob2017.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.raioncommunity.android.ob2017.ui.main.MainActivity;
import com.raioncommunity.android.ob2017.ui.welcome.WelcomeActivity;
import com.raioncommunity.android.ob2017.util.Constant;

import jonathanfinerty.once.Once;

/**
 * Created by bradhawk on 9/25/2016.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent nextIntent;
        if (!Once.beenDone(Constant.WELCOME_TOUR_KEY)) {
            nextIntent = new Intent(SplashActivity.this, WelcomeActivity.class);
        } else {
            nextIntent = new Intent(SplashActivity.this, MainActivity.class);
        }

        startActivity(nextIntent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        int result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(SplashActivity.this);
        if (result == ConnectionResult.SUCCESS) {

        } else if (result == ConnectionResult.SERVICE_MISSING || result == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ||
                result == ConnectionResult.SERVICE_DISABLED) {
            GoogleApiAvailability.getInstance().getErrorDialog(SplashActivity.this, result, 99).show();
        }
    }
}
