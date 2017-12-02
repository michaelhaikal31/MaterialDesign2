package com.example.haikal.materialdesign2.SimpleToDo;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.haikal.materialdesign2.R;
import com.example.haikal.materialdesign2.SimpleToDo.newdb.TaskContract;
import com.example.haikal.materialdesign2.SimpleToDo.newdb.TaskDbHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleToDoActivity extends AppCompatActivity {
    private TaskDbHelper mHelper;
    private ListView mlistView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_to_do);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setTitle(SimpleToDoActivity.class.getSimpleName());
       /*Call Class TaskDbHelper */
        mHelper = new TaskDbHelper(this);
        mlistView = (ListView) findViewById(R.id.ListViewToDo);

        UpdateUI();
    }

    private void UpdateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry._ID,
                        TaskContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }
       /*Default Adapter */
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(this,
                    R.layout.item_todo_layout,
                    R.id.tvName,
                    taskList);
            mlistView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

    /*Custom Adaprer if you want add item other */
    class CustomAdapter extends ArrayAdapter<Itemodo> {
        private Context context;
        private List<Itemodo> itemodoList;
        private int mLayout;

        public CustomAdapter(Context context, int layout, List<Itemodo> itemodoList1) {
            super(context, layout, itemodoList1);
            this.context = context;
            this.mLayout = layout;
            this.itemodoList = itemodoList1;
        }

        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder {
            public TextView tvtodo;
            public TextView tvtime;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View rowView = convertView;
            if (rowView == null) {

                LayoutInflater layoutInflater = getLayoutInflater();
                rowView = layoutInflater.inflate(mLayout, null, true);
                viewHolder = new ViewHolder();
                viewHolder.tvtodo = (TextView) rowView.findViewById(R.id.tvName);
                viewHolder.tvtime = (TextView) rowView.findViewById(R.id.tvDate);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) rowView.getTag();
            }

            Itemodo item = itemodoList.get(position);
            viewHolder.tvtodo.setText(item.getTextTodo());
            viewHolder.tvtime.setText(item.getTimeTodo());
            return rowView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addToDo:
                AddToDataBase();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AddToDataBase() {
        final EditText nameEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new Todo")
                .setMessage("What do you want to do next?")
                .setView(nameEditText)
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = String.valueOf(nameEditText.getText());
                        long date = (new Date()).getTime();
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(TaskContract.TaskEntry.COL_TASK_TITLE, name);
                        db.insertWithOnConflict(TaskContract.TaskEntry.TABLE,
                                null, values, SQLiteDatabase.CONFLICT_REPLACE);
                        db.close();
                        UpdateUI();
                    }
                }).setNegativeButton("Cancel", null).create();
        dialog.show();
    }

    public void deleteTask(View view) {
        View view1 = (View) view.getParent();
        TextView taskTextView = (TextView)view1.findViewById(R.id.tvName);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(TaskContract.TaskEntry.TABLE,
                TaskContract.TaskEntry.COL_TASK_TITLE + " = ?",
                new String[]{task});
        db.close();
        UpdateUI();
    }
}
