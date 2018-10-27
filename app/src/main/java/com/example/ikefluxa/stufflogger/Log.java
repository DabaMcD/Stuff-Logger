package com.example.ikefluxa.stufflogger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

class Log implements Serializable {
    ArrayList<LogLine> logLines;
    String date;

    Log() {
        // Set date vals
        Calendar cal = Calendar.getInstance();
        date = // Wed, Feb 10, 2018
                weekdayFromInt(cal.get(Calendar.DAY_OF_WEEK)) + ", " + // Wed
                monthFromInt(cal.get(Calendar.MONTH)) + " " + // Feb
                cal.get(Calendar.DAY_OF_MONTH) + ", " + // 10
                cal.get(Calendar.YEAR); // 2018
        logLines = new ArrayList<>();
    }
    private String monthFromInt(int month) {
        switch(month) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Apr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Aug";
            case 8:
                return "Sep";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
            default:
                return "Invalid input";
        }
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
                return "Invalid number";
        }
    }
}
