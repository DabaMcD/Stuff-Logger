package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LogToDoView extends View {
    private float width;

    public LogToDoView(Context context) {
        super(context);
    }
    public LogToDoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogToDoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init(float width) {
        this.width = width;
    }
    @Override
    protected void onDraw(Canvas canvas) {

    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
