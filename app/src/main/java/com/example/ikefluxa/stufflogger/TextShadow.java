package com.example.ikefluxa.stufflogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

class TextShadow extends Shadows {
    TextShadow() {
        super();
    }
    TextShadow(float shadowDiam, float shadowXshift, float shadowYshift, int shadowDarkness) {
        super(shadowDiam, shadowXshift, shadowYshift, shadowDarkness);
    }
    void draw(String txt, float x, float y, Canvas canvas, Paint paint) {
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
                canvas.drawText(txt, point.x, point.y, paint);
            }
        }

        // Draw the initial rect
        paint.setColor(origColor);
        canvas.drawText(txt, x, y, paint);
    }
}
