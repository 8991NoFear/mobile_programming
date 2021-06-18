package com.example.a92settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtCount = null;

    SharedPreferences sharedPreferences = null;

    SharedPreferences.OnSharedPreferenceChangeListener changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            String strColor = sharedPreferences.getString("COLOR", "0");
            int color = Color.parseColor(strColor);
            txtCount.setBackgroundColor(color);
            String strCount = sharedPreferences.getString("COUNT", "0");
            txtCount.setText(strCount);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCount = findViewById(R.id.txtCount);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();

        String strColor = sharedPreferences.getString("COLOR", "#000000");
        int color = Color.parseColor(strColor);
        txtCount.setBackgroundColor(color);
        String strCount = sharedPreferences.getString("COUNT", "0");
        txtCount.setText(strCount);

        // dk bo listener
         sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener);
    }

    public void onChangeColor(View v) {
        int intColor = ((ColorDrawable) v.getBackground()).getColor();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String strColor = String.format("#%06X", 0xFFFFFF & intColor);
        editor.putString("COLOR", strColor);

        editor.apply(); // lam j thi lam cuoi cung phai apply
    }

    public void onCount(View v) {
        try {
            int current = Integer.valueOf(txtCount.getText().toString());
            ++current;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("COUNT", String.valueOf(current));

            editor.apply(); // lam j thi lam cuoi cung phai apply
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuSettings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }
}