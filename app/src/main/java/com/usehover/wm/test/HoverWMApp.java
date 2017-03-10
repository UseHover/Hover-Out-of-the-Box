package com.usehover.wm.test;

import android.app.Application;

import com.usehover.wm.test.utils.AppStateUtils;
import com.usehover.wm.test.utils.NetworkUtils;

/**
 * Created by aung on 2/28/17.
 */

public class HoverWMApp extends Application {

    public static final String TAG = "HoverWMApp";

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkUtils.initNetworkUtils(getApplicationContext());
        AppStateUtils.initAppStateUtils(getApplicationContext());
    }
}
