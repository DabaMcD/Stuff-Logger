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
    float buttonDistFromCorner = (float) (Constants.SCREEN_WIDTH / 6.5);
    public float x = Constants.SCREEN_WIDTH - buttonDistFromCorner;
    public float y = Constants.SCREEN_HEIGHT - buttonDistFromCorner;
    public float rad = buttonDistFromCorner / 2;

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
        loglineAdderPlus1.draw(x - Constants.SCREEN_HEIGHT / 200, y - Constants.SCREEN_HEIGHT / 40, x + Constants.SCREEN_HEIGHT / 200, y + Constants.SCREEN_HEIGHT / 40, canvas, paint);
        loglineAdderPlus2.draw(x - Constants.SCREEN_HEIGHT / 40, y - Constants.SCREEN_HEIGHT / 200, x + Constants.SCREEN_HEIGHT / 40, y + Constants.SCREEN_HEIGHT / 200, canvas, paint);
        canvas.drawRect(x - Constants.SCREEN_HEIGHT / 200, y - Constants.SCREEN_HEIGHT / 40, x + Constants.SCREEN_HEIGHT / 200, y + Constants.SCREEN_HEIGHT / 40, paint);

        super.onDraw(canvas);
    }

    public void draw(Boolean hovering) {
        this.hovering = hovering;
        invalidate();
        requestLayout();
    }
}
