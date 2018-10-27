package com.example.ikefluxa.stufflogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

class CircleShadow extends Shadows {
    CircleShadow() {
        this.shadowDiam = Shadows.standardShadowDiam;
        this.shadowXshift = Shadows.standardShadowXshift;
        this.shadowYshift = Shadows.standardShadowYshift;
        this.shadowDarkness = Shadows.standardShadowDarkness;
    }
    CircleShadow(float shadowDiam, float shadowXshift, float shadowYshift, int shadowDarkness) {
        this.shadowDiam = shadowDiam;
        this.shadowXshift = shadowXshift;
        this.shadowYshift = shadowYshift;
        this.shadowDarkness = shadowDarkness;
    }
    void draw(float x, float y, float radius, Canvas canvas, Paint paint) {
        // I think the function name explains it
        eraseAndAddAndEliminatePoints(x, y);

        // Save the original color for later
        origColor = paint.getColor();

        // Calculate the opacity based on number of points & the darkness
        paint.setColor(Color.argb((int) shadowDarkness, 0, 0, 0));

        // Loop through all the points
        for(Point point : points) {
            // Draw a little bit of shade at each point
            // Remember, the points represent the center of the shape.
            if (Math.random() > 0.5) {
                canvas.drawCircle(point.x, point.y, radius, paint);
            }
        }

        // Draw the initial rect
        paint.setColor(origColor);
        canvas.drawCircle(x, y, radius, paint);
    }
}
