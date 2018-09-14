package com.example.ikefluxa.stufflogger;

public class LogLine {
    public MyTime startTime;
    public Subject subject;

    LogLine(MyTime startTime, Subject subject) {
        this.startTime = startTime;
        this.subject = subject;
    }
}
