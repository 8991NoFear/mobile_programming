package com.example.a73broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String CUSTOM_ACTION = BuildConfig.APPLICATION_ID + ".customaction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // register System Broadcast
        MyReceiver receiver = new MyReceiver();
        IntentFilter systemFilter = new IntentFilter();
        systemFilter.addAction(Intent.ACTION_SCREEN_ON);
        systemFilter.addAction(Intent.ACTION_SCREEN_OFF);
        this.registerReceiver(receiver, systemFilter);

        // register Custom Broadcast
        IntentFilter customfilter = new IntentFilter();
        customfilter.addAction(CUSTOM_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, customfilter);
    }


    public void sendLocalBroadcast(View v) {
       Intent intent = new Intent();
       intent.setAction(CUSTOM_ACTION);
       LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}