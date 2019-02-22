package com.stuff.log.ger;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;

class Globals {
    static ArrayList<User> users = new ArrayList<>();
    static void moveUserToFrontIndex(int index) {
        users.add(0, users.get(index));
        users.remove(index + 1);
    }

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
    static int darkenColor(int color, double factor) {
        return Color.rgb((int) (Color.red(color) * factor),
                (int) (Color.green(color) * factor),
                (int) (Color.blue(color) * factor)
        );
    }
    static int inverseColor(int color) {
        return Color.rgb(255-Color.red(color),
                255-Color.green(color),
                255-Color.blue(color));
    }
    static double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
