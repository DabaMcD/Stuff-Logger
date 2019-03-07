package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class NewUserTopBarView extends View {
    public NewUserTopBarView(Context context) {
        super(context);
    }
    public NewUserTopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public NewUserTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
