package com.example.a71asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textCount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCount = (TextView) findViewById(R.id.textCount);
    }

    public void ex(View v) {
        int max = 100;
        for (int i = 0; i < max; i++) {
            // tao rat nhieu AsyncTask va chay no
            MyAsyncTask task = new MyAsyncTask();
            task.excute
        }
    }

    private int sum = 0;
    private Object sync = new Object();
    Random rd = new Random();
    class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> { // chu y class Void chu khong phair kdl ng.thuy void

        void increase() {
            synchronized (sync) {
                sum++;
            }
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            Log.d("AsyncTask", "Thread ID is " + String.valueOf(Thread.currentThread().getId()));
            try {
                Thread.sleep(rd.nextInt(1000)); // rat nhieu luong truy cap vao bien rand nhung khong sua doi gi ca --> khong can dong bo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            increase();
            publishProgress(sum);
            // textCount.setText(sum); --> crash ngay vi ma trong ham nay khong duoc gui ve cho luong giao dien thuc thi
            return sum;
        }

        // ma trong ham nay duoc gui ve cho luong giao dien thuc thi
        @Override
        protected void onPostExecute(Integer integer) { // gia tri tra ve cua doInBackground() la dau vao cua onPostExecute()
            super.onPostExecute(integer);
            // textCount.setText(String.valueOf(integer));
        }

        // ma trong ham nay duoc gui ve cho luong giao dien thuc thi
        @Override
        protected void onProgressUpdate(Integer... values) { // gia tri truyen vao publishProgress la dau vao cua onProgressUpdate()
            super.onProgressUpdate(values);
            textCount.setText(String.valueOf(values[0]));
        }
    }
}