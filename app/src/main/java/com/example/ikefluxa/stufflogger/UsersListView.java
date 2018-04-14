package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class UsersListView extends View{
    Paint paint = new Paint();
    float lineThk = Constants.SCREEN_HEIGHT / 200;
    Rect userNameBounds = new Rect();

    public UsersListView(Context context) {
        super(context);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Constants.SCREEN_HEIGHT / 10) + lineThk) * Constants.users.size()));
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Constants.SCREEN_HEIGHT / 10) + lineThk) * Constants.users.size()));
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Constants.SCREEN_HEIGHT / 10) + lineThk) * Constants.users.size()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i < Constants.users.size(); i ++) {
            // Define some helpful stuff
            User user = Constants.users.get(i);
            int bob = (int) (Constants.SCREEN_HEIGHT / 10.0);
            float top = bob * i + lineThk * (i + 1);
            float bottom = top + bob;

            // Draw the line in between the users
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(0, top - lineThk, Constants.SCREEN_WIDTH, top, paint);

            // Draw the user's box
            paint.setColor(Color.LTGRAY);
            canvas.drawRect(0, top, Constants.SCREEN_WIDTH, bottom, paint);

            // Draw the user's name
            paint.setColor(Constants.darkenColor(user.color));
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(Constants.SCREEN_HEIGHT / 17);
            canvas.drawText(getLongestName(user.name), Constants.SCREEN_WIDTH / 28, top + (Constants.SCREEN_HEIGHT / 20) + (paint.getTextSize() / 3), paint);
        }

        super.onDraw(canvas);
    }

    private String getLongestName(String name) {
        Boolean dotdotdot = false;
        String result = name;
        int maxRight = (Constants.SCREEN_WIDTH / 3) * 2;
        paint.getTextBounds(result, 0, name.length(), userNameBounds);
        while(userNameBounds.width() + (Constants.SCREEN_WIDTH / 28) > maxRight) {
            result = result.substring(0, result.length() - 1);
            paint.getTextBounds(result + "...", 0, result.length() + 3, userNameBounds);
            dotdotdot = true;
        }
        return result + (dotdotdot ? "..." : "");
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
