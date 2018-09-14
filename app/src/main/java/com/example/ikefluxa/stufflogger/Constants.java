package com.example.ikefluxa.stufflogger;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Constants {
    static int STATUS_BAR_HEIGHT; // Status bar is a
    static int ORIG_LONGER_SCREEN_DIM; // With status bar and always the longer dimension
    static int ORIG_SHORTER_SCREEN_DIM; // With status bar if included and always the shorter dim
    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;
    static ArrayList<User> users = new ArrayList<>();
    static int currentUserIndex;
    static ArrayList<Integer> colors = new ArrayList<>(Arrays.asList(
            Color.rgb(255, 0, 0), // Red
            Color.rgb(0, 0, 255), // Blue
            Color.rgb(100, 255, 0), // Neon green
            Color.rgb(255, 44, 188), // Pink
            Color.rgb(0, 255, 255), // Turquoise
            Color.rgb(255, 157, 0), // Orange
            Color.rgb(193, 0, 255), // Purple
            Color.rgb(0, 255, 64), // Green
            Color.rgb(255, 255, 0), // Yellow
            Color.rgb(180, 80, 0) // Brown
    ));
    static double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    static int inverseColor(int color) {
        return Color.rgb(255-Color.red(color),
                255-Color.green(color),
                255-Color.blue(color));
    }
    static void correctScreenDims(int orientation) {
        if (orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            SCREEN_WIDTH = ORIG_LONGER_SCREEN_DIM;
            SCREEN_HEIGHT = ORIG_SHORTER_SCREEN_DIM - STATUS_BAR_HEIGHT;
        } else if (orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT){
            SCREEN_WIDTH = ORIG_SHORTER_SCREEN_DIM;
            SCREEN_HEIGHT = ORIG_LONGER_SCREEN_DIM - STATUS_BAR_HEIGHT;
        } else {
            System.out.println("ERROR in Constants, method correctScreenDims");
        }
    }
}
