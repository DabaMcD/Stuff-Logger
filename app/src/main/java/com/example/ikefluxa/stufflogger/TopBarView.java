package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TopBarView extends View {
    protected Paint paint = new Paint();
    protected String text = "Nothing for now";
    protected TextShadow textShadow = new TextShadow();
    protected RectShadow rectShadow = new RectShadow();
    protected Rect tBounds = new Rect();
    protected RectF rainbowRect = new RectF();
    private boolean rainbow = true;
    protected boolean textHasShadow = true;
    private int color; // Color is ranbow
    protected float height;
    public TopBarView(Context context) {
        super(context);
    }

    public TopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(rainbow) {
            drawRectShadow(canvas);
            drawRainbowRect(canvas);
        } else {
            drawRectWithShadow(canvas);
        }
        tweakTopBarTextSize();
        if(textHasShadow) {
            drawTextWithShadow(canvas);
        } else {
            drawTextWithoutShadow(canvas);
        }

        super.onDraw(canvas);
    }

    protected void drawTextWithShadow(Canvas canvas) {
        paint.setColor(Color.BLACK);
        textShadow.draw(text, Constants.SCREEN_WIDTH / 2, height / 2 + paint.getTextSize() / 3, canvas, paint);
    }

    protected void drawTextWithoutShadow(Canvas canvas) {
        canvas.drawText(text, Constants.SCREEN_WIDTH / 2, height / 2 + paint.getTextSize() / 3, paint);
    }

    private void tweakTopBarTextSize() {
        paint.setTextSize(Constants.SCREEN_HEIGHT / 10);
        paint.getTextBounds(text, 0, text.length(), tBounds);
        while(tBounds.width() > (Constants.SCREEN_WIDTH / 2) * 1.7) {
            paint.setTextSize(paint.getTextSize() - 1);
            paint.getTextBounds(text, 0, text.length(), tBounds);
        }
    }

    protected void drawRectWithShadow(Canvas canvas) {
        paint.setColor(color);
        rectShadow.draw(-20, -1, Constants.SCREEN_WIDTH + 1, height, canvas, paint);
    }

    protected void drawRainbowRect(Canvas canvas) {
        rainbowRect.top = 0;
        rainbowRect.bottom = height;
        for(int i = 0; i < Constants.SCREEN_WIDTH; i ++) {
            rainbowRect.left = i;
            rainbowRect.right = i + 1;
            paint.setColor(Color.HSVToColor(new float[] {(float) ((360.000 / Constants.SCREEN_WIDTH) * i), 1, 1}));
            canvas.drawRect(rainbowRect, paint);
        }
    }

    protected void drawRectShadow(Canvas canvas) {
        rectShadow.draw((float) -100, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 8, canvas, paint);
    }

    public void setColor(int color) {
        this.color = color;
        rainbow = false;
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
