package com.stuff.log.ger;

import java.io.Serializable;

class ToDoItem implements Serializable {
    private String name;
    private MyTime timeAdded;

    ToDoItem(String name, MyTime timeAdded) {
        this.name = name;
        this.timeAdded = timeAdded;
    }
    String getName() {
        return name;
    }
    MyTime getTimeAdded() {
        return timeAdded;
    }
}
