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
    MainTopBarView mainTopBarView;

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
        topBar.draw((float) -100, 0, Constants.SCREEN_WIDTH, mainTopBarView.getHeight(), canvas, paint);
        super.onDraw(canvas);
    }

    public void draw(MainTopBarView mainTopBarView) {
        this.mainTopBarView = mainTopBarView;
        invalidate();
        requestLayout();
    }
}
