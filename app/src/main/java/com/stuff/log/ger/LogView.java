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
    private float sideLimit;
    private float lineGap;
    private float firstLineYpos;
    private float width;
    private final float bufferAtBottom = 1.5f; // Measured in lineGaps

    public LogView(Context context) {
        super(context);
    }
    public LogView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init(float width) {
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

        // Make a nice short word (log) to represent the hash below
        log = Globals.users.get(0).logs.get(Globals.users.get(0).logs.size() - 1);
        lineGap = Screen.height / 10f; // Set min line gap
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (firstLineYpos - TopBar.standardHeight + lineGap * log.logLines.size() + lineGap * bufferAtBottom));
        this.width = width;

        tweakLineGap();
        updateSideLimit();
        updateFirstLineYpos();
        updateTextSize();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        drawHorizontalGridLines(canvas);
        drawDate(canvas);
        drawLoglines(canvas);

        super.onDraw(canvas);
    }
    private void tweakLineGap() {
        int longestLoglineIndex = -1;
        updateSideLimit();
        updateTextSize();
        float recordLineWidth = paint.measureText(log.date) + sideLimit * 2f;
        for(int i = 0; i < log.logLines.size(); i ++) {
            // Get the length of a log line (without left and right margins)
            float logLineNameWidth = paint.measureText(log.logLines.get(i).subject.name);
            float thisLogLineLength = logLineNameWidth + paint.measureText("888N-N") + sideLimit * 2f; // sideLimit * 2 because you gotta account for both sides

            if(thisLogLineLength > recordLineWidth) { // If it breaks the line length record
                longestLoglineIndex = i;
                recordLineWidth = (int) thisLogLineLength;
            }
        }
        while(recordLineWidth > width) {
            decreaseLineGap();
            updateTextSize();
            updateSideLimit();

            if(longestLoglineIndex == -1) {
                recordLineWidth = paint.measureText(log.date) + sideLimit * 2f; // sideLimit * 2 because you gotta account for both sides
            } else {
                String logLineSubjectName = log.logLines.get(longestLoglineIndex).subject.name;

                // logLineNameBounds = bounds of subject name
                float logLineNameWidth = paint.measureText(logLineSubjectName);
                recordLineWidth = (int) (sideLimit * 2f + logLineNameWidth + paint.measureText("888N-N"));
            }
        }
        updateFirstLineYpos();
        while(log.logLines.size() * lineGap + firstLineYpos /* and just for extra buffer at the bottom: */ + lineGap * bufferAtBottom > Screen.height) {
            decreaseLineGap();
            updateFirstLineYpos();
        }
    }
    private void decreaseLineGap() {
        lineGap /= 1.01d;
    }
    private void updateTextSize() {
        paint.setTextSize((float) (lineGap * 0.8d));
    }
    private void updateSideLimit() {
        sideLimit = (float) (lineGap / 2d);
    }
    private void updateFirstLineYpos() {
        firstLineYpos = (float) (TopBar.standardHeight + lineGap * 1.5);
    }
    private void drawHorizontalGridLines(Canvas canvas) {
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(Math.max(lineGap / 20f, 2));
        for(float i = firstLineYpos; i <= Screen.height; i += lineGap) {
            canvas.drawLine(0, i, width, i, paint);
        }
    }
    private void drawDate(Canvas canvas) {
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(log.date, sideLimit, TopBar.standardHeight + lineGap + paint.getTextSize() / 3f, paint);
    }
    private void drawLoglines(Canvas canvas) {
        /* THE X-POSITIONING CHART (if you can call it a chart)
         * Log lines are drawn in the following manner:
         *               137 - Code
         *<--sideLimit-->888N-NCode<--sideLimit-->
         *
         * Do you follow the above? The upper text is what will actually be shown on the log. The lower text is the spacing I use to get everything aligned.
         */

        // It is crucial that the text be sized and typefaced correctly at this point

        for(int i = 0; i < log.logLines.size(); i ++) {
            float txtYpos = (float) (
                    firstLineYpos + // The height of the top bar
                    (lineGap * (i + 0.5d)) + // Move down the log to the right spot
                    (paint.getTextSize() / 3d) // Centering the text vertically
            );

            String dadTime = MyTime.getDadTime(
                    log.logLines.get(i).startTime.hour,
                    log.logLines.get(i).startTime.minute
            );
            canvas.drawText(
                    String.valueOf(dadTime.charAt(0)), 
                    sideLimit,// Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
            canvas.drawText(
                    String.valueOf(dadTime.charAt(1)),
                    sideLimit + paint.measureText("8"), // Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
            canvas.drawText(
                    String.valueOf(dadTime.charAt(2)),
                    sideLimit + paint.measureText("88"), // Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
            canvas.drawText(
                    "-",
                    sideLimit + paint.measureText("888N"), // Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
            canvas.drawText(
                    log.logLines.get(i).subject.name,
                    sideLimit + paint.measureText("888N-N"), // Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
        }
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
