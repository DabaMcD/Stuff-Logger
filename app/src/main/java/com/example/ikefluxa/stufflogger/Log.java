package com.example.ikefluxa.stufflogger;

import java.util.ArrayList;
import java.util.Calendar;

public class Log {
    public ArrayList<LogLine> logLines;

    // Date stuff
    public String date;
    public int weekdayInt; // Zero-indexed
    public int monthInt; // 1-indexed, starting at Sunday
    public String weekday; // First three letters
    public String month; // First three letters
    public int day;
    public int year;

    public Log() {
        // Set date vals
        Calendar cal = Calendar.getInstance();
        monthInt = cal.get(Calendar.MONTH);
        month = monthFromInt(monthInt);
        day = cal.get(Calendar.DAY_OF_MONTH);
        weekdayInt = cal.get(Calendar.DAY_OF_WEEK);
        weekday = weekdayFromInt(weekdayInt);
        year = cal.get(Calendar.YEAR);
        date = weekday + ", " + month + " " + day + ", " + year;

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
                return "Invaid number";
        }
    }
}
