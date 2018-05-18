package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class UsersListView extends View{
    Paint paint = new Paint();
    float lineThk = Constants.SCREEN_HEIGHT / 200;
    float userButtonHt = (float) (Constants.SCREEN_HEIGHT / 10.0);
    Rect userNameBounds = new Rect();

    Drawable trashCan = getResources().getDrawable(R.drawable.ic_trashcan);
    VectorDrawable vectorTrashCan;
    BitmapDrawable bitmapTrashCan;

    float trashX;
    float trashRad;
    int trashClickingIndex;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vectorTrashCan = (VectorDrawable) trashCan;
        } else {
            bitmapTrashCan = (BitmapDrawable) trashCan;
        }
        for(int i = 0; i < Constants.users.size(); i ++) {
            // Define some helpful stuff
            User user = Constants.users.get(i);
            float top = userButtonHt * i + lineThk * (i + 1);
            float bottom = top + userButtonHt;

            // Draw the line in between the users
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(-1, top - lineThk, Constants.SCREEN_WIDTH + 1, top, paint);

            // Draw the user's box
            paint.setColor(Color.LTGRAY);
            canvas.drawRect(-1, top, Constants.SCREEN_WIDTH + 1, bottom, paint);

            // Draw the user's name
            paint.setColor(Constants.darkenColor(user.color));
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(Constants.SCREEN_HEIGHT / 17);
            canvas.drawText(getLongestName(user.name), Constants.SCREEN_WIDTH / 28, top + (Constants.SCREEN_HEIGHT / 20) + (paint.getTextSize() / 3), paint);

            // See whether I have to use bitmap instead of vector (bitmap is depricated)
            int spaceY = (int) (bottom - top);
            int centerX = (int) (Constants.SCREEN_WIDTH * 0.85);
            int trashHeight = (spaceY * 2) / 3;
            int trashWidth = (int) (trashHeight * (7 / 10.0));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                vectorTrashCan.setBounds(
                        centerX - (trashWidth / 2),
                        (int) ((top + (spaceY / 2)) - (trashHeight / 2)),
                        centerX + (trashWidth / 2),
                        (int) ((top + (spaceY / 2)) + (trashHeight / 2))
                );
                vectorTrashCan.draw(canvas);
            } else {
                bitmapTrashCan.setBounds(
                        centerX - (trashWidth / 2),
                        (int) ((top + (spaceY / 2)) - (trashHeight / 2)),
                        centerX + (trashWidth / 2),
                        (int) ((top + (spaceY / 2)) + (trashHeight / 2))
                );
                bitmapTrashCan.draw(canvas);
            }
            trashX = centerX;
            trashRad = (float) (trashHeight * 0.65);

            // Draw dark rectangle over the box (only if being clicked)
            if(Constants.mainClickingUserIndex == i) {
                paint.setColor(Color.argb(30, 0, 0, 0));
                canvas.drawRect(-1, top, Constants.SCREEN_WIDTH + 1, bottom, paint);
            }
        }

        super.onDraw(canvas);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        System.out.println("SCROLL CHANGED");
        super.onScrollChanged(l, t, oldl, oldt);
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

    public int actionDown(float x, float y) {
        // If they touch down inside one of the boxes,
        // activate the touchedUserIndex and set it to that user.
        Constants.mainClickingUserIndex = -1;
        for(int i = 0; i < Constants.users.size(); i ++) {
            // Trash can button part
            float top = userButtonHt * trashClickingIndex + lineThk * (trashClickingIndex + 1);
            float bottom = top + userButtonHt;
            if (Constants.getDist(x, y, trashX, top + ((bottom - top) / 2)) < trashRad) {
                trashClickingIndex = i;
                break;
            }

            // User button part
            top = userButtonHt * Constants.mainClickingUserIndex + lineThk * (Constants.mainClickingUserIndex + 1);
            bottom = top + userButtonHt;
            if(y > top && y < bottom && Constants.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
                Constants.mainClickingUserIndex = i;
                break;
            }
        }
        return Constants.mainClickingUserIndex;
    } // Triggers on scroll start

    public int actionMove(float x, float y) {
        // Trash can button part
        float top = userButtonHt * trashClickingIndex + lineThk * (trashClickingIndex + 1);
        float bottom = top + userButtonHt;

        if (Constants.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
            trashClickingIndex = -1;
        }

        // User button part
        // If they go outside the box, deactivate the touchedUserIndex
        top = userButtonHt * Constants.mainClickingUserIndex + lineThk * (Constants.mainClickingUserIndex + 1);
        bottom = top + userButtonHt;
        if (y > top && y < bottom && Constants.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
            // Return the user clicked
            return Constants.mainClickingUserIndex;
        } else {
            // Reset things
            Constants.mainClickingUserIndex = -1;
            return -1;
        }
    } // Triggers on scroll move

    public int actionUp(float x, float y) {
        // Trash can button part
        float top = userButtonHt * trashClickingIndex + lineThk * (trashClickingIndex + 1);
        float bottom = top + userButtonHt;

        if (Constants.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
            trashClickingIndex = -1;
        } else {
            
        }

        // User button part
        // If they're still inside the box when their finger lets up,
        // and the touchedUserIndex is still activated, return the user index.
        top = userButtonHt * Constants.mainClickingUserIndex + lineThk * (Constants.mainClickingUserIndex + 1);
        bottom = top + userButtonHt;
        if (y > top && y < bottom && Constants.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
            // Return the user clicked
            return Constants.mainClickingUserIndex;
        } else {
            // Reset things
            Constants.mainClickingUserIndex = -1;
            return -1;
        }
    } // Does not trigger after scrolling

    public void draw(int clickingUserIndex) {
        Constants.mainClickingUserIndex = clickingUserIndex;
        invalidate();
        requestLayout();
    }
}
