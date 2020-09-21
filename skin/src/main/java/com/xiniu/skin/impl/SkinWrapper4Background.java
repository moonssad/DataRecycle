package com.xiniu.skin.impl;

import android.content.Context;
import android.view.View;

import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkinWrapper;


public class SkinWrapper4Background implements ISkinWrapper<View> {
    private SkinItems skinItems;

    public void init(Context context, SkinItems skinItems2) {
        this.skinItems = skinItems2;
    }

    public void apply(View view, boolean z) {
        int i;
        SkinItems skinItems2 = this.skinItems;
        if (skinItems2 != null && skinItems2.getBackground() != null) {
            if (z) {
                i = this.skinItems.getBackground().getNightResId();
            } else {
                i = this.skinItems.getBackground().getDefaultResId();
            }
            view.setBackgroundResource(i);
        }
    }
}
