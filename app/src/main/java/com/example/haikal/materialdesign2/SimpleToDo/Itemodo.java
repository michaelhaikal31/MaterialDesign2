package com.example.haikal.materialdesign2.SimpleToDo;

import android.widget.TextView;

/**
 * Created by haikal on 30/11/2017.
 */

public class Itemodo {
    private String TextTodo;
    private Long TimeTodo;
    Itemodo(String textTodo, Long timeTodo){
        this.TextTodo = textTodo;
        this.TimeTodo = timeTodo;
    }

    public String getTextTodo() {
        return TextTodo;
    }

    public void setTextTodo(String textTodo) {
        TextTodo = textTodo;
    }

    public Long getTimeTodo() {
        return TimeTodo;
    }

    public void setTimeTodo(Long timeTodo) {
        TimeTodo = timeTodo;
    }
}
