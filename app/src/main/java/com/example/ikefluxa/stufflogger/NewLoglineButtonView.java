package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ike&Fluxa on 3/29/2018.
 */

public class NewLoglineButtonView extends View {
    Paint paint = new Paint();
    RectShadow loglineAdderPlus1 = new RectShadow();
    RectShadow loglineAdderPlus2 = new RectShadow();
    CircleShadow loglineAdder = new CircleShadow();
    public float buttonDistFromCorner;
    public float x;
    public float y;
    public float rad;

    private boolean hovering;

    public NewLoglineButtonView(Context context) {
        super(context);
    }

    public NewLoglineButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewLoglineButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Define vars
        buttonDistFromCorner = (float) (Math.min(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT) / 4.5);
        x = (float) (Constants.SCREEN_WIDTH - buttonDistFromCorner / 1.2);
        y = (float) (Constants.SCREEN_HEIGHT - buttonDistFromCorner / 1.2);
        rad = buttonDistFromCorner / 2;

        // Button
        paint.setColor(Constants.inverseColor(Constants.users.get(Constants.currentUserIndex).color));
        loglineAdder.draw(x, y, rad, canvas, paint);
        if(hovering) {
            paint.setColor(Color.argb(30, 0, 0, 0));
            canvas.drawCircle(x, y, rad, paint);
        }

        // '+' sign
        paint.setTextSize(buttonDistFromCorner / 2 * 3);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setColor(Color.DKGRAY);
        paint.setStrokeCap(Paint.Cap.ROUND);
        float endOfRectRelToCircle = rad / 2;
        float sideOfRectRelToCircle = rad / 15;

        // Vertical rect
        loglineAdderPlus1.draw(x - sideOfRectRelToCircle, y - endOfRectRelToCircle, x + sideOfRectRelToCircle, y + endOfRectRelToCircle, canvas, paint);
        // Horizontal rect
        loglineAdderPlus2.draw(x - endOfRectRelToCircle, y - sideOfRectRelToCircle, x + endOfRectRelToCircle, y + sideOfRectRelToCircle, canvas, paint);
        // Vertical rect
        canvas.drawRect(x - sideOfRectRelToCircle, y - endOfRectRelToCircle, x + sideOfRectRelToCircle, y + endOfRectRelToCircle, paint);

        super.onDraw(canvas);
    }

    public void draw(Boolean hovering) {
        this.hovering = hovering;
        invalidate();
        requestLayout();
    }
}