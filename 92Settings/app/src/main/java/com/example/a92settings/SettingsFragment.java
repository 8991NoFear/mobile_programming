package com.example.a92settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends PreferenceFragmentCompat {
    SharedPreferences sharedPreferences = null;

    SharedPreferences.OnSharedPreferenceChangeListener changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("COUNT")) {
                String newCount = sharedPreferences.getString("COUNT", "0");
                findPreference("COUNT").setSummary(newCount); // tuong ung voi findViewById
            } else if (key.equals("COLOR")) {
                String newColorValue = sharedPreferences.getString("COLOR", "#000000");
                int index = ((ListPreference) findPreference("COLOR")).findIndexOfValue(newColorValue);
                String entryName = (String) ((ListPreference) findPreference("COLOR")).getEntries()[index];
                ((ListPreference) findPreference("COLOR")).setSummary(entryName);
            }
        }
    };

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener);

        String currentCount = sharedPreferences.getString("COUNT", "0");
        changeListener.onSharedPreferenceChanged(sharedPreferences, "COUNT"); // chu dong kich hoat khi lan dau chay app
        changeListener.onSharedPreferenceChanged(sharedPreferences, "COLOR");
    }
}