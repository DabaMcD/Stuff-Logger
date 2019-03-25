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

public class LogClearLogButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float x, y, rad;
    private CircleShadow logClearer = new CircleShadow();
    private TextShadow clearText = new TextShadow();
    private boolean touching;

    boolean canClear; // If false, button gets grayed out

    private Context context;

    public LogClearLogButtonView(Context context) {
        super(context);
    }
    public LogClearLogButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogClearLogButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init(Context context, float width) {
        this.context = context;
        rad = width / 8;
        touching = false;

        x = rad * 2;
        y = Screen.height - rad * 2;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // Button
        paint.setColor(Globals.inverseColor(Globals.users.get(0).color));
        logClearer.draw(x, y, rad, canvas, paint);
        if(touching) {
            paint.setColor(Color.argb(30, 0, 0, 0));
            canvas.drawCircle(x, y, rad, paint);
        }

        // Text
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        tweakClearTextSize();
        clearText.draw("CLEAR", x, y + paint.getTextSize() / 3, canvas, paint);

        // Gray out the button
        if(!canClear) {
            paint.setColor(Color.argb(100, 255, 255, 255));
            canvas.drawCircle(x, y, rad, paint);
        }

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
        if(Globals.getDist(touchX, touchY, x, y) > rad && touching) {
            touching = false;
            draw();
        }
    }
    void actionUp(float touchX, float touchY, Intent currentIntent) {
        if(touching) {
            if (Globals.getDist(touchX, touchY, x, y) <= rad) {
                Globals.users.get(0).newLog();
                Files.reSave(context);
                currentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(currentIntent);
            }
            draw();
        }
        touching = false;
    }
}
