package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ike&Fluxa on 2/22/2018.
 */

public class DrawLog extends View {
    public boolean drawable = false;
    public DrawLog(Context context) {
        super(context);
    }

    public DrawLog(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawLog(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(drawable) {
            for(int i = 0; i < Constants.SCREEN_HEIGHT) {

            }
        }
        super.onDraw(canvas);
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
