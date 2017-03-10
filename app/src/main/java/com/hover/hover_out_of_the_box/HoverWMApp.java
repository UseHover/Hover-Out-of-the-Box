package com.hover.hover_out_of_the_box;

import android.app.Application;

import com.hover.hover_out_of_the_box.utils.AppStateUtils;
import com.hover.hover_out_of_the_box.utils.NetworkUtils;

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
