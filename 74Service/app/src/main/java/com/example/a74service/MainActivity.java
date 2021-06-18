package com.example.a74service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Intent intent = null;
    static Handler uiHandler = null;

    TextView txtCount = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MyService.class); // giong het voi Activity
        uiHandler = new Handler();

        txtCount = findViewById(R.id.txtCount);
    }

    public void onStartService(View v) {
        startService(intent);
    }

    public void onStopService(View v) {
        stopService(intent);
    }

    // bind service la mot trong cac co che de cac ung dung khac nhau trao doi voi nhau
    // duoi day chi lay vi du ve bind service trong cung 1 ung dung

    ServiceConnection serviceConn = null;
    public void onBindService(View v) {
        serviceConn = new ServiceConnection() {
            MyService myService = null;

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) { // name se la ten package, service la gt tra ve cua p.thuc onBind() trong MyService
                myService = ((MyService.MyBinder) service).getService(); // lay tham chieu toi doi tuong MyService
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtCount.setText("MyService is still running: count = " + String.valueOf(myService.getCount()));
                            }
                        });
                    }
                }, 0, 5000); // timmer nay co the lech voi timmer cua MyService
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myService = null;
            }
        };

        Intent it = new Intent(this, MyService.class);
        bindService(it, serviceConn, BIND_AUTO_CREATE);
    }

    public void onUnbindService(View v) {
        if (serviceConn != null) {
            unbindService(serviceConn);
        }
    }

}