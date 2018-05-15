package com.example.ikefluxa.stufflogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Log {
    public String date;
    public ArrayList<LogLine> logLines;

    // Date stuff
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
    String weekday; // First three letters
    String month; // First three letters
    int day;
    int year;

    public Log() {
        // Set date vals
        Calendar cal = Calendar.getInstance();
        month = simpleDateFormat.format(cal.getTime());
        day = cal.get(cal.DAY_OF_MONTH);
        weekday = weekdayFromInt(cal.get(cal.DAY_OF_WEEK));
        year = cal.get(cal.YEAR);
        date = weekday + ", " + month + " " + day + ", " + year;
    }

    private String weekdayFromInt(int day) {
        switch(day) {
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            case Calendar.SUNDAY:
                return "Sun";
            default:
                return "Invaid number";
        }
    }
}
