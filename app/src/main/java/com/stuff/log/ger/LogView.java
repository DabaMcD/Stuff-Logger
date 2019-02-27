package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LogView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Log log;
    private int leftLimit;
    private float lineGap;

    public LogView(Context context) {
        super(context);
    }
    public LogView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // Make a nice short word (log) to represent the hash below
        log = Globals.users.get(0).logs.get(Globals.users.get(0).logs.size() - 1);
        lineGap = Screen.height / 10f; // Set min line gap
        tweakLineGap();
        drawHorizontalGridLines(canvas);
        updateLeftLimit();
        updateTextSize();
        drawDate(canvas);
        drawLoglines(canvas);

        super.onDraw(canvas);
    }
    private void tweakLineGap() {
        int longestLoglineIndex = -1;
        updateLeftLimit();
        updateTextSize();
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        float recordLineWidth = paint.measureText(log.date) + leftLimit * 2;
        for(int i = 0; i < log.logLines.size(); i ++) {
            // Get the length of a log line (without left and right margins)
            float logLineNameWidth = paint.measureText(log.logLines.get(i).subject.name);
            float thisLogLineLength = logLineNameWidth + paint.measureText("N-N") + paint.measureText("8") * 3 + leftLimit * 2;

            if(thisLogLineLength > recordLineWidth) { // If it breaks the line length record
                longestLoglineIndex = i;
                recordLineWidth = (int) thisLogLineLength;
            }
        }
        while(recordLineWidth > Screen.width) {
            lineGap -= 0.1;
            updateTextSize();
            updateLeftLimit();

            if(longestLoglineIndex == -1) {
                recordLineWidth = paint.measureText(log.date) + leftLimit * 2 + leftLimit * 2;
            } else {
                String logLineSubjectName = log.logLines.get(longestLoglineIndex).subject.name;

                // logLineNameBounds = bounds of subject name
                float logLineNameWidth = paint.measureText(logLineSubjectName);
                recordLineWidth = (int) (leftLimit * 2.5 + logLineNameWidth + paint.measureText("N-N") + paint.measureText("8") * 3);
            }
        }
        while(log.logLines.size() * lineGap + (Screen.height / 10f + lineGap * 3) > Screen.height) {
            lineGap -= 0.1;
        }
    }
    private void updateTextSize() {
        paint.setTextSize((float) (lineGap * 0.8));
    }
    private void updateLeftLimit() {
        leftLimit = (int) (lineGap / 2f);
    }
    private void drawHorizontalGridLines(Canvas canvas) {
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(Math.max(lineGap / 20f, 2));
        for(int i = (int) (TopBar.standardHeight + lineGap * 1.5); i <= Screen.height; i += lineGap) {
            canvas.drawLine(0, i, Screen.width, i, paint);
        }
    }
    private void drawDate(Canvas canvas) {
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(log.date, leftLimit, TopBar.standardHeight + lineGap + paint.getTextSize() / 3f, paint);
    }
    private void drawLoglines(Canvas canvas) {
        /* Log lines are drawn in the following manner:
         *               137 - Code
         *<= leftLimit =>888N-NCode<= leftLimit =>
         *
         * Do you follow the above? The upper text is what will actually be shown on the log. The lower text is the spacing I use to get everything aligned.
         */

        for(int i = 0; i < log.logLines.size(); i ++) {
            float txtYpos = (float) (
                    (TopBar.standardHeight + lineGap * 1.5) + // The height of the top bar
                            (lineGap * (i + 0.5)) + // Move down the log to the right spot
                            (paint.getTextSize() / 3f) // Centering the text vertically
            );
            canvas.drawText(
                    log.logLines.get(i).subject.name,
                    leftLimit + paint.measureText("8") * 3f + paint.measureText("N-N"),
                    txtYpos,
                    paint
            );
            canvas.drawText("-", leftLimit + paint.measureText("8") * 3 + paint.measureText("N"), txtYpos, paint);
            String dadTime = MyTime.getDadTime(log.logLines.get(i).startTime.hour, log.logLines.get(i).startTime.minute);
            canvas.drawText(String.valueOf(dadTime.charAt(0)), leftLimit, txtYpos, paint);
            canvas.drawText(String.valueOf(dadTime.charAt(1)), leftLimit + paint.measureText("8"), txtYpos, paint);
            canvas.drawText(String.valueOf(dadTime.charAt(2)), leftLimit + paint.measureText("8") * 2, txtYpos, paint);
        }
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
