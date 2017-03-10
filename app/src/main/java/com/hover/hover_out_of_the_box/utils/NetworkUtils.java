package com.hover.hover_out_of_the_box.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by aung on 12/17/15.
 */
public class NetworkUtils {

    private static NetworkUtils objInstance;

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;

    private NetworkUtils(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static void initNetworkUtils(Context context) {
        objInstance = new NetworkUtils(context);
    }

    public static NetworkUtils getInstance() {
        return objInstance;
    }

    /**
     * Check if the device has connected to an active network. (Wifi / 3G)
     *
     * @return true if connected. false if not connected.
     */
    public boolean isOnline() {
        networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
