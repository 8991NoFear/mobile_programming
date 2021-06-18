package com.example.inputcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Thuoc tinh du lieu cua
         * +, EditText la text
         * +, CheckBox, RadioButton, Switch la check
         * +, SeekBar la progress
         * */

        // minh hoa SeekBar
        SeekBar seekBar = (SeekBar) findViewById(R.id.linearLayout4_seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView textView = (TextView) findViewById(R.id.linearLayout4_textView);
                String txt = Integer.toString(seekBar.getProgress());
                textView.setText(txt);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // minh hoa Spinner
        final ArrayList<String> spinnerItems = new ArrayList<String>();
        spinnerItems.add("Item 1");
        spinnerItems.add("Item 2");
        spinnerItems.add("Item 3");
        spinnerItems.add("Item 4");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); // bo chuyen doi
        arrayAdapter.addAll(spinnerItems);
        Spinner spinner = (Spinner) findViewById(R.id.linearLayout6_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(MainActivity.this, spinnerItems.get(position).toString(), Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}