package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Ike&Fluxa on 2/22/2018.
 */

public class DrawLog extends View {
    public int rowsPerPage = 10;
    public Paint paint = new Paint();
    public User user;
    Rect tBounds = new Rect();

    // Date stuff
    String weekday; // First three letters
    String monthday; // First three letters
    int year;
    String date;

    // Shadowy stuff
    RectShadow topBar = new RectShadow(-100, -1, Constants.SCREEN_WIDTH + 100, Constants.SCREEN_HEIGHT / 10, Shadows.standardShadowDiam, 0, 0, Shadows.standardShadowDarkness);

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

        // Draw top bar
        paint.setColor(user.color);
        topBar.draw(canvas, paint);

        // Draw name on top bar
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        // Specialize text size
        paint.setTextSize(Constants.SCREEN_HEIGHT / 14);
        paint.getTextBounds(user.name, 0, user.name.length(), tBounds);
        while(tBounds.width() > (Constants.SCREEN_WIDTH / 2) * 1.7) {
            paint.setTextSize(paint.getTextSize() - 1);
            paint.getTextBounds(user.name, 0, user.name.length(), tBounds);
        }
        // Shadow text
        paint.setColor(Color.argb(20, 0, 0, 0));
        canvas.drawText(user.name, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 20 + Constants.SCREEN_HEIGHT / 120 + paint.getTextSize() / 3, paint);
        // Real text
        paint.setColor(Color.WHITE);
        canvas.drawText(user.name, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 20 + paint.getTextSize() / 3, paint);

        // Draw lines representing a 1D grid
        rowsPerPage = Math.max(10, user.logLines.size() + 2);
        int lineGap = Constants.SCREEN_HEIGHT / rowsPerPage;
        for(int i = (int) (Constants.SCREEN_HEIGHT / 10 + lineGap * 1.5); i <= Constants.SCREEN_HEIGHT; i += lineGap) {
            paint.setColor(Color.LTGRAY);
            paint.setStrokeWidth(Constants.SCREEN_HEIGHT / 200);
            canvas.drawLine(0, i, Constants.SCREEN_WIDTH, i, paint);
        }

        // Draw date at top of log
        Calendar cal = Calendar.getInstance();
        monthday = new SimpleDateFormat("MMM DD").format(cal.getTime());
        weekday = weekdayFromInt(cal.get(cal.DAY_OF_WEEK));
        year = cal.get(cal.YEAR);
        date = weekday + ", " + monthday + ", " + year;
        paint.setTextSize((float) (lineGap * 0.8));
        canvas.drawText(weekday, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 3, paint);
        super.onDraw(canvas);
    }

    private String weekdayFromInt(int day) {
        switch(day) {
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            case Calendar.SUNDAY:
                return "Sun";
            default:
                return "Invaid number";
        }
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
