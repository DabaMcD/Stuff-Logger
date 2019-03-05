package com.stuff.log.ger;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class NewLogLineTopBarView extends View {
    public NewLogLineTopBarView(Context context) {
        super(context);
    }
    public NewLogLineTopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public NewLogLineTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        User user = Globals.users.get(0);
        TopBar.drawRectTextAndShadows(canvas, user.color, true, "Add Log Line (for " + user.name + ")");

        super.onDraw(canvas);
    }
    public void draw() {
        invalidate();
        requestLayout();
    }
}
