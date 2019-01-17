package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class UserTopBarView extends View {
    public UserTopBarView(Context context) {
        super(context);
    }
    public UserTopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public UserTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        TopBar.drawRectTextAndShadows(canvas, 12345, true, "ADD NEW USER");

        super.onDraw(canvas);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
