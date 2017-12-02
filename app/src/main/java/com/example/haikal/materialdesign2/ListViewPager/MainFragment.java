package com.example.haikal.materialdesign2.ListViewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haikal.materialdesign2.R;

/**
 * Created by haikal on 29/11/2017.
 */

public class MainFragment extends Fragment {
public MainFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item, container,false);
    }
}
