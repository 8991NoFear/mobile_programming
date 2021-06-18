package vn.binhld.androidsquawker.following;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import vn.binhld.androidsquawker.R;

/**
 * Shows the list of instructors you can follow
 */
public class FollowingPreferenceFragment extends PreferenceFragment {

    private final static String LOG_TAG = FollowingPreferenceFragment.class.getSimpleName();

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Add visualizer preferences, defined in the XML file in res->xml->preferences_squawker
        addPreferencesFromResource(R.xml.following_squawker);
    }
}

