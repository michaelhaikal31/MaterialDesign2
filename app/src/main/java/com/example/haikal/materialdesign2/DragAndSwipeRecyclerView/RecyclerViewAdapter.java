package com.example.haikal.materialdesign2.DragAndSwipeRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haikal.materialdesign2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haikal on 29/11/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> implements ItemTouchHelperAdapter {
    private static final String[] STRINGS = new String[]{"One","Two","Tree","Four","Five","Six","Seven","Eight","Nine","Teen"};
    private static List<String> stringList = new ArrayList<>();
    public RecyclerViewAdapter(){
        stringList.addAll(Arrays.asList(STRINGS));
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return true;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
