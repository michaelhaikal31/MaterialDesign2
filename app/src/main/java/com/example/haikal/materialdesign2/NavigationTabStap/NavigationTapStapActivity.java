package com.example.haikal.materialdesign2.NavigationTabStap;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.haikal.materialdesign2.R;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

public class NavigationTapStapActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private NavigationTabStrip TopNavigationStrip;
    private NavigationTabStrip CenterNavigationStrip;
    private NavigationTabStrip BotomNavigationStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_tap_stap);
        initUI();
        setUI();
    }

    private void setUI() {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager)container).removeView((View)object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                final View view = new View(getBaseContext());
                container.addView(view);
                return view;
            }
        });
        TopNavigationStrip.setTabIndex(1 ,true);
        CenterNavigationStrip.setViewPager(mViewPager, 1);
        BotomNavigationStrip.setTabIndex(1, true);
    }

    private void initUI() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        TopNavigationStrip = (NavigationTabStrip) findViewById(R.id.nts_top);
        CenterNavigationStrip = (NavigationTabStrip) findViewById(R.id.nts_center);
        BotomNavigationStrip = (NavigationTabStrip) findViewById(R.id.nts_bottom);
    }
}
