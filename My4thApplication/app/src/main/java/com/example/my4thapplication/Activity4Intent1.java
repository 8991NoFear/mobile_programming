package com.example.my4thapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity4Intent1 extends AppCompatActivity {
    EditText editText = null;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_intent1);

        it = getIntent(); // get intent make this happen
        editText = (EditText) findViewById(R.id.editText);
    }

    public void onOK(View v) {
        Intent repIt = new Intent();
        String msg = editText.getText().toString();
        repIt.putExtra("msg", msg);
        setResult(RESULT_OK, repIt);
        finish();
    }

    public void onCancel(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}