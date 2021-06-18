package com.example.a72intenet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    // format code: CTRL + ALT + L
    // duplicate code: CTRL + D

    TextView xmlView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xmlView = findViewById(R.id.xmlView);

        // Kiem tra ket noi intenet va loai ket noi thong qua wifi hay mang di dong
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean hasInternet = connMgr.getActiveNetworkInfo() != null && connMgr.getActiveNetworkInfo().isConnected();
        boolean isWifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
        boolean isMobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();

        String msg = null;
        if (hasInternet) {
            MyThread thread = new MyThread();
            thread.start();
            if (isWifi) {
                msg = "Download via Wifi";
            } else if (isMobile) {
                msg = "Download via Mobile";
            }
        } else {
            msg = "You don't have Internet";
        }
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    StringBuffer sb = null;
    BufferedReader br = null;
    class MyThread extends Thread {
        @Override
        public void run() {
            try {
                // B1. Tao http connection
                URL url = new URL("https://tuoitre.vn/rss/tin-moi-nhat.rss"); // rss cua vnexpress dinh exception o SSL handshake
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // B2. Set cac tham so cho connection
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                // B3. Ket noi thanh cong thi doc du lieu
                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    sb = new StringBuffer();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            xmlView.setText(sb.toString());
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}