package com.navis.appsettingsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textCount = null;
    SharedPreferences sharedPreferences = null;

    SharedPreferences.OnSharedPreferenceChangeListener changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if (s.equals("COLOR")) {
                String sColor = sharedPreferences.getString("COLOR", "#000000");
                int color = Color.parseColor(sColor);
                textCount.setBackgroundColor(color);
            }
            if (s.equals("COUNT")) {
                String sCount = sharedPreferences.getString("COUNT", "0");
                textCount.setText(sCount);
            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSettings)
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textCount = findViewById(R.id.textCount);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener);
        String sColor = sharedPreferences.getString("COLOR", "#000000");
        int color = Color.parseColor(sColor);
        textCount.setBackgroundColor(color);
        String sCount = sharedPreferences.getString("COUNT", "0");
        textCount.setText(sCount);
    }
    public void onChangeColor(View v)
    {
        int color = ((ColorDrawable)v.getBackground()).getColor();
        textCount.setBackgroundColor(color);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String sColor = String.format("#%06X",0xFFFFFF & color);
        editor.putString("COLOR", sColor);
        editor.apply();
    }
    public void onCount(View v)
    {
        int current = Integer.parseInt((String)textCount.getText());
        textCount.setText(Integer.toString(current + 1));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("COUNT",  Integer.toString(current + 1));
        editor.apply();
    }
}
