package com.hegazy.myshop.data.api.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.hegazy.myshop.R;


public class AlarmService extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Create the content intent for the notification, which launches this activity
        Intent contentIntent = new Intent(context, MyFirebaseMessagingService.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logoo)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.app_name))
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //Deliver the notification
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}
