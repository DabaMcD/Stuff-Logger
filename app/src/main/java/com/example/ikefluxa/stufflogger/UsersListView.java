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

public class UsersListView extends View{
    Paint paint = new Paint();
    float lineThk = Constants.SCREEN_HEIGHT / 300;

    public UsersListView(Context context) {
        super(context);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight(100); // ToDo: Fix this babby thing
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight(100);
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i < Constants.users.size(); i ++) {
            // Define some helpful stuff
            User user = Constants.users.get(i);
            int bob = (int) (Constants.SCREEN_HEIGHT / 10.0);
            float top = bob * (i + 1) + lineThk * (i + 1);
            float bottom = (float) ((Constants.SCREEN_HEIGHT / 10.0) * (i + 2) + lineThk * (i + 1));

            // Draw the line in between the users
//            paint.setColor(Color.DKGRAY);
//            canvas.drawRect(0, top - lineThk, Constants.SCREEN_WIDTH, top, paint);

            // Draw the user's box
            paint.setColor(user.color);
            canvas.drawRect(0, top, Constants.SCREEN_WIDTH, bottom, paint);
        }

        super.onDraw(canvas);
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
