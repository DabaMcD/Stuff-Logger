package com.example.ikefluxa.stufflogger;

import java.io.Serializable;

public class Subject implements Serializable {
    public String name;

    public Subject(String name) {
        this.name = name;
    }
}
