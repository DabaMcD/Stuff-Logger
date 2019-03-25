package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
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
    void draw() {
        invalidate();
        requestLayout();
    }
}
