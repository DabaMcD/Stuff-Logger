package com.example.ikefluxa.stufflogger;

public class MyTime {
    private int hour;
    private int minute;

    MyTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
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
}
