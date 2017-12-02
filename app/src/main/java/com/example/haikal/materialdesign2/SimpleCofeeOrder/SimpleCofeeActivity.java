package com.example.haikal.materialdesign2.SimpleCofeeOrder;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.haikal.materialdesign2.R;
import com.example.haikal.materialdesign2.SimpleCofeeOrder.db.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleCofeeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoViewModel todoViewModel;
    private List<Todo> Mtodos = new ArrayList<Todo>();
    private TodoAdapter todoAdapter = new TodoAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_cofee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null ){
            setSupportActionBar(toolbar);
            this.getSupportActionBar().setDisplayShowHomeEnabled(true);
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(todoAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryText();
            }
        });

    }

    private void EntryText() {
        final EditText nameEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new Todo")
                .setMessage("What do you want to do next?")
                .setView(nameEditText)
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = String.valueOf(nameEditText.getText());

                        //todoViewModel.addTodo(name, date);
                    }
                }).setNegativeButton("Cancel", null).create();
        dialog.show();

    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

        @Override
        public TodoAdapter.TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_layout, parent, false);
            return new TodoViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(TodoAdapter.TodoViewHolder holder, int position) {
            Todo todo = Mtodos.get(position);
            holder.getName().setText(todo.name);
            holder.getDate().setText((new Date(todo.date)).toString());
        }

        @Override
        public int getItemCount() {
            return Mtodos.size();
        }

        public class TodoViewHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView date;

            public TodoViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tvName);
                date = itemView.findViewById(R.id.tvDate);
                Button btnDelete = itemView.findViewById(R.id.btnDelete);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = getAdapterPosition();
                        Todo todo = Mtodos.get(pos);
                        todoViewModel.remoceTodo(todo.id);
                    }
                });
            }

            TextView getName() {
                return name;
            }

            TextView getDate() {
                return date;
            }
        }
    }
}
