package com.example.irregulartab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout = null;
    View tab1 = null; // de la View vi findViewById khong cast ve TabLayout.Tab duoc
    View tab2 = null;
    View tab3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Xu ly tren TabLayout.Tab
        tab1 = findViewById(R.id.layoutTab1);
        tab2 = findViewById(R.id.layoutTab2);
        tab3 = findViewById(R.id.layoutTab3);



        // Xu ly tren TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3")); // khong dung new Tab() ma dung tabLayout de tao Tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//              Cach tiep can khong chinh thong nhung rat la don gian
                if (tab.getText().equals("Tab 1")) {
                    tab1.setVisibility(View.VISIBLE);
                    tab2.setVisibility(View.INVISIBLE);
                    tab3.setVisibility(View.INVISIBLE);
                } else if (tab.getText().equals("Tab 2")) {
                    tab1.setVisibility(View.INVISIBLE);
                    tab2.setVisibility(View.VISIBLE);
                    tab3.setVisibility(View.INVISIBLE);
                } else {
                    tab1.setVisibility(View.INVISIBLE);
                    tab2.setVisibility(View.INVISIBLE);
                    tab3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }
}