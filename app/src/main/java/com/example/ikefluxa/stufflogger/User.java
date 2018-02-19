package com.example.ikefluxa.stufflogger;

import java.util.ArrayList;

/**
 * Created by Ike&Fluxa on 2/19/2018.
 */

public class User {
    String name;
    ArrayList<Subject> subjects = new ArrayList<>();

    public User () {

    }

    public User (String name) {
        this.name = name;
    }

    public User (String name, ArrayList<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
