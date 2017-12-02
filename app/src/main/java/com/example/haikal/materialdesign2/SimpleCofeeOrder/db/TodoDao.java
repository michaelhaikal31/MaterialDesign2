package com.example.haikal.materialdesign2.SimpleCofeeOrder.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.haikal.materialdesign2.SimpleCofeeOrder.db.Todo;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by haikal on 27/11/2017.
 */

public interface TodoDao {
    @Query("SELECT * FROM Todo")
    LiveData<List<Todo>> findAllTodos();

    @Insert(onConflict = REPLACE)
    void insertTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);
}
