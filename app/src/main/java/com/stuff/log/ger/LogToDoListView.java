package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
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

        toDoList = Globals.users.get(0).toDoList;
        topBarHeight = logTopBarHeight;
        height = Screen.height - topBarHeight;

        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (Screen.height - topBarHeight));
    }
    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);
    }
    private void tweakLineGap() {
        int longestLogLineIndex = -1;
        updateSideLimit();
        updateTextSize();
        float recordLineWidth = paint.measureText(topText) + sideLimit * 2f;
        for(int i = 0; i < toDoList.getSize(); i ++) {
            // Get the length of a log line (without left and right margins)
            float logLineNameWidth = paint.measureText(toDoList.getItem(i).getName());
            float thisLogLineLength = logLineNameWidth + paint.measureText("888N-N") + sideLimit * 2f; // sideLimit * 2 because you gotta account for both sides

            if(thisLogLineLength > recordLineWidth) { // If it breaks the line length record
                longestLogLineIndex = i;
                recordLineWidth = (int) thisLogLineLength;
            }
        }
        while(recordLineWidth > width) {
            decreaseLineGap();
            updateTextSize();
            updateSideLimit();

            if(longestLogLineIndex == -1) {
                recordLineWidth = paint.measureText(topText) + sideLimit * 2f; // sideLimit * 2 because you gotta account for both sides
            } else {
                String logLineSubjectName = toDoList.getItem(longestLogLineIndex).getName();

                // logLineNameBounds = bounds of subject name
                float logLineNameWidth = paint.measureText(logLineSubjectName);
                recordLineWidth = (int) (sideLimit * 2f + logLineNameWidth + paint.measureText("888N-N"));
            }
        }
        updateFirstLineYpos();
        while(toDoList.getSize() * lineGap + firstLineYpos /* and just for extra buffer at the bottom: */ + lineGap * bufferAtBottom > Screen.height) {
            decreaseLineGap();
            updateFirstLineYpos();
        }
    }
    private void decreaseLineGap() {
        lineGap /= 1.01d;
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
    private void drawDate(Canvas canvas) {
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(topText, sideLimit, lineGap + paint.getTextSize() / 3f, paint);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
