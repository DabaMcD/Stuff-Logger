package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ClearLogButtonView extends View {
    Boolean hovering = false;
    Paint paint = new Paint();
    public float buttonDistFromCorner, x, y, rad;
    CircleShadow loglineAdder = new CircleShadow();
    TextShadow clearText = new TextShadow();
    boolean touching = false;

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
        buttonDistFromCorner = (float) (Math.min(Screen.width, Screen.height) / 4.5);
        x = (float) (Screen.width - buttonDistFromCorner / 1.2);
        y = (float) (Screen.height - buttonDistFromCorner / 0.4);
        rad = buttonDistFromCorner / 2;

        // Button
        paint.setColor(Globals.inverseColor(Globals.users.get(0).color));
        loglineAdder.draw(x, y, rad, canvas, paint);
        if(hovering) {
            paint.setColor(Color.argb(30, 0, 0, 0));
            canvas.drawCircle(x, y, rad, paint);
        }

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        tweakClearTextSize();
        clearText.draw("CLEAR", x, y + paint.getTextSize() / 3, canvas, paint);

        super.onDraw(canvas);
    }
    private void tweakClearTextSize() {
        paint.setTextSize(0);
        while(paint.measureText("CLEAR") < rad * 2) {
            paint.setTextSize(paint.getTextSize() + 1);
        }
        while(paint.measureText("CLEAR") > rad * 1.5) {
            paint.setTextSize(paint.getTextSize() - 1);
        }
    }
    void draw() {
        invalidate();
        requestLayout();
    }
    void actionDown(float touchX, float touchY) {
        // If touching inside button
        if(Globals.getDist(touchX, touchY, x, y) <= rad) {
            touching = true;
            draw();
        }
    }
    void actionMove(float touchX, float touchY) {
        // If touching outside button
        if(Globals.getDist(touchX, touchY, x, y) > rad && hovering) {
            touching = false;
            draw();
        }
    }
    void actionUp(float touchX, float touchY, Context context, LogView logView) {
        if(touching) {
            if (Globals.getDist(touchX, touchY, x, y) <= rad) {
                Globals.users.get(0).newLog();
                Files.reSave(context);
                logView.draw();
            }
            draw();
        }
    }
}
