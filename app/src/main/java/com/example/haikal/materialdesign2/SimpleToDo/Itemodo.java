package com.example.haikal.materialdesign2.SimpleToDo;

import android.widget.TextView;

/**
 * Created by haikal on 30/11/2017.
 */

class Itemodo {
    private String TextTodo;
    private String TimeTodo;
    Itemodo(String textTodo, String timeTodo){
        this.TextTodo = textTodo;
        this.TimeTodo = timeTodo;
    }

    public String getTextTodo() {
        return TextTodo;
    }

    public void setTextTodo(String textTodo) {
        TextTodo = textTodo;
    }

    public String getTimeTodo() {
        return TimeTodo;
    }

    public void setTimeTodo(String timeTodo) {
        TimeTodo = timeTodo;
    }
}
