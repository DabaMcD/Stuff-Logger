package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ike&Fluxa on 2/22/2018.
 */

public class DrawLog extends View {
    public int rowsPerPage = 10;
    public Paint paint = new Paint();
    public User user;
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

        canvas.drawRect(-1, -1, Constants.SCREEN_WIDTH + 2, Constants.SCREEN_HEIGHT / 10, paint);
        user = Constants.users.get(Constants.currentUserIndex);
        rowsPerPage = Math.max(10, user.logLines.size() + 2);
        int lineGap = Constants.SCREEN_HEIGHT / rowsPerPage;
        for(int i = lineGap / 2; i <= Constants.SCREEN_HEIGHT; i += lineGap) {
            paint.setColor(Color.LTGRAY);
            paint.setStrokeWidth(Constants.SCREEN_HEIGHT / 200);
            canvas.drawLine(0, i, Constants.SCREEN_WIDTH, i, paint);
        }
        super.onDraw(canvas);
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
