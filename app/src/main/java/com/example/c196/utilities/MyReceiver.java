package com.example.c196.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.c196.R;

import static com.example.c196.utilities.Constants.CHANNEL_ID;
import static com.example.c196.utilities.Constants.CHANNEL_NAME;
import static com.example.c196.utilities.Constants.NOTIFICATION_TEXT;
import static com.example.c196.utilities.Constants.NOTIFICATION_TITLE;

public class MyReceiver extends BroadcastReceiver {
    static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("key"),Toast.LENGTH_LONG).show();
        createNotificationChannel(context, CHANNEL_ID);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_foreground)
                .setContentText(intent.getStringExtra(NOTIFICATION_TEXT))
                .setContentTitle(intent.getStringExtra(NOTIFICATION_TITLE))
                .build();

        NotificationManager notificationManager = (NotificationManager)context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(notificationID++,notification);
    }

    private void createNotificationChannel(Context context, String channelId) {
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }
}
