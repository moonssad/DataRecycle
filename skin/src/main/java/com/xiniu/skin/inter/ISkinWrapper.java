package com.xiniu.skin.inter;

import android.content.Context;
import android.view.View;

import com.xiniu.skin.SkinItems;

public interface ISkinWrapper<T extends View> {
    void apply(T t, boolean z);

    void init(Context context, SkinItems skinItems);
}
