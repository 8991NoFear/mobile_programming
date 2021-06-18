package vn.binhld.androidme.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import vn.binhld.androidme.R;
import vn.binhld.androidme.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        // Only create new fragments when there is no previously saved state
        Intent it = getIntent();
        if (it != null && savedInstanceState == null) {
            Bundle bundle = it.getExtras();
            int headIndex = bundle.getInt(MainActivity.HEAD_INDEX);
            int bodyIndex = bundle.getInt(MainActivity.BODY_INDEX);
            int legIndex = bundle.getInt(MainActivity.LEG_INDEX);

            // head fragment
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(headIndex);

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.head_container, headFragment).commit();

            // body fragment
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setmListIndex(bodyIndex);

            fm.beginTransaction().add(R.id.body_container, bodyFragment).commit();

            // leg fragment
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setmImageIds(AndroidImageAssets.getLegs());
            legFragment.setmListIndex(legIndex);

            fm.beginTransaction().add(R.id.leg_container, legFragment).commit();
        }
    }
}