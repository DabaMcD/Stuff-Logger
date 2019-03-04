package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LogActivityDividerView extends View {
    float lineThk;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public LogActivityDividerView(Context context) {
        super(context);
    }
    public LogActivityDividerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogActivityDividerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init() {
        // Once the log activity calls this function, the Screen dimensions will be up-to-date
        lineThk = Screen.width / 100f;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(lineThk);
        paint.setColor(Color.DKGRAY);
        canvas.drawLine(Screen.width / 2f, 0, Screen.width / 2f, Screen.height + lineThk, paint);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
