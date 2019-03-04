package com.stuff.log.ger;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LogActivityDividerView extends View {
    private float lineThk;
    public LogActivityDividerView(Context context) {
        super(context);
    }
    public LogActivityDividerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LogActivityDividerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void init() {
        // Once the log activity calls this function, the Screen dimensions will be up-to-date
        lineThk = Screen.width / 50f;
    }
}
