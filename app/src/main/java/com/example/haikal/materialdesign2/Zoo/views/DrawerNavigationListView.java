package com.example.haikal.materialdesign2.Zoo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.haikal.materialdesign2.Zoo.adapter.DrawerNavigationListAdapter;

import java.util.jar.Attributes;

/**
 * Created by haikal on 06/12/2017.
 */

public class DrawerNavigationListView extends ListView implements AdapterView.OnItemClickListener {
    public DrawerNavigationListView(Context context) {
        super(context);
    }
    public DrawerNavigationListView(Context context, AttributeSet attributes, int defStyleAttr){
        super(context, attributes, defStyleAttr);

        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter(context, 0);
        adapter.add("Exhibits");
        adapter.add("Gallerry");
        adapter.add("Maps");
        setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getContext(),"Selection Clicked : "+adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT).show();
    }
}
