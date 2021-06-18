package com.example.a81notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    private NotificationManager manager = null;
    private NotificationChannel channel = null;

    String channelId = "My Notification Channel Identifier";
    String channelName = "My Notification Channel Name";
    int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // >= android 8 phai co channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel); // lien ket NotificationChannel voi NotificationManager
        }
    }

    public void onNotify(View v) {
        // tao intent cho tap
        Intent tapItent = new Intent(this, MainActivity.class);
        PendingIntent tapPendingIntent = PendingIntent.getActivity(this, notificationId, tapItent, PendingIntent.FLAG_UPDATE_CURRENT);

        // tao 1 intent cho button
        Intent buttonItent = new Intent();
        buttonItent.setAction(Intent.ACTION_GET_CONTENT);
        buttonItent.setType("image/*");
        PendingIntent buttonPendingIntent = PendingIntent.getActivity(this, notificationId, buttonItent, PendingIntent.FLAG_UPDATE_CURRENT);

        //  tao BigPictureStyle
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        bigPictureStyle.bigPicture(bm);
        // bigPictureStyle.setBigContentTitle("and dep ve lo");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        builder.setSmallIcon(R.drawable.ic_notification); // bat buoc phai co icon neu khong se crash
        builder.setContentTitle("chu y");
        builder.setContentText("da download xong");
        builder.setContentIntent(tapPendingIntent); // tap action
        builder.addAction(R.drawable.ic_photo, "open photo", buttonPendingIntent); // action button
        builder.setStyle(bigPictureStyle); // set BigPictureStyle
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        manager.notify(notificationId, notification);
    }

    public void onUpdate(View v) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentTitle("chu y");
        builder.setContentText("da update noi dung");
        Notification notification = builder.build();
        manager.notify(notificationId, notification); // cung id nen se update or tao moi
    }

    public void onCancel(View v) {
        manager.cancel(notificationId);
//        manager.cancelAll();
    }
}