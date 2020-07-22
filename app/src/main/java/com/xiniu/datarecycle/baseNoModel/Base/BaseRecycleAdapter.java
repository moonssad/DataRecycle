package com.xiniu.datarecycle.baseNoModel.Base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-16
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public abstract class BaseRecycleAdapter<E, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public List<E> lists;
    public VH holder;

    public BaseRecycleAdapter(List<E> list) {
        this.lists = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        holder = viewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        BindView(holder, position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public E getItem(int position) {
        return lists == null ? null : lists.get(position);
    }


    public abstract int getLayoutId();

    public abstract VH viewHolder(View view);

    public abstract void BindView(VH holder, int position);
}

