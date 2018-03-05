package com.example.ikefluxa.stufflogger;

import java.util.ArrayList;

/**
 * Created by Ike&Fluxa on 2/19/2018.
 */

public class User {
    public String name;
    public ArrayList<Subject> subjects = new ArrayList<>();

    // Log info
    ArrayList<LogLine> logLines = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public User(String name, ArrayList<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
        if (this.name.toUpperCase().equals("CORDY") || this.name.toUpperCase().equals("CORDI") || this.name.toUpperCase().equals("CORDELIA")) {
            this.subjects.add(new Subject("Cut paper"));
            this.subjects.add(new Subject("Watch Daba play video games"));
            this.subjects.add(new Subject("Give Daba back rub"));
            this.subjects.add(new Subject("Scrape knee on driveway"));
            this.subjects.add(new Subject("Get bee sting on nose"));
        }
    }

    public void clearLog() {
        logLines = new ArrayList<>();
    }
}
