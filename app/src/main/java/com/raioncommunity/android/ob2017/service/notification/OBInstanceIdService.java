package com.raioncommunity.android.ob2017.service.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.raioncommunity.android.ob2017.debug.Tag.FCM_TAG;

/**
 * Created by bradhawk on 9/30/2016.
 */

/**
 * modified by arifinfirdaus
 */


public class OBInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(FCM_TAG, "Refreshed token: " + refreshedToken);


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // sendRegistrationToServer(refreshedToken);

    }
}
