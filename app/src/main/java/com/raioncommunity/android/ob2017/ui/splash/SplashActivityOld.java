package com.raioncommunity.android.ob2017.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.raioncommunity.android.ob2017.R;
import com.raioncommunity.android.ob2017.model.helper.OBDatabaseHelper;
import com.raioncommunity.android.ob2017.ui.main.MainActivity;
import com.raioncommunity.android.ob2017.ui.welcome.WelcomeActivity;


/**
 * Created by amira on 6/24/2017.
 */

/*
* Modified by arifinfirdaus
* */

public class SplashActivityOld extends Activity {


    private static int splashInterval = 1500;
    private OBDatabaseHelper mObDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        mObDatabaseHelper = new OBDatabaseHelper(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                boolean isSuccessUpdateCounter = mObDatabaseHelper.updateCounter();

                if (isSuccessUpdateCounter) {

                    int appRunCounter = mObDatabaseHelper.getTableCount(OBDatabaseHelper.TABLE_APP);

                    // jika baris yg dihasilkan <= 1
                    // maksudnya kalau pertama kali run
                    if (appRunCounter <= 1) {
                        navigateToWelcomeActivity();
                    } else {
                        navigateToMainActivitiy();
                    }
                }
                this.finish();
            }

            private void finish() {

            }
        }, splashInterval);

    }

    private void navigateToWelcomeActivity() {
        Intent intent = new Intent(SplashActivityOld.this, WelcomeActivity.class);
        startActivity(intent);
    }

    private void navigateToMainActivitiy() {
        Intent intent = new Intent(SplashActivityOld.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        int result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(SplashActivityOld.this);
        if (result == ConnectionResult.SUCCESS) {

        } else if (result == ConnectionResult.SERVICE_MISSING || result == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ||
                result == ConnectionResult.SERVICE_DISABLED) {
            GoogleApiAvailability.getInstance().getErrorDialog(SplashActivityOld.this, result, 99).show();
        }
    }
}
