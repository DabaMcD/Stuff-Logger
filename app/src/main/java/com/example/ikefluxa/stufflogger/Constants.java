package com.example.ikefluxa.stufflogger;

import android.graphics.Color;

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
            Color.rgb(255, 0, 0),
            Color.rgb(0, 0, 255),
            Color.rgb(100, 255, 0),
            Color.rgb(255, 44, 188),
            Color.rgb(0, 255, 255),
            Color.rgb(255, 157, 0),
            Color.rgb(193, 0, 255),
            Color.rgb(0, 255, 64)
    ));
}
