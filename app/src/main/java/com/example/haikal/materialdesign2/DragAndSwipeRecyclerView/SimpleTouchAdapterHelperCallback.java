package com.example.haikal.materialdesign2.DragAndSwipeRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TableRow;

/**
 * Created by haikal on 29/11/2017.
 */

public class SimpleTouchAdapterHelperCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter mitemTouchHelperAdapter;

    public SimpleTouchAdapterHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        mitemTouchHelperAdapter = itemTouchHelperAdapter;
    }


    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int draFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(draFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mitemTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mitemTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
