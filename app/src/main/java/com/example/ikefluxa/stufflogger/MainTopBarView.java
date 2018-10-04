package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MainTopBarView extends View {
    Paint paint = new Paint();

    // Unique to button stuff
    Boolean hovering;
    RectShadow userAdderV = new RectShadow();
    RectShadow userAdderH = new RectShadow();
    public float buttonDistFromCorner;
    public float x;
    public float y;
    public float rad;

    public MainTopBarView(Context context) {
        super(context);
    }

    public MainTopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MainTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TopBar.drawRainbowRect(canvas);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextSize(Constants.SCREEN_HEIGHT / 14);
        paint.setColor(Color.BLACK);
        TopBar.textShadow.draw("Stuff Logger", Constants.SCREEN_HEIGHT / 40, TopBar.standardHeight / 2 + paint.getTextSize() / 3, canvas, paint);

        drawButton(canvas);

        super.onDraw(canvas);
    }

    private void drawButton(Canvas canvas) {
        // Define vars
        buttonDistFromCorner = (float) ((this.getHeight() / 2) * 1.2);
        x = (float) (Constants.SCREEN_WIDTH - buttonDistFromCorner / 1.2);
        y = (float) (buttonDistFromCorner / 1.2);
        rad = (float) (buttonDistFromCorner / 1.5);

        // Button
        paint.setColor(Color.TRANSPARENT);
        if(hovering) {
            paint.setColor(Color.argb(30, 0, 0, 0));
            canvas.drawCircle(x, y, rad, paint);
        }

        // '+' sign
        paint.setTextSize(buttonDistFromCorner / 2 * 3);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setColor(Color.GREEN);
        paint.setStrokeCap(Paint.Cap.ROUND);
        float endOfRectRelToCircle = rad / 2;
        float sideOfRectRelToCircle = rad / 15;
        // Vertical rect
        userAdderV.draw(x - sideOfRectRelToCircle, y - endOfRectRelToCircle, x + sideOfRectRelToCircle, y + endOfRectRelToCircle, canvas, paint);
        // Horizontal rect
        userAdderH.draw(x - endOfRectRelToCircle, y - sideOfRectRelToCircle, x + endOfRectRelToCircle, y + sideOfRectRelToCircle, canvas, paint);
        // Vertical rect
        canvas.drawRect(x - sideOfRectRelToCircle, y - endOfRectRelToCircle, x + sideOfRectRelToCircle, y + endOfRectRelToCircle, paint);
    }

    public void draw(Boolean hovering) {
        this.hovering = hovering;
        invalidate();
        requestLayout();
    }
}
