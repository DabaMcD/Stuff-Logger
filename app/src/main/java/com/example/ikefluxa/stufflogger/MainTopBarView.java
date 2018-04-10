package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MainTopBarView extends View {
    Paint paint = new Paint();
    RectShadow topBar = new RectShadow();
    RectF rainbowRect = new RectF();
    TextShadow topBarText = new TextShadow();

    public MainTopBarView(Context context) {
        super(context);
    }

    public MainTopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MainTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw top bar
        paint.setColor(Color.BLACK);
        topBar.draw(-100, -1, Constants.SCREEN_WIDTH + 100, Constants.SCREEN_HEIGHT / 10, canvas, paint); // Just for the shadow
        drawRainbowTopBar(canvas);

        // Draw name on top bar
        drawText(canvas);
        super.onDraw(canvas);
    }

    private void drawRainbowTopBar(Canvas canvas) {
        rainbowRect.top = 0;
        rainbowRect.bottom = Constants.SCREEN_HEIGHT / 10;
        for(int i = 0; i < Constants.SCREEN_WIDTH; i ++) {
            rainbowRect.left = i;
            rainbowRect.right = i + 1;
            paint.setColor(Color.HSVToColor(new float[] {(float) ((360.000 / Constants.SCREEN_WIDTH) * i), 100, 100}));
            canvas.drawRect(rainbowRect, paint);
        }
    }

    private void drawText(Canvas canvas) {
        // Draw text "STUFF LOGGER"
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setTextSize(Constants.SCREEN_HEIGHT / 17);
        paint.setColor(Color.DKGRAY);
        topBarText.draw("Stuff Logger", Constants.SCREEN_WIDTH / 28, Constants.SCREEN_HEIGHT / 20 + paint.getTextSize() / 3, canvas, paint);
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
