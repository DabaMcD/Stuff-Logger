package com.example.ikefluxa.stufflogger;

import java.util.ArrayList;

/**
 * Created by Ike&Fluxa on 2/19/2018.
 */

public class User {
    public String name;
    public ArrayList<Subject> subjects = new ArrayList<>();
    public int color;

    // Log info
    ArrayList<LogLine> logLines = new ArrayList<>();

    public User(String name) {
        this.name = name;
        color = Constants.colors.get((int) Math.floor(Math.random() * Constants.colors.size()));
        // Delete the below later
        prank();
    }

    public User(String name, ArrayList<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
        color = Constants.colors.get((int) Math.floor(Math.random() * Constants.colors.size()));
        prank();
    }

    public User(String name, ArrayList<Subject> subjects, int color) {
        this.name = name;
        this.subjects = subjects;
        this.color = color;
        prank();
    }

    public User(String name, int color) {
        this.name = name;
        this.color = color;
        prank();
    }

    private void prank() {
        if (this.name.toUpperCase().equals("CORDY") || this.name.toUpperCase().equals("CORDI") || this.name.toUpperCase().equals("CORDELIA") || this.name.toUpperCase().equals("COKEY") || this.name.toUpperCase().equals("COKESTER") || this.name.toUpperCase().equals("CORD") || this.name.toUpperCase().equals("CORDI N")) {
            this.subjects.add(new Subject("Cut paper"));
            this.subjects.add(new Subject("Watch Daba play video games"));
            this.subjects.add(new Subject("Give Daba back rub"));
            this.subjects.add(new Subject("Scrape knee on driveway"));
            this.subjects.add(new Subject("Get bee sting on nose"));
        }
        this.name = "¿¡KrOrKeE!?";
    } // Delete later

    public void clearLog() {
        logLines = new ArrayList<>();
    }
}
