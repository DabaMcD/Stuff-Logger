package com.stuff.log.ger;

import java.io.Serializable;
import java.util.ArrayList;

class ToDoList implements Serializable {
    private ArrayList<ToDoItem> toDoItems;

    ToDoList() {
        this.toDoItems = new ArrayList<>();
    }
    ToDoItem getItem(int index) {
        return toDoItems.get(index);
    }
    void addToDoItem(ToDoItem toDoItem) {
        toDoItems.add(toDoItem);
    }
}
