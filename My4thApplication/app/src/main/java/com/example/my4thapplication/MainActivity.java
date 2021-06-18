package com.example.my4thapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_CODE_1 = 1; // get text input from activity 2

    Button btn1, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
    }

    public void onIntent1(View v) {
        Intent it = new Intent(this, Activity4Intent1.class);
        startActivityForResult(it, REQUEST_CODE_1);
    }

    public void onIntent2(View v) {
        Intent it = new Intent(this, Activity4Intent2.class);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_1) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String msg = data.getStringExtra("msg");
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}