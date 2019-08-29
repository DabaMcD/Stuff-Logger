package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LogLogLinesListView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Log log;
    private float sideMargin;
    private float lineGap;
    private float firstLineYpos;
    private float width;
    private final float bufferAtBottom = 1.5f; // Measured in lineGaps

    public LogLogLinesListView(Context context) {
        super(context);
    }
    public LogLogLinesListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogLogLinesListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init(float width, float logTopBarHeight) {
        this.width = width;
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        // Make a nice short word (log) to represent the hash below
        log = Globals.users.get(0).logs.get(Globals.users.get(0).logs.size() - 1);
        lineGap = Screen.height / 10f; // Set min line gap

        updateSideMargin();
        updateFirstLineYpos();
        updateTextSize();

        // Scrolling stuff
        setVerticalScrollBarEnabled(true);
        setMinimumHeight(100);
        int bottomOfLogLines = (int) (firstLineYpos + lineGap * log.logLines.size() + lineGap * bufferAtBottom);
        int minScrollHeight = (int) (Screen.height - logTopBarHeight);
        setMinimumHeight(bottomOfLogLines > minScrollHeight ? bottomOfLogLines : minScrollHeight);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        try {
            tweakLineGap();
            drawHorizontalGridLines(canvas);
            drawDate(canvas);
            drawLogLines(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDraw(canvas);
    }
    private void tweakLineGap() {
        // Goal: set the line gap correctly so that the length of the longest piece of
        // text will perfectly fit with perfect margin on both sides
        //
        // Once I know how long the text should be, I can reverse calculate through to the
        // line gap using dimensional analysis using the following ratios:
        // paint.measureText(text) / paint.getTextSize() = (I'll have to calculate this on the fly)
        // paint.getTextSize() / lineGap = 0.8

    }
    private void updateTextSize() {
        paint.setTextSize((float) (lineGap * 0.8d));
    }
    private void updateSideMargin() {
        sideMargin = (float) (lineGap / 2d);
    }
    private void updateFirstLineYpos() {
        firstLineYpos = (float) (lineGap * 1.5);
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
        canvas.drawText(log.date, sideMargin, lineGap + paint.getTextSize() / 3f, paint);
    }
    private void drawLogLines(Canvas canvas) {
        /* THE X-POSITIONING CHART (if you can call it a chart)
         * Log lines are drawn in the following manner:
         *               137 - Code
         *<--sideMargin-->888N-NCode<--sideMargin-->
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

            String dadTime = log.logLines.get(i).startTime.getDadTime();
            canvas.drawText(
                    String.valueOf(dadTime.charAt(0)),
                    sideMargin,// Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
            canvas.drawText(
                    String.valueOf(dadTime.charAt(1)),
                    sideMargin + paint.measureText("8"), // Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
            canvas.drawText(
                    String.valueOf(dadTime.charAt(2)),
                    sideMargin + paint.measureText("88"), // Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
            canvas.drawText(
                    "-",
                    sideMargin + paint.measureText("888N"), // Correct x-position according to the chart above
                    txtYpos,
                    paint
            );
            canvas.drawText(
                    log.logLines.get(i).subject.name,
                    sideMargin + paint.measureText("888N-N"), // Correct x-position according to the chart above
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
