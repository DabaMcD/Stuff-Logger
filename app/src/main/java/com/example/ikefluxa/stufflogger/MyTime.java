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
        if(Math.round(time.minute / 6) * 6 >= 10) {
            result += String.valueOf(time.hour + 1);
            result += "0";
        } else {
            result += String.valueOf(time.hour);
            result += Math.round(time.minute / 6) * 6;
        }
        return result;
    }
}
