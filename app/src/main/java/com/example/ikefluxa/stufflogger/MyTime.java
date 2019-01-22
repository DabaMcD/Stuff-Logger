package com.example.ikefluxa.stufflogger;

import java.io.Serializable;
import java.util.Calendar;

class MyTime implements Serializable {
    int hour;
    int minute;
    int second; // not needed now but maybe later

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
    // todo: test the below somehow
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
}
