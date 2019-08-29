package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LogToDoListView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float width;

    private float height;
    private float topBarHeight;
    private final String topText = "To Do List";
    private ToDoList toDoList;

    private float sideLimit;
    private float lineGap = 100;
    private float firstLineYpos;

    private final float bufferAtBottom = 0.75f; // in LineGaps

    public LogToDoListView(Context context) {
        super(context);
    }
    public LogToDoListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogToDoListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init(float width, float logTopBarHeight) {
        toDoList = Globals.users.get(0).toDoList;
        this.width = width;
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        tweakLineGap();

        toDoList = Globals.users.get(0).toDoList;
        topBarHeight = logTopBarHeight;
        height = Screen.height - topBarHeight;

        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (Screen.height - topBarHeight));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        try {
            paint.setColor(Color.RED);
            canvas.drawPaint(paint);
            drawHorizontalGridLines(canvas);
            drawTopText(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDraw(canvas);
    }
    private void tweakLineGap() {
        paint.setTextSize(1000); // We just need a big number here
        float topTextWidth = paint.measureText(topText);
        float ratio = paint.getTextSize() / topTextWidth; // For dimensional analysis
        paint.setTextSize((width - sideLimit * 2) * ratio);
    }
    private void updateTextSize() {
        paint.setTextSize((float) (lineGap * 0.8d));
    }
    private void updateSideLimit() {
        sideLimit = (float) (lineGap / 2d);
    }
    private void updateFirstLineYpos() {
        firstLineYpos = (float) (lineGap * 1.5);
    }
    private void drawHorizontalGridLines(Canvas canvas) {
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(Math.max(lineGap / 20f, 2));
        for(float i = firstLineYpos; i <= Screen.height; i += lineGap) {
            canvas.drawLine(0, i, width, i, paint);
        }
    }
    private void drawTopText(Canvas canvas) {
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(topText, sideLimit, lineGap + paint.getTextSize() / 3f, paint);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
