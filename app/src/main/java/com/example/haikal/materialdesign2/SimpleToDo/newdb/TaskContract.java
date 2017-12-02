package com.example.haikal.materialdesign2.SimpleToDo.newdb;

import android.provider.BaseColumns;

/**
 * Created by haikal on 30/11/2017.
 */

public class TaskContract
{
    public static final String DB_NAME = "com.example.haikal.materialdesign2.SimpleToDo.newdb";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns{
        public static final String TABLE = "tasks";
        public static final String COL_TASK_TITLE = "title";
    }
}
