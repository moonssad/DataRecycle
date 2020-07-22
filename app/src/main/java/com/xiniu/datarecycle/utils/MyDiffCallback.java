package com.xiniu.datarecycle.utils;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

/**
 * 创建者：wyz
 * 创建时间：2020-07-22
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class MyDiffCallback extends DiffUtil.Callback {
    private List<String> mOldList;
    private List<String> mNewList;

    public MyDiffCallback(List<String> mOldList, List<String> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        String oldItem = mOldList.get(oldItemPosition);
        String newItem = mNewList.get(newItemPosition);
        Bundle diffBundle = new Bundle();
        if (!TextUtils.equals(oldItem, newItem)) {
            diffBundle.putString("content", newItem);
        }
        return diffBundle;
    }

}
