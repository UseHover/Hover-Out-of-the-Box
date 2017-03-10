package com.usehover.wm.test.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by aung on 12/16/15.
 */
public class ScreenUtils {

    public static final float SCALE_START_ANCHOR = 0.3f;
    public static final int SCALE_DELAY = 200;
    public static final int IMMEDIATELY = 0;

    private static ScreenUtils objInstance;

    private float mWidthPx, mHeightPx;
    private float mDensity;

    private ScreenUtils() {

    }

    public static ScreenUtils getInstance() {
        if (objInstance == null) {
            objInstance = new ScreenUtils();
        }

        return objInstance;
    }

    public void initScreenDimension(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        mDensity = activity.getResources().getDisplayMetrics().density;
        mWidthPx = outMetrics.widthPixels;
        mHeightPx = outMetrics.heightPixels;
    }

    /**
     * Put the content below the status bar and make the status bar translucent.
     *
     * @param activity
     */
    public static void setStatusbarTranslucent(boolean isToTranslucent, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isToTranslucent) {
                Window window = activity.getWindow();
            /*
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    */
                window.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                WindowManager.LayoutParams attrs = activity.getWindow()
                        .getAttributes();
                //attrs.flags &= (~WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                attrs.flags &= (~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                activity.getWindow().setAttributes(attrs);
                activity.getWindow().clearFlags(
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }
        }
    }

    public static void setStatusbarColor(int colorReference, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(activity.getResources().getColor(colorReference));
        }
    }

    /**
     * Show soft keyboard in some situation.
     */
    public static void showSoftKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        }
    }

    /**
     * Hide soft keyboard based on the EditText which the focus is in.
     *
     * @param etList
     */
    public static void hideSoftKeyboard(Context context, EditText... etList) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
        if (imm != null) {
            for (EditText et : etList) {
                imm.hideSoftInputFromWindow(et.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * Get pixel from dpi.
     *
     * @param dpi
     * @return
     */
    public static float getPixelFromDPI(Context context, float dpi) {
        return (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, context.getResources().getDisplayMetrics()));
    }

    public float getScreenWidthPx() {
        return mWidthPx;
    }

    public float getScreenHeightPx() {
        return mHeightPx;
    }

    public float getScreenDensity() {
        return mDensity;
    }
}
