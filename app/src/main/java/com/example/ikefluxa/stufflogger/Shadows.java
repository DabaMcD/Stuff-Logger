package com.example.ikefluxa.stufflogger;

import android.graphics.Point;
import java.util.ArrayList;

class Shadows {
    int origColor;
    ArrayList<Point> points = new ArrayList<>();
    float shadowDiam;
    float shadowXshift;
    float shadowYshift;
    float shadowDarkness;
    final static float standardShadowDiam = 13;
    final static float standardShadowXshift = 3;
    final static float standardShadowYshift = 3;
    final static int standardShadowDarkness = 2;

    // todo: add constructor and call the super function from children instead of making constructor over and over

    void eraseAndAddAndEliminatePoints(float shapeCenterX, float shapeCenterY) {
        // Reset array of points
        points = new ArrayList<>();

        // Loop through a bunch of points in a square (with center at (0, 0) coordinates)
        for(int i = (int) (-shadowDiam / 2); i < shadowDiam / 2; i ++) {
            // Need nested fors since we're working in two dimensions
            for(int j = (int) (-shadowDiam / 2); j < shadowDiam / 2; j ++) {
                // Effectively translate the points based on the shadowXshift, shadowYshift, and center of the square
                points.add(new Point((int) (i + shapeCenterX + shadowXshift), (int) (j + shapeCenterY + shadowYshift)));
            }
        }

        eliminatePoints(shapeCenterX + shadowXshift, shapeCenterY + shadowYshift);
    }
    private void eliminatePoints(float shadeCenterX, float shadeCenterY) {
        for(int i = 0; i < points.size(); i ++) {
            if (Constants.getDist(shadeCenterX, shadeCenterY, points.get(i).x, points.get(i).y) > shadowDiam / 2) {
                points.remove(i);
                i --;
            }
        }
    }
}
