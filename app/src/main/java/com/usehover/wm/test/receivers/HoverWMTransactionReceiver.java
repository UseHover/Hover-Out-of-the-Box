package com.usehover.wm.test.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.usehover.wm.test.HoverWMApp;

/**
 * Created by aung on 3/1/17.
 */

public class HoverWMTransactionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(HoverWMApp.TAG, "Received : " + intent.getStringExtra("code"));
        Toast.makeText(context, "HoverWMTransactionReceiver - Received : " + intent.getStringExtra("code"), Toast.LENGTH_SHORT).show();
    }
}
