package com.example.ikefluxa.stufflogger;

import java.io.Serializable;

class LogLine implements Serializable {
    MyTime startTime;
    Subject subject;

    LogLine(MyTime startTime, Subject subject) {
        this.startTime = startTime;
        this.subject = subject;
    }
}
