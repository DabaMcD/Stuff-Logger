package com.example.ikefluxa.stufflogger;

import java.sql.Time;

/**
 * Created by Ike&Fluxa on 2/19/2018.
 */

public class LogLine {
    public Time startTime;
    public Subject subject;

    public LogLine(Time time, Subject subject) {
        startTime = time;
        this.subject = subject;
    }
}
