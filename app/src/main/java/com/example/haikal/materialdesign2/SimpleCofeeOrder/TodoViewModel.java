package com.example.haikal.materialdesign2.SimpleCofeeOrder;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.haikal.materialdesign2.SimpleCofeeOrder.db.AppDataBase;
import com.example.haikal.materialdesign2.SimpleCofeeOrder.db.Todo;

import java.util.List;

/**
 * Created by haikal on 27/11/2017.
 */

public class TodoViewModel extends AndroidViewModel{
    public final LiveData<List<Todo>> todos;
    private AppDataBase mDb;
    public TodoViewModel(@NonNull Application application, LiveData<List<Todo>> todos) {
        super(application);
        this.todos = todos;
        mDb = AppDataBase.getDatebase(this.getApplication());
    }
    public  void addTodo(String name, long date){
        Todo todo = new Todo();
        todo.name = name;
        todo.date = date;
        mDb.todoModel().insertTodo(todo);
    }
    public void remoceTodo(int id){
        Todo todo = new Todo();
        todo.id = id;
        mDb.todoModel().deleteTodo(todo);
    }
}
