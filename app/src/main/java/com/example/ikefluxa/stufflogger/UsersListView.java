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
    float lineThk = Constants.SCREEN_HEIGHT / 100;
    Rect userNameBounds = new Rect();

    public UsersListView(Context context) {
        super(context);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Constants.SCREEN_HEIGHT / 10) + lineThk) * (Constants.users.size() + 1) - lineThk));
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Constants.SCREEN_HEIGHT / 10) + lineThk) * (Constants.users.size() + 1) - lineThk));
    }

    public UsersListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Constants.SCREEN_HEIGHT / 10) + lineThk) * (Constants.users.size() + 1) - lineThk));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i < Constants.users.size(); i ++) {
            // Define some helpful stuff
            User user = Constants.users.get(i);
            int bob = (int) (Constants.SCREEN_HEIGHT / 10.0);
            float top = bob * (i + 1) + lineThk * (i + 1);
            float bottom = top + bob;

            // Draw the line in between the users
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(0, top - lineThk, Constants.SCREEN_WIDTH, top, paint);

            // Draw the user's box
            paint.setColor(Constants.inverseColor(user.color));
            canvas.drawRect(0, top, Constants.SCREEN_WIDTH, bottom, paint);

            // Draw the user's name
            paint.setColor(user.color);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTextSize(Constants.SCREEN_HEIGHT / 17);
            canvas.drawText(getLongestName(user.name), Constants.SCREEN_WIDTH / 28, top + (Constants.SCREEN_HEIGHT / 20) - (paint.getTextSize() / 3), paint);
        }

        super.onDraw(canvas);
    }

    private String getLongestName(String name) {
        String result = "";
        paint.getTextBounds(name, 0, name.length(), userNameBounds);

        return result;
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
