package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class UsersListView extends View{
    Paint paint = new Paint();
    RectShadow topBar = new RectShadow();
    RectF rainbowRect = new RectF();
    TextShadow topBarText = new TextShadow();

    public UsersListView(Context context) {
        super(context);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight(1000);
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight(1000);
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight(1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawList(canvas);

        super.onDraw(canvas);
    }

    private void drawList(Canvas canvas) {
        for(int i = 0; i < /*Constants.users.size()*/10; i ++) {
            paint.setColor(Color.rgb(i * 25, i * 25, i * 25));
            canvas.drawRect(100, i * 100, 200, (i + 1) * 100, paint);
        }
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
