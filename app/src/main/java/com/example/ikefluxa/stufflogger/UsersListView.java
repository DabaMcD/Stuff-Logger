package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class UsersListView extends View {
    public UsersListView(Context context) {
        super(context);
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
