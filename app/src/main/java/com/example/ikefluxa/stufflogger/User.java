package com.example.ikefluxa.stufflogger;

import java.util.ArrayList;

public class User {
    public String name;
    public int color;
    ArrayList<LogLine> logLines = new ArrayList<>();

    User(String name) {
        this.name = name;
        color = Constants.colors.get((int) Math.floor(Math.random() * Constants.colors.size()));
    }
}
