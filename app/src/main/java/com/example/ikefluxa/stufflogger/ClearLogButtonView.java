package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.hardware.camera2.params.BlackLevelPattern;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ClearLogButtonView extends View {
    Boolean hovering = false;
    Paint paint = new Paint();
    public float buttonDistFromCorner, x, y, rad;
    CircleShadow loglineAdder = new CircleShadow();
    TextShadow clearText = new TextShadow();

    public ClearLogButtonView(Context context) {
        super(context);
    }

    public ClearLogButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearLogButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Define vars
        buttonDistFromCorner = (float) (Math.min(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT) / 4.5);
        x = (float) (Constants.SCREEN_WIDTH - buttonDistFromCorner / 1.2);
        y = (float) (Constants.SCREEN_HEIGHT - buttonDistFromCorner / 0.4);
        rad = buttonDistFromCorner / 2;

        // Button
        paint.setColor(Constants.inverseColor(Constants.users.get(Constants.currentUserIndex).color));
        loglineAdder.draw(x, y, rad, canvas, paint);
        if(hovering) {
            paint.setColor(Color.argb(30, 0, 0, 0));
            canvas.drawCircle(x, y, rad, paint);
        }

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLACK);
        clearText.draw("CLEAR", x, y, canvas, paint);

        super.onDraw(canvas);
    }

    public void draw(boolean hovering) {
        this.hovering = hovering;
        invalidate();
        requestLayout();
    }
}
