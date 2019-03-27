package com.stuff.log.ger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

class Log implements Serializable {
    ArrayList<LogLine> logLines;
    String date;

    Log() {
        date = MyTime.getCurrentPrettyDate();
        logLines = new ArrayList<>();
    }
}
