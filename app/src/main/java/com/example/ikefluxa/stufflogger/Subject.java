package com.example.ikefluxa.stufflogger;

import java.io.Serializable;

class Subject implements Serializable {
    String name;

    Subject(String name) {
        this.name = name;
    }
}
