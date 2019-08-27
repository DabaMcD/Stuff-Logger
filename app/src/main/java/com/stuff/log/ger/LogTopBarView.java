package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LogTopBarView extends View {
    public LogTopBarView(Context context) {
        super(context);
    }
    public LogTopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        try {
            User user = Globals.users.get(0);
            TopBar.drawRectTextAndShadows(canvas, user.color, true, user.name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDraw(canvas);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
    float getTopBarHeight() {
        return TopBar.standardHeight;
    }
}
