package com.example.droidcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OrderActivity extends AppCompatActivity {
    private String itemSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent it = getIntent();
        if (it.getStringExtra("itemSelected") != null) {
            itemSelected = it.getStringExtra("itemSelected").toString();
        }

        TextView orderTitle = findViewById(R.id.orderTitle);
        if (itemSelected != null) {
            orderTitle.setText("You Odered A/An " + itemSelected);
        } else {
            orderTitle.setText("You Didn't Odered Anything");
        }
    }
}