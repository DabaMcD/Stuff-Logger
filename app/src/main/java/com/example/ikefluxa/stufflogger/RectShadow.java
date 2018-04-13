package com.example.ikefluxa.stufflogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Ike&Fluxa on 3/18/2018.
 */

public class RectShadow extends Shadows {
    public RectShadow(float shadowDiam, float shadowXshift, float shadowYshift, int shadowDarkness) {
        this.shadowDiam = shadowDiam;
        this.shadowXshift = shadowXshift;
        this.shadowYshift = shadowYshift;
        this.shadowDarkness = shadowDarkness;
    }
    public RectShadow() {
        this.shadowDiam = Shadows.standardShadowDiam;
        this.shadowXshift = Shadows.standardShadowXshift;
        this.shadowYshift = Shadows.standardShadowYshift;
        this.shadowDarkness = Shadows.standardShadowDarkness;
    }

    public void draw(float left, float top, float right, float bottom, Canvas canvas, Paint paint) {
        // I think the function name explains it
        eraseAndAddAndEliminatePoints((left + right) / 2, (top + bottom) / 2);

        // Save the original color for later
        origcolor = paint.getColor();

        // Set very translucent color
        paint.setColor(Color.argb((int) shadowDarkness, 0, 0, 0));

        // Loop through all the points
        for(Point point : points) {
            // Draw a little bit of shade at each point
            // Remember, the points represent the center of the shape.
            if (Math.random() > 0.5) {
                canvas.drawRect(point.x - (right - left) / 2, point.y - (bottom - top) / 2, point.x + (right - left) / 2, point.y + (bottom - top) / 2, paint);
            }
        }

        // Draw the initial rect
        paint.setColor(origcolor);
        canvas.drawRect(left, top, right, bottom, paint);
    }
}
