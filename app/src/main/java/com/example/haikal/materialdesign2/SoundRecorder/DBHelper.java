package com.example.haikal.materialdesign2.SoundRecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.haikal.materialdesign2.SoundRecorder.listeners.OnDatabaseChangedListener;

import java.util.Comparator;

/**
 * Created by haikal on 04/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String LOG_TAG = "DBHelper";
    private static OnDatabaseChangedListener mOnDatabaseChangedListener;
    public static final String DATABASE_NAME = "save_recordings.db";
    private static final int DATABASE_VERSION = 1;

    public static  abstract class DBHelperItem implements BaseColumns{
        public static final String TABLE_NAME="saved_recordings.db";
        public static final String COLUMN_NAME_RECORDING_NAME = "recoding_name";
        public static final String COLUMN_NAME_RECORDING_FILE_PATH = "file_path";
        public static final String COLUMN_NAME_RECORDING_LENGTH = "length";
        public static final String COLUMN_NAME_TIME_ADDED = "time_added";
    }
    private static final String TEXT_TYPE = "TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE"+DBHelperItem.TABLE_NAME+"("+
                    DBHelperItem._ID+" INTEGER PRIMARY KEY"+COMMA_SEP+
                    DBHelperItem.COLUMN_NAME_RECORDING_NAME+TEXT_TYPE+COMMA_SEP+
                    DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH+TEXT_TYPE+COMMA_SEP+
                    DBHelperItem.COLUMN_NAME_RECORDING_LENGTH+"INTEGER"+COMMA_SEP+
                    DBHelperItem.COLUMN_NAME_TIME_ADDED+"INTEGER"+");";
    @SuppressWarnings("unused")
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" +DBHelperItem.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public static void setmOnDatabaseChangedListener(OnDatabaseChangedListener listener){
        mOnDatabaseChangedListener = listener;
    }
    public RecordingItem getItemAt(int position){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DBHelperItem._ID,
                DBHelperItem.COLUMN_NAME_RECORDING_NAME,
                DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH,
                DBHelperItem.COLUMN_NAME_RECORDING_LENGTH,
                DBHelperItem.COLUMN_NAME_TIME_ADDED
        };
        Cursor c = db.query(DBHelperItem.TABLE_NAME, projection, null , null, null,null,null);
        if(c.moveToPosition(position)){
            RecordingItem item = new RecordingItem();
            item.setmId(c.getInt(c.getColumnIndex(DBHelperItem._ID)));
            item.setmName(c.getString(c.getColumnIndex(DBHelperItem.COLUMN_NAME_RECORDING_NAME)));
            item.setmFilePath(c.getString(c.getColumnIndex(DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH)));
            item.setmLength(c.getInt(c.getColumnIndex(DBHelperItem.COLUMN_NAME_RECORDING_LENGTH)));
            item.setmTime(c.getLong(c.getColumnIndex(DBHelperItem.COLUMN_NAME_TIME_ADDED)));
            c.close();
            return  item;
        }
        return null;
    }

    public void removeItemWithId(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] whereArgs = {String.valueOf(id)};
        db.delete(DBHelperItem.TABLE_NAME, "_ID=?", whereArgs);
    }

    public int getCount(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {DBHelperItem._ID};
        Cursor c = db.query(DBHelperItem.TABLE_NAME, projection, null, null, null, null, null);
        int count = c.getCount();
        c.close();
        return count;
    }

    public Context getContext(){
        return mContext;
    }

    public class RecordingComparator implements Comparator<RecordingItem>{
        @Override
        public int compare(RecordingItem recordingItem, RecordingItem t1) {
            Long o1 = recordingItem.getmTime();
            Long o2 = t1.getmTime();
            return o2.compareTo(o1);
        }
    }

    public long addRecording(String recordingName, String filePath, long length) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_NAME, recordingName);
        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH, filePath);
        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_LENGTH, length);
        cv.put(DBHelperItem.COLUMN_NAME_TIME_ADDED, System.currentTimeMillis());
        long rowId = db.insert(DBHelperItem.TABLE_NAME, null, cv);

        if (mOnDatabaseChangedListener != null) {
            mOnDatabaseChangedListener.onNewDatabaseEntryAdded();
        }

        return rowId;
    }

    public void renameItem(RecordingItem item, String recordingName, String filePath) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_NAME, recordingName);
        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH, filePath);
        db.update(DBHelperItem.TABLE_NAME, cv,
                DBHelperItem._ID + "=" + item.getmId(), null);

        if (mOnDatabaseChangedListener != null) {
            mOnDatabaseChangedListener.onDatabaseEntryRenamed();
        }
    }

    public long restoreRecording(RecordingItem item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_NAME, item.getmName());
        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH, item.getmFilePath());
        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_LENGTH, item.getmLength());
        cv.put(DBHelperItem.COLUMN_NAME_TIME_ADDED, item.getmTime());
        cv.put(DBHelperItem._ID, item.getmId());
        long rowId = db.insert(DBHelperItem.TABLE_NAME, null, cv);
        if (mOnDatabaseChangedListener != null) {
            //mOnDatabaseChangedListener.onNewDatabaseEntryAdded();
        }
        return rowId;
    }
}
