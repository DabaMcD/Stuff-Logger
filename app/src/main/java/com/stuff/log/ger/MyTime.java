package com.stuff.log.ger;

import java.io.Serializable;
import java.util.Calendar;

class MyTime implements Serializable {
    private int year;
    private int month;
    private int dayOfWeek;
    private int dayOfMonth;
    private int hour;
    private int minute;
    private int second; // not needed now but maybe later
    private int millis;

    MyTime() {
        year = getCurrentYear();
        month = getCurrentMonth();
        dayOfWeek = getCurrentDayOfWeek();
        dayOfMonth = getCurrentDayOfMonth();
        hour = getCurrentHour();
        minute = getCurrentMinute();
        second = getCurrentSecond();
        millis = getCurrentMillis();
    }
    static String getDadTime(MyTime time) {
        String result = "";
        if(Math.round((double) time.minute / (double) 6) >= 10) {
            if(String.valueOf((time.hour + 1) % 24).length() <= 1) {
                result += "0";
            }
            result += String.valueOf((time.hour + 1) % 24);
            result += "0";
        } else {
            if(String.valueOf(time.hour).length() <= 1) {
                result += "0";
            }
            result += String.valueOf(time.hour);
            result += Math.round((double) time.minute / (double) 6);
        }
        return result;
    }

    // Current time references (all return and int)
    static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    static int getCurrentDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
    static int getCurrentDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    static int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    static int getCurrentMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    static int getCurrentSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }
    static int getCurrentMillis() {
        return Calendar.getInstance().get(Calendar.MILLISECOND);
    }
    static String monthFromInt(int month) {
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
    static String weekdayFromInt(int day) {
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

    static String getCurrentPrettyDate() {
        // Returns the date in the form   Mon, Feb 19, 2018
        return weekdayFromInt(getCurrentDayOfWeek()) + ", " + // Mon,
                monthFromInt(getCurrentMonth()) + " " + // Feb
                getCurrentDayOfMonth() + ", " + // 19,
                getCurrentYear(); // 2018
    }
    static String getCurrentShortDate() {
        // Returns the date in the form   19/02/2018
        return getCurrentDayOfMonth() + "/" + // 19/
                getCurrentMonth() + "/" + // 02/
                getCurrentYear(); // 2018
        // todo: use a println to make sure the functions above work
    }
}
