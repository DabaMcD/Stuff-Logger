package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class LogToDoListView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float width;
    private float height;
    private float topBarHeight;
    private final String topText = "To Do List";
    private ToDoList toDoList;

    private float sideLimit;
    private float lineGap;
    private float firstLineYpos;

    private final float bufferAtBottom = 0.75f; // in LineGaps

    public LogToDoListView(Context context) {
        super(context);
        this.setWillNotDraw(false); // For some weird reason we need this or else the onDraw method won't run.
    }
    public LogToDoListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setWillNotDraw(false); // For some weird reason we need this or else the onDraw method won't run.
    }
    public LogToDoListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setWillNotDraw(false); // For some weird reason we need this or else the onDraw method won't run.
    }
    void init(float width, float logTopBarHeight) {
        toDoList = Globals.users.get(0).toDoList;
        this.width = width;
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        tweakLineGap();
        System.out.println(paint.getTextSize());

        toDoList = Globals.users.get(0).toDoList;
        topBarHeight = logTopBarHeight;
        height = Screen.height - topBarHeight;

        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (Screen.height - topBarHeight));
        setMinimumWidth((int) width);
        this.setWillNotDraw(false); // For some weird reason we need this or else the onDraw method won't run.
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(topBarHeight, Screen.width - width);

        paint.setColor(Color.RED);
        canvas.drawPaint(paint);
        drawHorizontalGridLines(canvas);
        drawTopText(canvas);

        canvas.restore();

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
        System.out.println(getWidth());
        System.out.println(getHeight());
    }
}
