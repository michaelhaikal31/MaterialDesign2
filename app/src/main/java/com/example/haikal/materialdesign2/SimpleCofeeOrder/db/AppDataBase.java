package com.example.haikal.materialdesign2.SimpleCofeeOrder.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by haikal on 27/11/2017.
 */

public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase INSTANCE;
    public abstract TodoDao todoModel();

    public static AppDataBase getDatebase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class, "com.example.haikal.materialdesign2.SimpleCofeeOrder.db")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance(){
        INSTANCE = null;
    }
}
