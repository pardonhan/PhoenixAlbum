package com.phoenix.album.utils;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by HanFL on 2016/11/4.
 */

public class DisplayUtils {

    private static boolean isInitialize = false;
    public static int screenWidth;
    public static int screenHeight;
    public static int screenDpi;
    public static float density = 1;
    public static float scaledDensity;

    public static void initScreen(Activity activity) {
        if (isInitialize) return;
        isInitialize = true;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealMetrics(metrics);
        } else {
            display.getMetrics(metrics);
        }

        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        screenDpi = metrics.densityDpi;
        density = metrics.density;
    }

}
