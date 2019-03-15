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

public class LogNewLogLineButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectShadow logLineAdderPlus1 = new RectShadow();
    private RectShadow logLineAdderPlus2 = new RectShadow();
    private CircleShadow logLineAdder = new CircleShadow();
    float x, y, rad;
    private boolean touching;

    private Context context;

    public LogNewLogLineButtonView(Context context) {
        super(context);
    }
    public LogNewLogLineButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogNewLogLineButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init(Context context, float width) {
        this.context = context;
        rad = width / 4;
        touching = false;

        x = rad * 3;
        y = Screen.height - rad;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // Button
        paint.setColor(Globals.inverseColor(Globals.users.get(0).color));
        logLineAdder.draw(x, y, rad, canvas, paint);
        if(touching) {
            paint.setColor(Color.argb(30, 0, 0, 0));
            canvas.drawCircle(x, y, rad, paint);
        }

        // + sign
        paint.setTextSize(rad);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setColor(Color.DKGRAY);
        paint.setStrokeCap(Paint.Cap.ROUND);
        float endOfRectRelToCircle = rad / 2;
        float sideOfRectRelToCircle = rad / 15;

        // Vertical rect
        logLineAdderPlus1.draw(x - sideOfRectRelToCircle, y - endOfRectRelToCircle, x + sideOfRectRelToCircle, y + endOfRectRelToCircle, canvas, paint);
        // Horizontal rect
        logLineAdderPlus2.draw(x - endOfRectRelToCircle, y - sideOfRectRelToCircle, x + endOfRectRelToCircle, y + sideOfRectRelToCircle, canvas, paint);
        // Vertical rect
        canvas.drawRect(x - sideOfRectRelToCircle, y - endOfRectRelToCircle, x + sideOfRectRelToCircle, y + endOfRectRelToCircle, paint);

        super.onDraw(canvas);
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
        if(Globals.getDist(touchX, touchY, x, y) > rad && touching) {
            touching = false;
            draw();
        }
    }
    void actionUp(float touchX, float touchY) {
        if(touching) {
            if (Globals.getDist(touchX, touchY, x, y) <= rad) {
                startNewLogLineActivity();
            } else {
                draw();
            }
        }
    }
    private void startNewLogLineActivity() {
        Intent myIntent = new Intent(context, NewLogLineActivity.class);
        context.startActivity(myIntent);
    }
}
