package com.example.haikal.materialdesign2.DragAndSwipeRecyclerView;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.haikal.materialdesign2.R;

public class DragAndSwipeRecyclerViewActivity extends AppCompatActivity {
private RecyclerViewAdapter recyclerViewAdapter;
private  RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_swipe_recycler_view);

        recyclerView= (RecyclerView)findViewById(R.id.recyclerView_DragAndSwipe);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        ItemTouchHelper.Callback callback = new SimpleTouchAdapterHelperCallback(recyclerViewAdapter);
        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorIndigo, R.color.colorPink, R.color.colorLime);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                    }
                },300);
            }
        });

    }
}
