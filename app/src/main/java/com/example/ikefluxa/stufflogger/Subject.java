package com.example.ikefluxa.stufflogger;

import java.io.Serializable;

/**
 * Created by Ike&Fluxa on 2/19/2018.
 */

public class Subject implements Serializable {
    public String name;

    public Subject(String name) {
        this.name = name;
    }
}
