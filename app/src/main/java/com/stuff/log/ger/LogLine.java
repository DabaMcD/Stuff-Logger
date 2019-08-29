package com.stuff.log.ger;

import android.graphics.Paint;

import java.io.Serializable;

class LogLine implements Serializable {
    MyTime startTime;
    Subject subject;

    LogLine(MyTime startTime, Subject subject) {
        this.startTime = startTime;
        this.subject = subject;
    }
    static float getLogLineWidth(LogLine logLine, Paint paint) {
        return paint.measureText("888N-N" + logLine);
    }
}
