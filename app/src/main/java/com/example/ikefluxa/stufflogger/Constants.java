package com.example.ikefluxa.stufflogger;

import android.content.Context;
import android.graphics.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

class Constants {
    static int mainClickingUserIndex = -1;
    static ArrayList<User> users = new ArrayList<>();
    static User deletedUser;
    static ArrayList<Integer> colors = new ArrayList<>(Arrays.asList(
            Color.rgb(255, 0, 0), // Red
            Color.rgb(0, 0, 255), // Blue
            Color.rgb(100, 255, 0), // Neon green
            Color.rgb(255, 44, 188), // Pink
            Color.rgb(0, 255, 255), // Turquoise
            Color.rgb(255, 157, 0), // Orange
            Color.rgb(193, 0, 255), // Purple
            Color.rgb(0, 255, 64), // Green
            Color.rgb(255, 255, 0), // Yellow
            Color.rgb(180, 80, 0) // Brown
    ));
    static void moveUserToFrontIndex(int index) {
        users.add(0, users.get(index));
        users.remove(index + 1);
    }
    static double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
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
    static int inverseColor(int color) {
        return Color.rgb(255-Color.red(color),
                255-Color.green(color),
                255-Color.blue(color));
    }
    static int darkenColor(int color, double factor) {
        return Color.rgb((int) (Color.red(color) * factor),
                (int) (Color.green(color) * factor),
                (int) (Color.blue(color) * factor)
        );
    }
    static void saveUserFiles(Context context) {
        // Erase previous files
        int i = 0;
        File file = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
        while(file.getAbsoluteFile().exists() && !file.isDirectory()) {
            context.deleteFile(file.getName());
            i ++;
            file = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
        }

        // Create new files
        i = 0;
        while(i < users.size()) {
            try {
                File outputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile));
                oos.writeObject(users.get(i));
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i ++;
        }
    }
    static void retrieveUserFiles(Context context) {
        if(users.size() == 0) {
            int i = 0;
            File inputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
            while (inputFile.exists() && !inputFile.isDirectory()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFile));
                    users.add((User) ois.readObject());
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
                inputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
            }
        } else {
            System.out.println("Oops, and error has occurred when retrieving user files. Users was:");
            System.out.println(users);
        }
    }
}
