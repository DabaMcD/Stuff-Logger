package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DrawLog extends View {
    private Paint paint = new Paint();
    private User user;
    private int leftLimit;
    private int lineGap;

    // Date stuff
    SimpleDateFormat simpleDateFormatMMM = new SimpleDateFormat("MMM", Locale.getDefault());
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
        month = simpleDateFormatMMM.format(cal.getTime());
        day = cal.get(Calendar.DAY_OF_MONTH);
        weekday = weekdayFromInt(cal.get(Calendar.DAY_OF_WEEK));
        year = cal.get(Calendar.YEAR);
        date = weekday + ", " + month + " " + day + ", " + year;

        // Draw lines representing a 1D grid
        int minRowsPerPage = 10;
        lineGap = Constants.SCREEN_HEIGHT / minRowsPerPage;
        // Tweak lineGap
        tweakLineGap();
        // Draw lines
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(Math.max(lineGap / 20, 2));
        for(int i = (int) (Constants.SCREEN_HEIGHT / 10 + lineGap * 1.5); i <= Constants.SCREEN_HEIGHT; i += lineGap) {
            canvas.drawLine(0, i, Constants.SCREEN_WIDTH, i, paint);
        }

        leftLimit = lineGap / 2;

        // Draw date at top of log
        paint.setTextSize((float) (lineGap * 0.8));
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(date, leftLimit, (float) (Constants.SCREEN_HEIGHT / 10 + lineGap) + paint.getTextSize() / 3, paint);

        // Draw loglines
        for(int i = 0; i < user.logLines.size(); i ++) {
            float txtYpos = (float) ((Constants.SCREEN_HEIGHT / 10 + lineGap * 1.5) + (lineGap * (i + 0.5)) + (paint.getTextSize() / 3));
            canvas.drawText(user.logLines.get(i).subject.name, leftLimit + paint.measureText("8") * 3 + paint.measureText("N-N"), txtYpos, paint);
            canvas.drawText("-", leftLimit + paint.measureText("8") * 3 + paint.measureText("N"), txtYpos, paint);
            canvas.drawText(String.valueOf(MyTime.getDadTime(user.logLines.get(i).startTime).charAt(0)), leftLimit, txtYpos, paint);
            canvas.drawText(String.valueOf(MyTime.getDadTime(user.logLines.get(i).startTime).charAt(1)), leftLimit + paint.measureText("8"), txtYpos, paint);
            canvas.drawText(String.valueOf(MyTime.getDadTime(user.logLines.get(i).startTime).charAt(2)), leftLimit + paint.measureText("8") * 2, txtYpos, paint);
        }

        super.onDraw(canvas);
    }

    private void tweakTopBarTextSize() {
        while(paint.measureText(user.name) > (Constants.SCREEN_WIDTH / 2) * 1.7) {
            paint.setTextSize(paint.getTextSize() - 1);
        }
    }

    private void tweakLineGap() {
        int longestLoglineIndex = -1;
        leftLimit = lineGap / 2;
        paint.setTextSize((float) (lineGap * 0.8));
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

        // I had a big bug before, because the line below didn't have " + leftLimit * 2" on the end.
        int recordLineWidth = (int) paint.measureText(date) + leftLimit * 2;
        for(int i = 0; i < user.logLines.size(); i ++) {
            if(paint.measureText(user.logLines.get(i).subject.name) * 2 + paint.measureText("N-N") + paint.measureText("8") * 3 + leftLimit * 2 > recordLineWidth) { // If it breaks the line length record
                longestLoglineIndex = i;
                recordLineWidth = (int) (paint.measureText(user.logLines.get(i).subject.name) * 2 + paint.measureText("N-N") + paint.measureText("8") * 3 + leftLimit * 2);
            }
        }
        while(recordLineWidth - 1 > Constants.SCREEN_WIDTH) {
            lineGap -= 0.1;
            paint.setTextSize((float) (lineGap * 0.8));
            leftLimit = lineGap / 2;

            if(longestLoglineIndex == -1) {
                recordLineWidth = (int) (paint.measureText(date) + leftLimit * 2);
            } else {
                String logLineSubjectName = user.logLines.get(longestLoglineIndex).subject.name;

                // logLineNameBounds = bounds of subject name
                recordLineWidth = (int) (leftLimit * 2.5 + paint.measureText(logLineSubjectName) + paint.measureText("N-N") + paint.measureText("8") * 3 + leftLimit * 2);
            }
        }
        while(user.logLines.size() * lineGap + (Constants.SCREEN_HEIGHT / 10 + lineGap * 3) > Constants.SCREEN_HEIGHT) {
            lineGap -= 0.1;
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
                return "Invalid number";
        }
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
