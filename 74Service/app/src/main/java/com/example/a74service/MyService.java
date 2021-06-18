package com.example.a74service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    Timer timer = null;
    private int count = 0;

    int notificationId = 1;
    String channelId = "My Notification Channel Identifier";
    String channelName = "My Notification Channel Name";

    NotificationCompat.Builder builder = null;
    NotificationChannel channel = null;
    NotificationManager manager = null;

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public int getCount() {
        return  count;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        // tu day ...
        Toast t = Toast.makeText(this, "service started", Toast.LENGTH_SHORT);
        t.show();

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        Intent tapIt = new Intent(this, MainActivity.class);
        PendingIntent penTapIt = PendingIntent.getActivity(this, 0, tapIt, 0);
        builder = new NotificationCompat.Builder(this, channelId);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("MyService is running!");
        builder.setContentText("Count: 0");
        builder.setContentIntent(penTapIt);
        Notification notification = builder.build();
        // ... den day la dang chay o che do background

        // tu day den het chuyen len chay o che do foreground --> ko bi android system kill
        startForeground(notificationId, notification);

        timer = new Timer();
        TimerTask serviceTask = new TimerTask() {
            @Override
            public void run() {
                count++;
                MainActivity.uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast t = Toast.makeText(MyService.this, "service is running: count = " + String.valueOf(count), Toast.LENGTH_SHORT);
                        t.show();
                        builder.setContentText("count: " + String.valueOf(count));
                        Notification ntf = builder.build();
                        manager.notify(notificationId, ntf);
                    }
                });
            }
        };
        timer.schedule(serviceTask,0, 5000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // WARNING: timmer chay tren luong khac service day --> service dung nhung timer chua chac dung dau
        if (timer != null) {
            timer.cancel();
        }
        Toast t = Toast.makeText(this, "service stopped", Toast.LENGTH_SHORT);
        t.show();
        super.onDestroy();
    }
}
