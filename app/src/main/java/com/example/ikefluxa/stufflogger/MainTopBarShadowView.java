package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MainTopBarShadowView extends View {
    Paint paint = new Paint();
    RectShadow topBar = new RectShadow();

    public MainTopBarShadowView(Context context) {
        super(context);
    }

    public MainTopBarShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MainTopBarShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        topBar.draw((float) -100, (float) (Constants.SCREEN_HEIGHT / 20), (float) Constants.SCREEN_WIDTH, (float) (Constants.SCREEN_HEIGHT / 10), canvas, paint);
        super.onDraw(canvas);
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
