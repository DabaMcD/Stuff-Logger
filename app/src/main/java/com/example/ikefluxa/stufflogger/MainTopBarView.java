package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MainTopBarView extends View {
    Paint paint = new Paint();
    RectShadow topBar = new RectShadow();
    RectF rainbowRect = new RectF();
    TextShadow topBarText = new TextShadow();
    Boolean hovering;

    // Unique to button stuff
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
        // Draw top bar
        paint.setColor(Color.BLACK);
        topBar.draw((float) -100, (float) (Constants.SCREEN_HEIGHT / 20), (float) Constants.SCREEN_WIDTH, (float) (Constants.SCREEN_HEIGHT / 10), canvas, paint); // Just for the shadow
        drawRainbowTopBar(canvas);

        // Draw name on top bar
        drawText(canvas);

        // Draw button
        drawButton(canvas);

        super.onDraw(canvas);
    }

    private void drawRainbowTopBar(Canvas canvas) {
        rainbowRect.top = 0;
        rainbowRect.bottom = (float) (Constants.SCREEN_HEIGHT / 10.0);
        for(int i = 0; i < Constants.SCREEN_WIDTH; i ++) {
            rainbowRect.left = i;
            rainbowRect.right = i + 1;
            paint.setColor(Color.HSVToColor(new float[] {(float) ((360.000 / Constants.SCREEN_WIDTH) * i), 1, 1}));
            canvas.drawRect(rainbowRect, paint);
        }
    }

    private void drawText(Canvas canvas) {
        // Draw text "STUFF LOGGER"
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setTextSize(Constants.SCREEN_HEIGHT / 17);
        paint.setColor(Color.DKGRAY);
        topBarText.draw("Stuff Logger", Constants.SCREEN_WIDTH / 28, Constants.SCREEN_HEIGHT / 20 + paint.getTextSize() / 3, canvas, paint);
    }

    private void drawButton(Canvas canvas) {
        // Define vars
        buttonDistFromCorner = (float) ((Constants.SCREEN_HEIGHT / 20) * 1.2);
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
