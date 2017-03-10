package com.hover.hover_out_of_the_box.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hover.hover_out_of_the_box.data.vos.PaymentIntegrationSuccessVO;

/**
 * Created by aung on 3/2/17.
 */

public class AppStateUtils {

    private static final String HWM_SHARED_PREFERENCE = "HWM_SHARED_PREFERENCE_0302_0001";

    private static final String PREF_KEY_SPI_SERVICE_ID = "PREF_KEY_SPI_SERVICE_ID";
    private static final String PREF_KEY_SPI_SERVICE_NAME = "PREF_KEY_SPI_SERVICE_NAME";
    private static final String PREF_KEY_SPI_OPERATOR_NAME = "PREF_KEY_SPI_OPERATOR_NAME";
    private static final String PREF_KEY_SPI_COUNTRY_NAME = "PREF_KEY_SPI_COUNTRY_NAME";
    private static final String PREF_KEY_SPI_CURRENCY = "PREF_KEY_SPI_CURRENCY";

    private static AppStateUtils objInstance;

    private SharedPreferences sharedPref;

    private AppStateUtils(Context context) {
        sharedPref = context.getSharedPreferences(HWM_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static void initAppStateUtils(Context context) {
        objInstance = new AppStateUtils(context);
    }

    public static AppStateUtils getInstance() {
        if (objInstance == null)
            throw new RuntimeException("AppStateUtils should have initialized in onCreate method of Custom App Object first.");

        return objInstance;
    }

    public void saveSuccessPaymentIntegration(PaymentIntegrationSuccessVO obj) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(PREF_KEY_SPI_SERVICE_ID, obj.getServiceId());
        editor.putString(PREF_KEY_SPI_SERVICE_NAME, obj.getServiceName());
        editor.putString(PREF_KEY_SPI_OPERATOR_NAME, obj.getOperatorName());
        editor.putString(PREF_KEY_SPI_COUNTRY_NAME, obj.getCountryName());
        editor.putString(PREF_KEY_SPI_CURRENCY, obj.getCurrency());

        editor.apply();
    }

    public boolean isPaymentAlreadyIntegrated() {
        return sharedPref.getInt(PREF_KEY_SPI_SERVICE_ID, -1) != -1;
    }
}
