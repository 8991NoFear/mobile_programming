
package com.example.a74serviceandinternet;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    String channelId = "My Notification Channel Id";
    String channelName = "My Notification Channel Name";
    int notificationId = 1; // phai != 0

    NotificationChannel channel = null;
    NotificationManager manager = null;
    NotificationCompat.Builder builder = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE);
            manager.createNotificationChannel(channel);
        }
        builder = new NotificationCompat.Builder(this, channelId);
        builder.setSmallIcon(R.drawable.ic_download); // phai co icon va icon phai khac icon launcher
        builder.setContentTitle("MyService is running!!!");
        builder.setContentText("downloading: 0KB");
        Notification notification = builder.build();
        try {
            startForeground(notificationId, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MyAsyncTask downloadTask = new MyAsyncTask(this);
        downloadTask.execute();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
