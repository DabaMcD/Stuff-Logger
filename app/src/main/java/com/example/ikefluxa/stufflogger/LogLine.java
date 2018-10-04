package com.example.ikefluxa.stufflogger;


import java.io.Serializable;

public class LogLine implements Serializable {
    public MyTime startTime;
    public Subject subject;

    LogLine(MyTime startTime, Subject subject) {
        this.startTime = startTime;
        this.subject = subject;
    }
}
