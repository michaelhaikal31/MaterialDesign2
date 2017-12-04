package com.example.haikal.materialdesign2.SoundRecorder.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.haikal.materialdesign2.ListViewPager.MainFragment;
import com.example.haikal.materialdesign2.R;
import com.example.haikal.materialdesign2.SoundRecorder.Fragment_Record;
import com.example.haikal.materialdesign2.SoundRecorder.SettingAcity.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class SoundRecorderActivity extends AppCompatActivity {
private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_recorder);

        viewPager = (ViewPager)findViewById(R.id.viewpager_recoder);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_recoder);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Record(), "RECORD");
        adapter.addFragment(new MainFragment(), "SAVE RECORDINGS");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recoder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting_recoder : startActivity(new Intent(getBaseContext(), SettingsPrefActivity.class));
            break;
            case R.id.setting_activity : startActivity(new Intent(getBaseContext(),SettingsActivity.class));
            break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

}

