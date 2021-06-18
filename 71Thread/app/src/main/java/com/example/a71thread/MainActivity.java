package com.example.a71thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView ex1TextCount = null;
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ex1TextCount = (TextView) findViewById(R.id.ex1TextCount);
        handler = new Handler(getMainLooper()); // handler cua main thread
    }

    private int sum = 0;
    private Random rand = new Random();
    private final Object sync = new Object(); // bien de dong bo --> dat la hang de khong duoc phep thay doi
    public void ex1(View v) {
        int max = 1000;
        Runnable runnable = new Runnable() { // anomyous class chi truy cap duoc thuoc tinh va bien final
            // cach 1: dong bo tren mot phuong thuc --> thuc chat la dong bo tren this
            synchronized void increase() {
                // nhung cau lenh dung "sum" thi phai dong bo
                sum++;
                Log.d("EX1", "sum = " + sum);
            }

            @Override
            public void run() {
//                 increase();

                // cach 2: dong bo tren mot bien
                synchronized (sync) {
                    sum++;
                    Log.d("EX1", "sum = " + sum);
                }

                try {
                    Thread.sleep(rand.nextInt(1000)); // rat nhieu luong truy cap vao bien rand nhung khong sua doi gi ca --> khong can dong bo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                 cach 1: sd runOnUiThread gui doan ma ve chay tren luong UI
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        textCount.setText(String.valueOf(sum));
//                        Log.d("THREAD", "sum = " + sum);
//                    }
//                });

                // cach 2: sd handler gui doan ma ve chay tren luong UI
                // ban chat runOnUiThread cung la sd handler
                // khi sd handler co the post voi delayed time
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ex1TextCount.setText(String.valueOf(sum));
                    }
                }, 1000);


            }
        };

        for (int i = 0; i < max; i++) {
            // cach 1: tao thread chuan tac
            // tat cac cac luong deu su dung chung mot instance cua Runnable
            Thread thread = new Thread(runnable);
            thread.start();

            // tham khao: https://www.geeksforgeeks.org/runnable-interface-in-java/
            // o truong hop nay khong the xung dot duoc vi tat ca cac Runnable duoc gan cho luong main chay
//            Runnable rn = new MyRunnable();
//            rn.run();

            // chi dinh mot luong khac se chay runnable chu khong phai luong main --> day la cach hay su dung nhat
            //            Thread thread = new Thread(new MyRunnable()); // --> do biet tai sao lai bi xung dot???
            //            thread.start();
        }
    }

    private boolean ran = false;
    public void ex2(View v) {
        MyThread myThread = null;
        if (myThread == null) {
            myThread = new MyThread("Hello ba con!");
            myThread.start();
        }

        Message myMessage = Message.obtain(); // tra ve 1 message tu global pool
        myMessage.obj = "Hello ca nha!";
        myThread.getMyHandler().sendMessage(myMessage); // gui message cho handler cua luong
    }

    // cach 3: tao custom thread
    class MyThread extends Thread {
        private Handler myHandler = null;
        private String myParam = null;

        public MyThread(String myParam) {
            this.myParam = myParam;
        }

        @Override
        public void run() {
            Looper.prepare(); // luong chi co toi da 1 message loop, mac dinh luong ko co --> goi prepare() de tao
            myHandler = new Handler() { // mac dinh gan handler nay voi message loop cua luong hien tai, neu khong co se throw
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    Log.d("EX2", msg.toString());
                    if (msg.obj.equals("quit")) {
                        getLooper().quit(); // ket thuc luong
                    }
                }
            };
            Looper.loop(); // khoi chay message queue
        }

        public Handler getMyHandler() {
            while (myHandler == null) {
                // do nothing
            }
            return myHandler;
        }
    }

    class MyRunnable implements Runnable {
        // ban chat la dong bo muc cau lenh tren bien this
        synchronized void increase() {
            sum++;
            Log.d("EX1", "sum = " + sum);
        }

        @Override
        public void run() {
            increase();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ex1TextCount.setText(String.valueOf(sum));
                }
            });
        }
    }
}