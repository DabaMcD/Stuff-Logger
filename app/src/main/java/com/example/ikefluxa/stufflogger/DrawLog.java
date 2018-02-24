package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Ike&Fluxa on 2/22/2018.
 */

public class DrawLog extends View {
    public boolean drawable = true;
    public int rowsPerPage = 10;
    public ArrayList<LogLine> logLines = new ArrayList<>();
    public Paint paint = new Paint();
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
            int lineGap = Constants.SCREEN_HEIGHT / rowsPerPage;
            for(int i = 0; i < Constants.SCREEN_HEIGHT - lineGap; i += lineGap) {
                paint.setColor(Color.LTGRAY);
                paint.setStrokeWidth(Constants.SCREEN_HEIGHT / 200);
                canvas.drawLine(0, i, Constants.SCREEN_WIDTH, i, paint);
            }
        }
        super.onDraw(canvas);
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
