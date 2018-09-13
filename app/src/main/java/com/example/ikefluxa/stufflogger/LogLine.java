package com.example.ikefluxa.stufflogger;


import java.io.Serializable;

/**
 * Created by Ike&Fluxa on 2/19/2018.
 */

public class LogLine implements Serializable {
    public MyTime startTime;
    public Subject subject;

    public LogLine(MyTime startTime, Subject subject) {
        this.startTime = startTime;
        this.subject = subject;
    }
}
