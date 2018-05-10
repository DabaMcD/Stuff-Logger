package com.example.ikefluxa.stufflogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

public class TopBarView {
    public static float standardHeight;
    private static Paint paint = new Paint();
    private static TextShadow textShadow = new TextShadow();
    private static RectShadow rectShadow = new RectShadow();
    private static Rect tBounds = new Rect();
    private static RectF rainbowRect = new RectF();
    private static int color; // Color of rect is rainbow until defined

    public static void drawRectTextAndShadows(Canvas canvas, int color12345ForRainbow, boolean textHasShadow, String text) {
        if(color12345ForRainbow == 12345) {
            drawRectShadow(canvas);
            drawRainbowRect(canvas);
        } else {
            TopBarView.color = color12345ForRainbow;
            drawRectWithShadow(canvas, color12345ForRainbow);
        }
        tweakTopBarTextSize(text);
        if(textHasShadow) {
            drawTextWithShadow(canvas, text);
        } else {
            drawTextWithoutShadow(canvas, text);
        }
    }

    public static void drawTextWithShadow(Canvas canvas, String text) {
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        textShadow.draw(text, Constants.SCREEN_WIDTH / 2, standardHeight / 2 + paint.getTextSize() / 3, canvas, paint);
    }

    public static void drawTextWithoutShadow(Canvas canvas, String text) {
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, Constants.SCREEN_WIDTH / 2, standardHeight / 2 + paint.getTextSize() / 3, paint);
    }

    public static void tweakTopBarTextSize(String text) {
        paint.setTextSize(Constants.SCREEN_HEIGHT / 10);
        paint.getTextBounds(text, 0, text.length(), tBounds);
        while(tBounds.width() > (Constants.SCREEN_WIDTH / 2) * 1.7) {
            paint.setTextSize(paint.getTextSize() - 1);
            paint.getTextBounds(text, 0, text.length(), tBounds);
        }
    }

    public static void drawRectWithShadow(Canvas canvas, int color) {
        paint.setColor(color);
        rectShadow.draw(-20, -1, Constants.SCREEN_WIDTH + 1, standardHeight, canvas, paint);
    }

    public static void drawRainbowRect(Canvas canvas) {
        rainbowRect.top = 0;
        rainbowRect.bottom = standardHeight;
        for(int i = 0; i < Constants.SCREEN_WIDTH; i ++) {
            rainbowRect.left = i;
            rainbowRect.right = i + 1;
            paint.setColor(Color.HSVToColor(new float[] {(float) ((360.000 / Constants.SCREEN_WIDTH) * i), 1, 1}));
            canvas.drawRect(rainbowRect, paint);
        }
    }

    public static void drawRectShadow(Canvas canvas) {
        paint.setColor(Color.BLACK);
        rectShadow.draw((float) -100, 0, Constants.SCREEN_WIDTH, standardHeight, canvas, paint);
    }
}
