package com.raioncommunity.android.ob2017.service.notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.raioncommunity.android.ob2017.R;

import static com.raioncommunity.android.ob2017.debug.Tag.FCM_TAG;

/**
 * Created by bradhawk on 9/30/2016.
 */

/**
 * modified by arifinfirdaus
 */

// onMessageReceived bakal ke trigger kalo app lagi running, tapi nggk ada notifikasi
// klao di background, onMessageReceived nggk ke trigger, tapi ada notifikasi
// klao app off, onMessageReceived nggk ke trigger, tapi ada notifikasi

public class OBMessageService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(FCM_TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(FCM_TAG, "Message data payload: " + remoteMessage.getData());
            String value0 = remoteMessage.getData().get("key0");
            Log.d(FCM_TAG, "onMessageReceived: value0: " + value0);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(FCM_TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            String messageText = remoteMessage.getNotification().getBody();
            Log.d(FCM_TAG, "onMessageReceived: messageText: " + messageText);
            handleNow(messageText);
        }

    }


    // MARK : - handle notification message now
    private void handleNow(String messageText) {
        Log.d(FCM_TAG, "handleNow");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_tobedetermined_icon)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setDefaults(Notification.DEFAULT_SOUND);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(uri);
        mBuilder.build();
    }

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();

    }
}
