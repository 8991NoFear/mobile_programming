package com.example.mythirdapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("ActivityLifeCycle", "onCreate");

        textView = findViewById(R.id.textView0);
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("text", "NO"));
        }
    }

    public void onClickButton(View v) {
        String msg = (String) ((Button) v).getText();
        textView.setText(textView.getText() + msg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ActivityLifeCycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ActivityLifeCycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ActivityLifeCycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ActivityLifeCycle", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ActivityLifeCycle", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ActivityLifeCycle", "onDestroy");
    }

    // Hien tai dang khong vao day ???
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("ActivityLifeCycle", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("ActivityLifeCycle", "onRestoreInstanceState");
    }

}