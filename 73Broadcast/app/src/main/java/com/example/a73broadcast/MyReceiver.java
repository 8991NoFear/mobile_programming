package com.example.a73broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = null;
        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_ON:
                str = "screen on";
                break;
            case Intent.ACTION_SCREEN_OFF:
                str = "screen off";
                break;
            case MainActivity.CUSTOM_ACTION:
                str = "custom action occurs";
                break;
        }
        Toast t = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        t.show();
    }
}
