package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class NewLoglineButtonView extends View {
    private Paint paint = new Paint();
    private RectShadow loglineAdderPlus1 = new RectShadow();
    private RectShadow loglineAdderPlus2 = new RectShadow();
    private CircleShadow loglineAdder = new CircleShadow();
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
        float buttonDistFromCorner = (float) (Math.min(Screen.width, Screen.height) / 4.5);
        x = (float) (Screen.width - buttonDistFromCorner / 1.2);
        y = (float) (Screen.height - buttonDistFromCorner / 1.2);
        rad = buttonDistFromCorner / 2;

        // Button
        paint.setColor(Constants.inverseColor(Constants.users.get(0).color));
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
    void draw(Boolean hovering) {
        this.hovering = hovering;
        invalidate();
        requestLayout();
    }
}
