package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LogToDoScrollView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float width;
    private float height;
    private float topBarHeight;
    private final String topText = "To Do List";

    public LogToDoScrollView(Context context) {
        super(context);
    }
    public LogToDoScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogToDoScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init(float width, float logTopBarHeight) {
        this.width = width;
        topBarHeight = logTopBarHeight;
        height = Screen.height - topBarHeight;
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (Screen.height - topBarHeight));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(Screen.width - width, topBarHeight);

        canvas.restore();

        super.onDraw(canvas);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
