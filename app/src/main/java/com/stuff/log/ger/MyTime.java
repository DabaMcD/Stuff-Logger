package com.stuff.log.ger;

import java.io.Serializable;
import java.util.Calendar;

class MyTime implements Serializable {
    int hour;
    int minute;
    int second; // not needed now but maybe later
    int day;
    int

    MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    MyTime() {
        hour = getHour();
        minute = getMinute();
        second = getSecond();
    }
    static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    static int getSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }
    static int getMillis() {
        return Calendar.getInstance().get(Calendar.MILLISECOND);
    }
    static String getDadTime(int hour, int minute) {
        String result = "";
        if(Math.round((double) minute / (double) 6) >= 10) {
            if(String.valueOf((hour + 1) % 24).length() <= 1) {
                result += "0";
            }
            result += String.valueOf((hour + 1) % 24);
            result += "0";
        } else {
            if(String.valueOf(hour).length() <= 1) {
                result += "0";
            }
            result += String.valueOf(hour);
            result += Math.round((double) minute / (double) 6);
        }
        return result;
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
    static String getDisplayableDate() {
        // Returns the date in the form   Mon, Feb 19, 2018
        Calendar cal = Calendar.getInstance();
        return weekdayFromInt(cal.get(Calendar.DAY_OF_WEEK)) + ", " + // Mon,
                monthFromInt(cal.get(Calendar.MONTH)) + " " + // Feb
                cal.get(Calendar.DAY_OF_MONTH) + ", " + // 19,
                cal.get(Calendar.YEAR); // 2018
    }
}
