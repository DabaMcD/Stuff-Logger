package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
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
    Rect tBounds = new Rect();
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
        // Shorten user name
        user = Constants.users.get(Constants.currentUserIndex);

        // Draw top bar shadow
        paint.setColor(Color.argb(20, 0, 0, 0));
        canvas.drawRect(-1, -1, Constants.SCREEN_WIDTH + 1, (Constants.SCREEN_HEIGHT / 7) + (Constants.SCREEN_HEIGHT / 180), paint);

        // Draw top bar
        paint.setColor(user.color);
        canvas.drawRect(-1, -1, Constants.SCREEN_WIDTH + 1, Constants.SCREEN_HEIGHT / 7, paint);

        // Draw name on top bar
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        // Specialize text size
        paint.setTextSize(Constants.SCREEN_HEIGHT / 10);
        paint.getTextBounds(user.name, 0, user.name.length(), tBounds);
        while(tBounds.width() > (Constants.SCREEN_WIDTH / 2) * 1.5) {
            paint.setTextSize(paint.getTextSize() - 1);
            paint.getTextBounds(user.name, 0, user.name.length(), tBounds);
        }
        // Shadow text
        paint.setColor(Color.argb(20, 0, 0, 0));
        canvas.drawText(user.name, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 14 + Constants.SCREEN_HEIGHT / 120 + paint.getTextSize() / 3, paint);
        // Real text
        paint.setColor(Color.WHITE);
        canvas.drawText(user.name, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 14 + paint.getTextSize() / 3, paint);

        // Draw lines representing a 1D grid
        rowsPerPage = Math.max(10, user.logLines.size() + 2);
        int lineGap = Constants.SCREEN_HEIGHT / rowsPerPage;
        for(int i = Constants.SCREEN_HEIGHT / 7 + lineGap / 2; i <= Constants.SCREEN_HEIGHT; i += lineGap) {
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
