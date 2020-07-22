package com.xiniu.datarecycle.mvvm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-02
 * 功能描述：手势滑动移动位置的帮助类
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class CustomItemTouchCallback extends ItemTouchHelper.Callback {

    private onSwipedListener swipeListener;

    public CustomItemTouchCallback(onSwipedListener swipeListener) {
        this.swipeListener = swipeListener;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags;
        int swipFlags;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipFlags = 0;
            } else {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipFlags = ItemTouchHelper.LEFT;
            }
            return makeMovementFlags(dragFlags, swipFlags);

    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
        if (swipeListener != null) {
            swipeListener.itemMoved(fromPosition, toPosition);
        }

        Log.e("onMove: ", "onMove");
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.e("onSwiped: ", viewHolder.getAdapterPosition() + "|" + direction);
        if (swipeListener != null) {
            swipeListener.ondeleted(viewHolder.getAdapterPosition());
        }
    }

    //设置那些对象不能够交换位置。
    @Override
    public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder current, @NonNull RecyclerView.ViewHolder target) {
//        return !(current.getAdapterPosition() < 3 || target.getAdapterPosition() < 3);
        return true;
    }


    public interface onSwipedListener {
        void ondeleted(int position);

        void itemMoved(int fromPosition, int toPosition);
    }
}
