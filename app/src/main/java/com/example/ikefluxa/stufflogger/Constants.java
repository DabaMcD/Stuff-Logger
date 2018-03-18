package com.example.ikefluxa.stufflogger;

import android.graphics.Color;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ike&Fluxa on 2/22/2018.
 */

public class Constants {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static ArrayList<User> users = new ArrayList<>();
    public static int currentUserIndex;
    public static ArrayList<Integer> colors = new ArrayList<>(Arrays.asList(
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
    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }
}
