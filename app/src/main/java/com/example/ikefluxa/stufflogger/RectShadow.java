package com.example.ikefluxa.stufflogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Ike&Fluxa on 3/18/2018.
 */

public class RectShadow extends Shadows {
    float left;
    float top;
    float right;
    float bottom;
    public RectShadow(float left, float top, float right, float bottom, Paint paint, Canvas canvas, float shadowDiam, float shadowXshift, float shadowYshift, float shadowDarkness) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.shadowDiam = shadowDiam;
        this.shadowXshift = shadowXshift;
        this.shadowYshift = shadowYshift;
        this.shadowDarkness = shadowDarkness;
        this.canvas = canvas;
        this.paint = paint; // Important to set it, not to directly set it equal to the other, or else if you change one, the other changes too.
        this.origcolor = paint.getColor();
    }

    public void draw() {
        // I think the function name explains it
        eraseAndAddAndEliminatePoints((left + right) / 2, (top + bottom) / 2);
        // Calculate the opacity based on number of points & the darkness
        paint.setColor(Color.argb((int) (shadowDarkness / points.size()), 0, 0, 0));
        // Loop through all the points
        for(Point point : points) {
            // Draw a little bit of shade at each point
            // Remember, the points represent the center of the shape.
            canvas.drawRect(point.x - (right - left) / 2, point.y - (bottom - top) / 2, point.x + (right - left) / 2, point.y + (bottom - top) / 2, paint);
        }

        // Draw the initial rect
        paint.setColor(origcolor);
        canvas.drawRect(left, top, right, bottom, paint); // with the original paint.
    }
}
