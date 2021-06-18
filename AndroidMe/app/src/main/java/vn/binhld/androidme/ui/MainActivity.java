package vn.binhld.androidme.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import vn.binhld.androidme.R;
import vn.binhld.androidme.data.AndroidImageAssets;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    // Variables to store the values for the list index of the selected images
    // The default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    public static final String HEAD_INDEX = "headIndex";
    public static final String LEG_INDEX = "legIndex";
    public static final String BODY_INDEX = "bodyIndex";

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Determine if you're creating a two-pane or single-pane display
        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            if (savedInstanceState == null) {
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
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position / 12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position % 12;

        // Set the currently displayed item for the correct body part fragment
        switch (bodyPartNumber) {
            case 0:
                headIndex = listIndex;
                break;
            case 1:
                bodyIndex = listIndex;
                break;
            case 2:
                legIndex = listIndex;
                break;
            default:
                break;
        }

        if (mTwoPane) {
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(headIndex);

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.head_container, headFragment).commit();

            // body fragment
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setmListIndex(bodyIndex);

            fm.beginTransaction().replace(R.id.body_container, bodyFragment).commit();

            // leg fragment
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setmImageIds(AndroidImageAssets.getLegs());
            legFragment.setmListIndex(legIndex);

            fm.beginTransaction().replace(R.id.leg_container, legFragment).commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(HEAD_INDEX, headIndex);
            bundle.putInt(BODY_INDEX, bodyIndex);
            bundle.putInt(LEG_INDEX, legIndex);

            Intent it = new Intent(this, AndroidMeActivity.class);
            it.putExtras(bundle);

            Button button = findViewById(R.id.next_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(it);
                }
            });
        }
    }
}