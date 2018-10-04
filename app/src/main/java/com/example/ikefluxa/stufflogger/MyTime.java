package com.example.ikefluxa.stufflogger;

import java.io.Serializable;

public class MyTime implements Serializable {
    public int hour;
    public int minute;
    private int second; // not needed now but maybe later

    MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public static String getDadTime(int hour, int minute) {
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
