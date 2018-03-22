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
    Rect dateBounds = new Rect();
    Rect logLineBounds = new Rect();
    Rect dashBounds = new Rect();
    Rect eightBounds = new Rect();
    Rect logLineNameBounds = new Rect();
    Rect nBounds = new Rect();
    int recordLineWidth;
    int longestLoglineIndex = -1;
    int leftLimit;
    int lineGap;

    // Date stuff
    String weekday; // First three letters
    String month; // First three letters
    int day;
    int year;
    String date;

    // Shadowy stuff
    RectShadow topBar = new RectShadow();
    TextShadow topBarText = new TextShadow();

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
        topBar.draw(-100, -1, Constants.SCREEN_WIDTH + 100, Constants.SCREEN_HEIGHT / 10, canvas, paint);

        // Draw name on top bar
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        // Tweak text size
        paint.setTextSize(Constants.SCREEN_HEIGHT / 16);
        tweakTopBarTextSize();
        paint.setColor(Color.BLACK);
        topBarText.draw(user.name, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 20 + paint.getTextSize() / 3, canvas, paint);

        // Set date vals
        Calendar cal = Calendar.getInstance();
        month = new SimpleDateFormat("MMM").format(cal.getTime());
        day = cal.get(cal.DAY_OF_MONTH);
        weekday = weekdayFromInt(cal.get(cal.DAY_OF_WEEK));
        year = cal.get(cal.YEAR);
        date = weekday + ", " + month + " " + day + ", " + year;

        // Draw lines representing a 1D grid
        rowsPerPage = Math.max(10, user.logLines.size() + 2);
        lineGap = Constants.SCREEN_HEIGHT / rowsPerPage;
        // Tweak lineGap
        tweakLinegap();
        // Draw lines
        for(int i = (int) (Constants.SCREEN_HEIGHT / 10 + lineGap * 1.5); i <= Constants.SCREEN_HEIGHT; i += lineGap) {
            paint.setColor(Color.LTGRAY);
            paint.setStrokeWidth(Constants.SCREEN_HEIGHT / 200);
            canvas.drawLine(0, i, Constants.SCREEN_WIDTH, i, paint);
        }

        leftLimit = lineGap / 2;

        // Draw date at top of log
        paint.setTextSize((float) (lineGap * 0.8));
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(date, leftLimit, (float) (Constants.SCREEN_HEIGHT / 10 + lineGap) + paint.getTextSize() / 3, paint);

        // Get some bounds
        paint.getTextBounds("N-N", 0, 3, dashBounds);
        paint.getTextBounds("N", 0, 1, nBounds);
        paint.getTextBounds("8", 0, 1, eightBounds);

        // Draw loglines
        for(int i = 0; i < user.logLines.size(); i ++) {
            float txtYpos = (float) ((Constants.SCREEN_HEIGHT / 10 + lineGap * 1.5) + (lineGap * (i + 0.5)) + (paint.getTextSize() / 3));
            canvas.drawText(user.logLines.get(i).subject.name, leftLimit + eightBounds.width() * 3 + dashBounds.width(), txtYpos, paint);
            canvas.drawText("-", leftLimit + eightBounds.width() * 3 + nBounds.width(), txtYpos, paint);
            canvas.drawText(String.valueOf(MyTime.getDadTime(user.logLines.get(i).startTime).charAt(0)), leftLimit, txtYpos, paint);
            canvas.drawText(String.valueOf(MyTime.getDadTime(user.logLines.get(i).startTime).charAt(1)), leftLimit + eightBounds.width(), txtYpos, paint);
            canvas.drawText(String.valueOf(MyTime.getDadTime(user.logLines.get(i).startTime).charAt(2)), leftLimit + eightBounds.width() * 2, txtYpos, paint);
        }
        super.onDraw(canvas);
    }

    private void tweakTopBarTextSize() {
        paint.getTextBounds(user.name, 0, user.name.length(), tBounds);
        while(tBounds.width() > (Constants.SCREEN_WIDTH / 2) * 1.7) {
            paint.setTextSize(paint.getTextSize() - 1);
            paint.getTextBounds(user.name, 0, user.name.length(), tBounds);
        }
    }

    private void tweakLinegap() {
        paint.setTextSize((float) (lineGap * 0.8));
        paint.getTextBounds(date, 0, date.length(), dateBounds);
        paint.getTextBounds("8", 0, 1, eightBounds);
        paint.getTextBounds("N:N", 0, 3, dashBounds);
        recordLineWidth = dateBounds.width();
        for(int i = 0; i < user.logLines.size(); i ++) {
            // Get the length of a log line
            String logLineSubjectName = user.logLines.get(i).subject.name;
            paint.getTextBounds(logLineSubjectName, 0, logLineSubjectName.length(), logLineBounds);
            float thisLogLineLength = logLineBounds.width();
            paint.getTextBounds(user.logLines.get(i).subject.name, 0, user.logLines.get(i).subject.name.length(), logLineNameBounds);
            thisLogLineLength += logLineNameBounds.width() + dashBounds.width() + eightBounds.width() * 3;

            if(thisLogLineLength > recordLineWidth) { // If it breaks the line length record
                longestLoglineIndex = i;
                recordLineWidth = (int) thisLogLineLength;
            }
        }
        while(recordLineWidth > Constants.SCREEN_WIDTH) {
            lineGap -= 2;
            paint.setTextSize((float) (lineGap * 0.8));

            if(longestLoglineIndex == -1) {
                paint.setTextSize((float) (lineGap * 0.8));
                paint.getTextBounds(date, 0, date.length(), dateBounds);
                recordLineWidth = dateBounds.width() + leftLimit;
            } else {
                String logLineSubjectName = user.logLines.get(longestLoglineIndex).subject.name;

                // logLineNameBounds = bounds of subject name
                paint.getTextBounds(logLineSubjectName, 0, logLineSubjectName.length(), logLineNameBounds);
                paint.getTextBounds("N:N", 0, 3, logLineBounds);
                paint.getTextBounds("8", 0, 1, eightBounds);
                float thisLogLineLength = leftLimit * 2 + logLineNameBounds.width() + dashBounds.width() + eightBounds.width() * 3;

                recordLineWidth = (int) thisLogLineLength;
            }
        }
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
