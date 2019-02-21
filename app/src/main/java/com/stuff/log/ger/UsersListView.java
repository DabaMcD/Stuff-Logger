package com.stuff.log.ger;

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
    private Paint paint = new Paint();
    private float lineThk = Screen.height / 200;
    private float userButtonHt = (float) (Screen.height / 10.0);
    private Rect userNameBounds = new Rect();

    private Drawable trashCan = getResources().getDrawable(R.drawable.ic_trashcan);
    private VectorDrawable vectorTrashCan;
    private BitmapDrawable bitmapTrashCan;
    private float trashX;
    private float trashRad;
    private int trashClickingIndex = -1;
    private int touchingUserIndex = -1;

    public UsersListView(Context context) {
        super(context);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Screen.height / 10) + lineThk) * Globals.users.size()));
    }
    public UsersListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Screen.height / 10) + lineThk) * Globals.users.size()));
    }
    public UsersListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVerticalScrollBarEnabled(true);
        setMinimumHeight((int) (((Screen.height / 10) + lineThk) * Globals.users.size()));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vectorTrashCan = (VectorDrawable) trashCan;
        } else {
            bitmapTrashCan = (BitmapDrawable) trashCan;
        }
        for(int i = 0; i < Globals.users.size(); i ++) {
            // Define some helpful stuff
            User user = Globals.users.get(i);
            float top = userButtonHt * i + lineThk * (i + 1);
            float bottom = top + userButtonHt;

            // Draw the line in between the users
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(-1, top - lineThk, Screen.width + 1, top, paint);

            // Draw the user's box
            paint.setColor(Color.LTGRAY);
            canvas.drawRect(-1, top, Screen.width + 1, bottom, paint);

            // Draw the user's name
            paint.setColor(Globals.darkenColor(user.color, 0.67));
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(Screen.height / 17);
            canvas.drawText(getLongestName(user.name), Screen.width / 28, top + (Screen.height / 20) + (paint.getTextSize() / 3), paint);

            // See whether I have to use bitmap instead of vector (bitmap is deprecated)
            int spaceY = (int) (bottom - top);
            int centerX = (int) (Screen.width * 0.85);
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
            if(touchingUserIndex == i) {
                paint.setColor(Color.argb(30, 0, 0, 0));
                canvas.drawRect(-1, top, Screen.width + 1, bottom, paint);
            }

            // Draw dark circle over trash can (only if being clicked)
            if(trashClickingIndex == i) {
                paint.setColor(Color.argb(30, 0, 0, 0));
                canvas.drawCircle(trashX, top + (spaceY / 2), trashRad, paint);
            }
        }

        super.onDraw(canvas);
    }
    private String getLongestName(String name) {
        Boolean dotDotDot = false;
        String result = name;
        int maxRight = (Screen.width / 3) * 2;
        paint.getTextBounds(result, 0, name.length(), userNameBounds);
        while(userNameBounds.width() + (Screen.width / 28) > maxRight) {
            result = result.substring(0, result.length() - 1);
            paint.getTextBounds(result + "...", 0, result.length() + 3, userNameBounds);
            dotDotDot = true;
        }
        return result + (dotDotDot ? "..." : "");
    }
    int actionDown(float x, float y) {
        // If they touch down inside one of the boxes,
        // activate the touchedUserIndex and set it to that user.
        touchingUserIndex = -1;
        for(int i = 0; i < Globals.users.size(); i ++) {
            // Trash can button part
            float top = userButtonHt * i + lineThk * (i + 1);
            float bottom = top + userButtonHt;

            if (Globals.getDist(x, y, trashX, top + ((bottom - top) / 2)) < trashRad) {
                trashClickingIndex = i;
                break;
            }

            // User button part
            if(y > top && y < bottom && Globals.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
                touchingUserIndex = i;
                break;
            }
        }
        return touchingUserIndex;
    } // Triggers on scroll start
    int actionMove(float x, float y) {
        // Trash can button part
        float top = userButtonHt * trashClickingIndex + lineThk * (trashClickingIndex + 1);
        float bottom = top + userButtonHt;

        if (Globals.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
            trashClickingIndex = -1;
        }

        // User button part
        // If they go outside the box, deactivate the touchedUserIndex
        top = userButtonHt * touchingUserIndex + lineThk * (touchingUserIndex + 1);
        bottom = top + userButtonHt;
        if (y > top && y < bottom && Globals.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
            // Return the user clicked
            return touchingUserIndex;
        } else {
            // Reset things
            touchingUserIndex = -1;
            return -1;
        }
    } // Triggers on scroll move
    int actionUpTrashButton(float x, float y) {
        float top = userButtonHt * trashClickingIndex + lineThk * (trashClickingIndex + 1);
        float bottom = top + userButtonHt;
        if (Globals.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
            trashClickingIndex = -1;
            return -1;
        } else {
            return trashClickingIndex;
        }
    }
    int actionUpUserButton(float x, float y) {
        // If they're still inside the box when their finger lets up,
        // and the touchedUserIndex is still activated, return the user index.
        float top = userButtonHt * touchingUserIndex + lineThk * (touchingUserIndex + 1);
        float bottom = top + userButtonHt;
        if (y > top && y < bottom && Globals.getDist(x, y, trashX, top + ((bottom - top) / 2)) >= trashRad) {
            // Return the user clicked
            return touchingUserIndex;
        } else {
            // Reset things
            touchingUserIndex = -1;
            return -1;
        }
    } // Does not trigger after scrolling
    void draw(int clickingUserIndex) {
        touchingUserIndex = clickingUserIndex;
        invalidate();
        requestLayout();
    }
}
