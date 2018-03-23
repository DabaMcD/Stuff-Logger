package com.example.ikefluxa.stufflogger;

/**
 * Created by Ike&Fluxa on 3/21/2018.
 */

public class MyTime {
    public int hour;
    public int minute;
    public int second;
    public MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public static String getDadTime(MyTime time) {
        String result = "";
        if(Math.round((double) time.minute / (double) 6) >= 10) {
            if(String.valueOf(time.hour + 1).length() <= 1) {
                result += "0";
            }
            result += String.valueOf(time.hour + 1);
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
}
