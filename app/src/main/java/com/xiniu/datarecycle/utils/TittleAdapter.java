package com.xiniu.datarecycle.utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * 创建者：wyz
 * 创建时间：2020-07-06
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TittleAdapter extends BindingRecyclerViewAdapter<TittleBean> {
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

    }

}

