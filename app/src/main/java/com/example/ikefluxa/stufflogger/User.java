package com.example.ikefluxa.stufflogger;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String name;
    public ArrayList<Subject> subjects = new ArrayList<>();
    public int color;

    ArrayList<Log> logs = new ArrayList<>();

    User(String name) {
        this.name = name;
        color = Constants.colors.get((int) Math.floor(Math.random() * Constants.colors.size()));
        logs.add(new Log());
    }

    public User(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public void newLog() {
        logs.add(new Log());
    }
}
