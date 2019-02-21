package com.stuff.log.ger;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

class Screen {
    static int statusBarHeight;
    static int longerDim; // Including status bar if needed
    static int shorterDim; // Including status bar if needed
    static int width;
    static int height;

    static void setDims(WindowManager windowManager, Resources resources) {
        // This method should only be called in the onCreate method of the startup activity.

        // Whenever you call this method, it should look like the below:
        // Screen.setDims(getWindowManager(), getResources());

        // In order for the getWindowManager() and the getResources() to work, you must be calling it directly from the activity class


        // Set status bar height
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId);
        }

        // Set total screen width & height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int wd = displayMetrics.widthPixels;
        int ht = displayMetrics.heightPixels;
        longerDim = Math.max(ht, wd);
        shorterDim = Math.min(ht, wd);
    }
    static void correctDims(Resources resources) {
        // This method should be called in the onCreate method of every activity.

        // Whenever you call this method, it should look like the below:
        // Screen.correctDims(getResources());

        if (resources.getConfiguration().orientation == 2) { // 2 = landscape
            width = longerDim;
            height = shorterDim - statusBarHeight;
        } else { // if (orientation == 1) --- 1 = portrait
            width = shorterDim;
            height = longerDim - statusBarHeight;
        }

        // Just to re-define the top bar height
        TopBar.standardHeight = (float) (height  * 0.175);
    }
}
