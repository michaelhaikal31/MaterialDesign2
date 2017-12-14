package com.example.haikal.materialdesign2.MakerInstitute;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.haikal.materialdesign2.Dialog.DialogActivity;
import com.example.haikal.materialdesign2.R;

import java.util.ArrayList;

public class MakerInstituteActivity extends AppCompatActivity {
    private ListView listView;
    private DialogActivity dialogActivity;
    private ArrayList<String> arrayList;
    private  ArrayAdapter<String> arrayAdapter;
    private DataBaseHelper dataBaseHelper;
    private  TextView  textView_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maker_institute);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);

        listView = (ListView)findViewById(R.id.listView_maker);
        textView_name = (TextView)findViewById(R.id.text_makers);

       udapte();
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maker, menu);
        return super.onCreateOptionsMenu(menu);
    }
public void udapte(){
    arrayList = dataBaseHelper.getAllFile();

    arrayAdapter= new ArrayAdapter<String>(this,R.layout.item_list_makers,R.id.text_makers,arrayList);
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        if(item.getItemId() == R.id.menu_tambah){
            final EditText editText = new EditText(this);
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setView(editText)
                    .setMessage("set Text")
                    .setTitle("todo list")
                    .setPositiveButton("add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String item = editText.getText().toString();
                            dataBaseHelper.add_item(item);
                            arrayAdapter.notifyDataSetChanged();
                            udapte();
                        }
                    }).setNegativeButton("Cancel",null).create();
            alertDialog.show();


        }
        return super.onOptionsItemSelected(item);
    }
}
