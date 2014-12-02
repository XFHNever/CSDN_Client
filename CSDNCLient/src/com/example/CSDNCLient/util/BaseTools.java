package com.example.CSDNCLient.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by never on 2014/8/30.
 */
public class BaseTools {
    public static int getWindowsWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
