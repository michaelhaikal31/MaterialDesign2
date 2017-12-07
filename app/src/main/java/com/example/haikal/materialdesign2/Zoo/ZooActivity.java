package com.example.haikal.materialdesign2.Zoo;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.haikal.materialdesign2.R;
import com.example.haikal.materialdesign2.Zoo.adapter.DrawerNavigationListAdapter;
import com.example.haikal.materialdesign2.Zoo.views.DrawerNavigationListView;

public class ZooActivity extends AppCompatActivity {
    private DrawerNavigationListView drawerNavigationListView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo);
        //this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        ListView listView = (ListView)findViewById(R.id.drawer);
        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter(this, 0);
        adapter.add("Exhibits");
        adapter.add("Gallerry");
        adapter.add("Maps");
        listView.setAdapter(adapter);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open_Drawer, R.string.close_Drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(getSupportActionBar()!=null)
                    getSupportActionBar().setTitle(R.string.open_Drawer);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.close_Drawer);
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true); //show icon menu like humburger
        //mActionBarDrawerToggle.setDrawerSlideAnimationEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        /*if(item.getItemId() == android.R.id.home)
            onBackPressed();*/
        return super.onOptionsItemSelected(item);
    }
}
