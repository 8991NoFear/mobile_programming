package com.example.a74serviceandinternet;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;

import androidx.core.app.NotificationCompat;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<Void, String, Void> {
    MyService service = null;

    int notificationId;

    NotificationChannel channel = null;
    NotificationManager manager = null;
    NotificationCompat.Builder builder = null;

    public MyAsyncTask(MyService service) {
        this.service = service;

        notificationId = service.notificationId;
        manager = service.manager;
        builder = service.builder;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://www.sample-videos.com/text/Sample-text-file-500kb.txt");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                OutputStream os = new FileOutputStream(service.getFilesDir() + "/sample.txt"); // cha biet no luu file o dau luon
                // int lenghtOfFile = conn.getContentLength(); // --> cha hieu sao cu tra ve -1
                byte data[] = new byte[1024];
                int total = 0;
                int count = 0;
                while ((count = is.read(data)) != -1) {
                    total += count;
                    publishProgress(String.valueOf((int) total/1024));
                    os.write(data, 0, count);
                }
                os.flush();
                os.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        builder.setContentText("downloading: " + values[0] + "KB");
        Notification notification = builder.build();
        manager.notify(notificationId, notification);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        builder.setContentText("download complete");
        Notification notification = builder.build();
        manager.notify(notificationId, notification);

        service.stopSelf();
        super.onPostExecute(aVoid);
    }
}