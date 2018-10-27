package com.example.ikefluxa.stufflogger;

import java.io.Serializable;
import java.util.ArrayList;

 class User implements Serializable {
    String name;
    ArrayList<Subject> subjects = new ArrayList<>();
    int color;
    ArrayList<Log> logs = new ArrayList<>();

    User(String name) {
        this.name = name;
        color = Constants.colors.get((int) Math.floor(Math.random() * Constants.colors.size()));
        logs.add(new Log());
    }
    User(String name, int color) {
        this.name = name;
        this.color = color;
    }
    void newLog() {
        logs.add(new Log());
    }
}
