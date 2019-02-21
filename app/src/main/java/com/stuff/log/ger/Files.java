package com.stuff.log.ger;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Files {
    static void eraseFiles(Context context) {
        int i = 0;
        File file = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
        while(file.getAbsoluteFile().exists() && !file.isDirectory()) {
            context.deleteFile(file.getName());
            i ++;
            file = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
        }
    }
    static void reSave(Context context) {
        eraseFiles(context);

        // Create new files
        int i = 0;
        while(i < Globals.users.size()) {
            try {
                File outputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile));
                oos.writeObject(Globals.users.get(i));
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i ++;
        }
    }
    static void retrieve(Context context) {
        if(Globals.users.size() == 0) {
            int i = 0;
            File inputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
            while (inputFile.exists() && !inputFile.isDirectory()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFile));
                    Globals.users.add((User) ois.readObject());
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
                inputFile = new File(context.getFilesDir(), "User" + String.valueOf(i) + ".txt");
            }
        } else {
            System.out.println("Oops, and error has occurred when retrieving user files. Users was:");
            System.out.println(Globals.users);
        }
    }
}
