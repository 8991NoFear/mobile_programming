package com.example.my4thapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity4Intent2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_intent2);
    }

    public void onIntent1(View v) {
        Intent it = new Intent(this, Activity4Intent1.class);
        startActivity(it);
    }

    public void onIntent3(View v) {
        Intent it = new Intent(this, Activity4Intent3.class);
        startActivity(it);
    }
}