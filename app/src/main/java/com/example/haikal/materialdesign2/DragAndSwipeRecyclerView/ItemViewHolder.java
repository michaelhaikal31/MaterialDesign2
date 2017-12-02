package com.example.haikal.materialdesign2.DragAndSwipeRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.haikal.materialdesign2.R;

/**
 * Created by haikal on 29/11/2017.
 */

class ItemViewHolder extends RecyclerView.ViewHolder {
    public final TextView textView;
    public ItemViewHolder(View view) {
        super(view);
        textView = (TextView)view.findViewById(R.id.item_text);
    }
}
