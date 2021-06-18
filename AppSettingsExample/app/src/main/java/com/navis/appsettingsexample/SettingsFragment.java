package com.navis.appsettingsexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceRecyclerViewAccessibilityDelegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends PreferenceFragmentCompat {
    SharedPreferences sharedPreferences = null;
    SharedPreferences.OnSharedPreferenceChangeListener changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if (s.equals("COUNT"))
            {
                String newCount = sharedPreferences.getString("COUNT", "0");
                findPreference("COUNT").setSummary(newCount);
            }
            if (s.equals("COLOR"))
            {
                String newColor = sharedPreferences.getString("COLOR", "#000000");
                int i = ((ListPreference)findPreference("COLOR")).findIndexOfValue(newColor);
                String entry = (String)((ListPreference)findPreference("COLOR")).getEntries()[i];
                ((ListPreference)findPreference("COLOR")).setSummary(entry);
            }
        }
    };

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener);
        changeListener.onSharedPreferenceChanged(sharedPreferences, "COUNT");
        changeListener.onSharedPreferenceChanged(sharedPreferences, "COLOR");
    }
}
