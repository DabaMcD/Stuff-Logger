package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ike&Fluxa on 2/22/2018.
 */

public class DrawLog extends View {
    public Paint paint = new Paint();
    public Log log;
    int leftLimit;
    int lineGap;

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
        log = Constants.users.get(Constants.currentUserIndex).logs.get(Constants.users.get(Constants.currentUserIndex).logs.size() - 1);

        // Draw lines making a 1D grid
        lineGap = (int) (Constants.SCREEN_HEIGHT / 10f);
        // Tweak lineGap
        tweakLinegap();
        // Draw lines
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(Math.max(lineGap / 20, 2));
        for(int i = (int) (TopBar.standardHeight + lineGap * 1.5); i <= Constants.SCREEN_HEIGHT; i += lineGap) {
            canvas.drawLine(0, i, Constants.SCREEN_WIDTH, i, paint);
        }

        leftLimit = lineGap / 2;

        // Draw date at top of log
        paint.setTextSize((float) (lineGap * 0.8));
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(log.date, leftLimit, TopBar.standardHeight + lineGap + paint.getTextSize() / 3, paint);

        // Get some bounds
        float dashWidth = paint.measureText("N-N");
        float nWidth = paint.measureText("N");
        float eightWidth = paint.measureText("8");

        // Draw loglines
        for(int i = 0; i < log.logLines.size(); i ++) {
            float txtYpos = (float) ((TopBar.standardHeight + lineGap * 1.5) + (lineGap * (i + 0.5)) + (paint.getTextSize() / 3));
            canvas.drawText(log.logLines.get(i).subject.name, leftLimit + eightWidth * 3 + dashWidth, txtYpos, paint);
            canvas.drawText("-", leftLimit + eightWidth * 3 + nWidth, txtYpos, paint);
            String dadTime = MyTime.getDadTime(log.logLines.get(i).startTime);
            canvas.drawText(String.valueOf(dadTime.charAt(0)), leftLimit, txtYpos, paint);
            canvas.drawText(String.valueOf(dadTime.charAt(1)), leftLimit + eightWidth, txtYpos, paint);
            canvas.drawText(String.valueOf(dadTime.charAt(2)), leftLimit + eightWidth * 2, txtYpos, paint);
        }

        super.onDraw(canvas);
    }

    private void tweakLinegap() {
        int longestLoglineIndex = -1;
        leftLimit = lineGap / 2;
        paint.setTextSize((float) (lineGap * 0.8));
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        float dateWidth = paint.measureText(log.date) + leftLimit * 2;
        float eightWidth = paint.measureText("8");
        float dashWidth = paint.measureText("N:N");
        float recordLineWidth = dateWidth;
        for(int i = 0; i < log.logLines.size(); i ++) {
            // Get the length of a log line (without left and right margins)
            float logLineNameWidth = paint.measureText(log.logLines.get(i).subject.name);
            float thisLogLineLength = logLineNameWidth + dashWidth + eightWidth * 3 + leftLimit * 2;

            if(thisLogLineLength > recordLineWidth) { // If it breaks the line length record
                longestLoglineIndex = i;
                recordLineWidth = (int) thisLogLineLength;
            }
        }
        while(recordLineWidth > Constants.SCREEN_WIDTH) {
            lineGap -= 0.1;
            paint.setTextSize((float) (lineGap * 0.8));
            leftLimit = lineGap / 2;

            if(longestLoglineIndex == -1) {
                dateWidth = paint.measureText(log.date);
                recordLineWidth = dateWidth + leftLimit * 2;
            } else {
                String logLineSubjectName = log.logLines.get(longestLoglineIndex).subject.name;

                // logLineNameBounds = bounds of subject name
                float logLineNameWidth = paint.measureText(logLineSubjectName);
                dashWidth = paint.measureText("N:N");
                eightWidth = paint.measureText("8");
                recordLineWidth = (int) (leftLimit * 2.5 + logLineNameWidth + dashWidth + eightWidth * 3);
            }
        }
        while(log.logLines.size() * lineGap + (Constants.SCREEN_HEIGHT / 10 + lineGap * 3) > Constants.SCREEN_HEIGHT) {
            lineGap -= 0.1;
        }
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
