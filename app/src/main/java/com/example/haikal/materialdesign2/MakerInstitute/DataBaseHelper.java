package com.example.haikal.materialdesign2.MakerInstitute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haikal on 13/12/2017.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String TAG_TABLE = "TB_todo";
    private static final String TAG_SERVER = "DB_todo";
    private static final String TAG_ID= "ID_todo";
    private static  final String TAG_NAME = "name_todo";
    public DataBaseHelper(Context context) {
        super(context,TAG_SERVER, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String  CREATE_DB_TABLE = "CREATE TABLE "+TAG_TABLE+"("+TAG_ID+" INTEGER PRIMARY KEY,"
                    +TAG_NAME+" TEXT);";
        sqLiteDatabase.execSQL(CREATE_DB_TABLE);
    }

    public void add_item(String n){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAG_NAME, n);
        sqLiteDatabase.insert(TAG_TABLE, null,values);
        sqLiteDatabase.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TAG_TABLE);
            onCreate(sqLiteDatabase);
    }

    public ArrayList<String> getAllFile (){
        ArrayList<String> list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM "+TAG_TABLE;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if(cursor.moveToNext()){
            do {
                list.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return  list;
    }
}
