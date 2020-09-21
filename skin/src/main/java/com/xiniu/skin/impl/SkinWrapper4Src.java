package com.xiniu.skin.impl;

import android.content.Context;
import android.widget.ImageView;

import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkinWrapper;


public class SkinWrapper4Src implements ISkinWrapper<ImageView> {
    private SkinItems skinItems;

    public void init(Context context, SkinItems skinItems2) {
        this.skinItems = skinItems2;
    }

    public void apply(ImageView imageView, boolean z) {
        int i;
        SkinItems skinItems2 = this.skinItems;
        if (skinItems2 != null && skinItems2.getSrc() != null) {
            if (z) {
                i = this.skinItems.getSrc().getNightResId();
            } else {
                i = this.skinItems.getSrc().getDefaultResId();
            }
            imageView.setImageResource(i);
        }
    }
}
