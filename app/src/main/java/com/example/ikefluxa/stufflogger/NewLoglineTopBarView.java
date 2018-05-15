package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class NewLoglineTopBarView extends View {
    public NewLoglineTopBarView(Context context) {
        super(context);
    }

    public NewLoglineTopBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewLoglineTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        User user = Constants.users.get(Constants.currentUserIndex);
        TopBar.drawRectTextAndShadows(canvas, user.color, true, "Add Logline (for " + user.name + ")");

        super.onDraw(canvas);
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
