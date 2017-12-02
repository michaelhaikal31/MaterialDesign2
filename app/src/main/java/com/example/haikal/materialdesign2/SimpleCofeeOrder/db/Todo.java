package com.example.haikal.materialdesign2.SimpleCofeeOrder.db;

import android.arch.persistence.room.PrimaryKey;

/**
 * Created by haikal on 27/11/2017.
 */

public class Todo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public long date;

    public boolean equals(Todo todo) {
        return todo.id == id &&
                todo.name == name &&
                todo.date == date;
    }
}
