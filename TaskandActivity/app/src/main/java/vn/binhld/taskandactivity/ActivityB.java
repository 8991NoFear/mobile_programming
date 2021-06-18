package vn.binhld.taskandactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityB extends BaseActivity {

    Button buttonStartActivityA, buttonStartActivityB, buttonStartActivityC, buttonStartActivityD;
    TextView textViewTaskInfo, textViewInstanceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        buttonStartActivityA = (Button) findViewById(R.id.buttonStartActivityA);
        buttonStartActivityB = (Button) findViewById(R.id.buttonStartActivityB);
        buttonStartActivityC = (Button) findViewById(R.id.buttonStartActivityC);
        buttonStartActivityD = (Button) findViewById(R.id.buttonStartActivityD);

        textViewTaskInfo = (TextView) findViewById(R.id.textViewTaskInfo);
        textViewInstanceValue = (TextView) findViewById(R.id.textViewInstanceValue);

        buttonStartActivityA.setOnClickListener(this);
        buttonStartActivityB.setOnClickListener(this);
        buttonStartActivityC.setOnClickListener(this);
        buttonStartActivityD.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewTaskInfo.setText(getAppTaskState());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStartActivityA:
                startActivity(new Intent(this, ActivityA.class));
                break;
            case R.id.buttonStartActivityB:
                startActivity(new Intent(this, ActivityB.class));
                break;
            case R.id.buttonStartActivityC:
                startActivity(new Intent(this, ActivityC.class));
                break;
            case R.id.buttonStartActivityD:
                startActivity(new Intent(this, ActivityD.class));
                break;
            default:
                break;
        }
    }
}