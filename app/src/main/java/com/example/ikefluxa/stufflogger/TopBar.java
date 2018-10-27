package com.example.ikefluxa.stufflogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

class TopBar {
    static float standardHeight;
    private static Paint paint = new Paint();
    static TextShadow textShadow = new TextShadow();
    private static RectShadow rectShadow = new RectShadow();
    private static Rect tBounds = new Rect();
    private static RectF rainbowRect = new RectF();
    public static int color; // Color of rect is rainbow until defined

    static void drawRectTextAndShadows(Canvas canvas, int color12345ForRainbow, boolean textHasShadow, String text) {
        if(color12345ForRainbow == 12345) {
            drawRectShadow(canvas);
            drawRainbowRect(canvas);
        } else {
            color = color12345ForRainbow;
            drawRectWithShadow(canvas, color12345ForRainbow);
        }
        tweakTopBarTextSize(text);
        if(textHasShadow) {
            drawTextWithShadow(canvas, text);
        } else {
            drawTextWithoutShadow(canvas, text);
        }
    }
    static void drawTextWithShadow(Canvas canvas, String text) {
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        textShadow.draw(text, Constants.SCREEN_WIDTH / 2, standardHeight / 2 + paint.getTextSize() / 3, canvas, paint);
    }
    static void drawTextWithoutShadow(Canvas canvas, String text) {
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, Constants.SCREEN_WIDTH / 2, standardHeight / 2 + paint.getTextSize() / 3, paint);
    }
    static void tweakTopBarTextSize(String text) {
        paint.setTextSize(Constants.SCREEN_HEIGHT / 10);
        paint.getTextBounds(text, 0, text.length(), tBounds);
        while(tBounds.width() > (Constants.SCREEN_WIDTH / 2) * 1.7) {
            paint.setTextSize(paint.getTextSize() - 1);
            paint.getTextBounds(text, 0, text.length(), tBounds);
        }
    }
    static void drawRectWithShadow(Canvas canvas, int color) {
        paint.setColor(color);
        rectShadow.draw(-20, -1, Constants.SCREEN_WIDTH + 1, standardHeight, canvas, paint);
    }
    static void drawRainbowRect(Canvas canvas) {
        rainbowRect.top = 0;
        rainbowRect.bottom = standardHeight;
        for(int i = 0; i < Constants.SCREEN_WIDTH; i ++) {
            rainbowRect.left = i;
            rainbowRect.right = i + 1;
            paint.setColor(Color.HSVToColor(new float[] {(float) ((360.000 / Constants.SCREEN_WIDTH) * i), 1, 1}));
            canvas.drawRect(rainbowRect, paint);
        }
    }
    static void drawRectShadow(Canvas canvas) {
        paint.setColor(Color.BLACK);
        rectShadow.draw((float) -100, 0, Constants.SCREEN_WIDTH, standardHeight, canvas, paint);
    }
}
