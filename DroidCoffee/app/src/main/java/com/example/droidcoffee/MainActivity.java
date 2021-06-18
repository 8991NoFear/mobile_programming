package com.example.droidcoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String itemSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView donutImage = findViewById(R.id.donutImage);
        donutImage.setOnTouchListener(imageTouchListener);
        ImageView froyoImage = findViewById(R.id.froyoImage);
        froyoImage.setOnTouchListener(imageTouchListener);
        ImageView icecreamImage = findViewById(R.id.icecreamImage);
        icecreamImage.setOnTouchListener(imageTouchListener);
    }

    private View.OnTouchListener imageTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setAlpha(0.7f);
                    break;
                case MotionEvent.ACTION_UP:
                    v.setAlpha(1.0f);
                    break;
            }
            return false; // vi touch xay ra truoc click nen return false de tiep tuc lan toa sk
        }
    };

    public void donutClick(View v) {
        itemSelected = "Donut";
        Toast t = Toast.makeText(this, "You Ordered A/An " + itemSelected, Toast.LENGTH_SHORT);
        t.show();
    }

    public void froyoClick(View v) {
        itemSelected = "Froyo";
        Toast t = Toast.makeText(this, "You Ordered A/An " + itemSelected, Toast.LENGTH_SHORT);
        t.show();
    }

    public void icecreamClick(View v) {
        itemSelected = "Ice Cream";
        Toast t = Toast.makeText(this, "You Ordered A/An " + itemSelected, Toast.LENGTH_SHORT);
        t.show();
    }

    public void fabClick(View v) {
        Intent it = new Intent();
        it.setClass(this, OrderActivity.class);
        it.putExtra("itemSelected", itemSelected);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast toast = Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT);
        toast.show();
        return super.onOptionsItemSelected(item);
    }
}