package com.example.a91sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivilegedExceptionAction;

public class MainActivity extends AppCompatActivity {
    TextView txtCount = null;

    String sharedPreferenceName = BuildConfig.APPLICATION_ID;
    SharedPreferences sharedPreferences = null;

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Toast t = Toast.makeText(MainActivity.this, key, Toast.LENGTH_SHORT);
            t.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCount = findViewById(R.id.txtCount);

        sharedPreferences = getSharedPreferences(sharedPreferenceName, MODE_PRIVATE);

        int color = sharedPreferences.getInt("COLOR", Integer.MAX_VALUE);
        Log.d("abc", String.valueOf(color));
        txtCount.setBackgroundColor(color);
        int count = sharedPreferences.getInt("COUNT", 0);
        Log.d("abc", String.valueOf(count));
        txtCount.setText(String.valueOf(count)); // txtCount.setText(count); --> an exception ResourceNotFound :((

        // dk bo listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onChangeColor(View v) {
        int color = ((ColorDrawable) v.getBackground()).getColor();
        txtCount.setBackgroundColor(color);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("COLOR", color);

        editor.apply(); // lam j thi lam cuoi cung phai apply
    }

    public void onCount(View v) {
        try {
            int current = Integer.valueOf(txtCount.getText().toString());
            txtCount.setText(String.valueOf(++current)); // chuyen thanh current++ thi deo hien thi luon

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("COUNT", current);

            editor.apply(); // lam j thi lam cuoi cung phai apply
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}