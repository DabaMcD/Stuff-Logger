package com.stuff.log.ger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MainTopBarView extends View {
    private Paint paint = new Paint();
    private Context context;

    // Button stuff
    private boolean hovering = false;
    private RectShadow userAdderV = new RectShadow();
    private RectShadow userAdderH = new RectShadow();
    public float buttonDistFromCorner;
    public float x;
    public float y;
    public float rad;

    public MainTopBarView(Context context) {
        super(context);
        init(context);
    }
    public MainTopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public MainTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        this.context = context;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        TopBar.drawRainbowRect(canvas);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextSize(Screen.height / 14f);
        paint.setColor(Color.BLACK);
        TopBar.textShadow.draw("Stuff Logger", Screen.height / 40f, TopBar.standardHeight / 2 + paint.getTextSize() / 3, canvas, paint);

        drawButton(canvas);

        super.onDraw(canvas);
    }
    private void drawButton(Canvas canvas) {
        // Define vars
        buttonDistFromCorner = (float) ((this.getHeight() / 2) * 1.2);
        x = (float) (Screen.width - buttonDistFromCorner / 1.2);
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
    void draw() {
        invalidate();
        requestLayout();
    }
    void actionDown(float touchX, float touchY) {
        if(Globals.getDist(touchX, touchY, x, y) <= rad) {
            hovering = true;
        }
    }
    void actionMove(float touchX, float touchY) {
        if(Globals.getDist(touchX, touchY, x, y) > rad) {
            hovering = false;
        }
    }
    void actionUp(float touchX, float touchY) {
        if(Globals.getDist(touchX, touchY, x, y) <= rad && hovering) {
            onNewUserTouch();
        }
        hovering = false;
    }
    private void onNewUserTouch() {
        Intent myIntent = new Intent(context, UserActivity.class);
        context.startActivity(myIntent);
    }
}
