package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MainTopBarShadowView extends View {
    public MainTopBarShadowView(Context context) {
        super(context);
    }
    public MainTopBarShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MainTopBarShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        TopBar.drawRectWithShadow(canvas, Color.BLACK);
        super.onDraw(canvas);
    }
    public void draw() {
        invalidate();
        requestLayout();
    }
}
