package com.example.haikal.materialdesign2.ListViewPager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.haikal.materialdesign2.R;

public class ListViewPagerActivity extends AppCompatActivity {
private  TabLayout tabLayout;
private  ViewPager viewPager;
    public static int int_items = 4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0 :return  new MainFragment();
                case 1 :return  new MainFragment();
                case 2 :return  new MainFragment();
                case 3 :return  new MainFragment();
            }
            return null;
        }
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0 :return  "Home";
                case 1 :return  "Home";
                case 2 :return  "Home";
                case 3 :return  "Home";
            }
            return null;
        }
    }


}
