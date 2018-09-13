package com.example.ikefluxa.stufflogger;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ike&Fluxa on 2/19/2018.
 */

public class User implements Serializable {
    public String name;
    public ArrayList<Subject> subjects = new ArrayList<>(); // todo: do we really need this??
    public int color;

    ArrayList<Log> logs = new ArrayList<>();

    public User(String name) {
        this.name = name;
        color = Constants.colors.get((int) Math.floor(Math.random() * Constants.colors.size()));
        logs.add(new Log());
    }

    public User(String name, ArrayList<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
        color = Constants.colors.get((int) Math.floor(Math.random() * Constants.colors.size()));
    }

    public User(String name, ArrayList<Subject> subjects, int color) {
        this.name = name;
        this.subjects = subjects;
        this.color = color;
    }

    public User(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public void newLog() {
        logs.add(new Log());
    }
}
