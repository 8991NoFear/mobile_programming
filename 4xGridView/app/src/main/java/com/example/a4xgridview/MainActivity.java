package com.example.a4xgridview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<MyItemData> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            long id = i;
            String title = "figure " + String.valueOf(i);
            Uri figureUri = Uri.parse("android.resource://com.example.a4xgridview/drawable/pikachu" + String.valueOf(i));
            data.add(new MyItemData(id, title, figureUri));
        }

        MyAdapter adapter = new MyAdapter(data);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }
}