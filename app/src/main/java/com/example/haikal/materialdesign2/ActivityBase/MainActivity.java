package com.example.haikal.materialdesign2.ActivityBase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.haikal.materialdesign2.AndroidSpeech.SpeechActivity;
import com.example.haikal.materialdesign2.Dialog.DialogActivity;
import com.example.haikal.materialdesign2.DragAndSwipeRecyclerView.DragAndSwipeRecyclerViewActivity;
import com.example.haikal.materialdesign2.ListViewPager.ListViewPagerActivity;
import com.example.haikal.materialdesign2.Notifications.NotoficationsActivity;
import com.example.haikal.materialdesign2.R;
import com.example.haikal.materialdesign2.SimpleCofeeOrder.SimpleCofeeActivity;
import com.example.haikal.materialdesign2.SimpleToDo.SimpleToDoActivity;
import com.example.haikal.materialdesign2.SoundRecorder.SoundRecorderActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView list_item;
    private String[] Item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list_item = (ListView) findViewById(R.id.listview_item);
        Item = getResources().getStringArray(R.array.Item);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_2, android.R.id.text1, Item);
        list_item.setAdapter(arrayAdapter);
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(getBaseContext(), SpeechActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getBaseContext(), SoundRecorderActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getBaseContext(), NotoficationsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getBaseContext(), DialogActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getBaseContext(), DragAndSwipeRecyclerViewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getBaseContext(), SimpleCofeeActivity.class));
                        break;
                    case 6: startActivity(new Intent(getBaseContext(), SimpleToDoActivity.class));
                        break;
                    default:
                        break;

                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
