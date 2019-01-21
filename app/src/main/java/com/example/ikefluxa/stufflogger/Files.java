package com.example.ikefluxa.stufflogger;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Files {
    static void save(Context context) {
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
        while(i < Constants.users.size()) {
            try {
                File outputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile));
                oos.writeObject(Constants.users.get(i));
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i ++;
        }
    }
    static void retrieve(Context context) {
        if(Constants.users.size() == 0) {
            int i = 0;
            File inputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
            while (inputFile.exists() && !inputFile.isDirectory()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFile));
                    Constants.users.add((User) ois.readObject());
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
                inputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
            }
        } else {
            System.out.println("Oops, and error has occurred when retrieving user files. Users was:");
            System.out.println(Constants.users);
        }
    }
}
