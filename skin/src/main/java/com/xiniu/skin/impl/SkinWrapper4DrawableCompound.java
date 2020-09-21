package com.xiniu.skin.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.xiniu.skin.ResBean;
import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkinWrapper;


public class SkinWrapper4DrawableCompound implements ISkinWrapper<TextView> {
    private SkinItems skinItems;

    public void init(Context context, SkinItems skinItems2) {
        this.skinItems = skinItems2;
    }

    public void apply(TextView textView, boolean z) {
        SkinItems skinItems2 = this.skinItems;
        if (skinItems2 != null) {
            if (skinItems2.getDrawableLeft() != null || this.skinItems.getDrawableTop() != null || this.skinItems.getDrawableRight() != null || this.skinItems.getDrawableBottom() != null) {
                textView.setCompoundDrawables(getDrawable(textView.getContext(), this.skinItems.getDrawableLeft(), z), getDrawable(textView.getContext(), this.skinItems.getDrawableTop(), z), getDrawable(textView.getContext(), this.skinItems.getDrawableRight(), z), getDrawable(textView.getContext(), this.skinItems.getDrawableBottom(), z));
            }
        }
    }

    private Drawable getDrawable(Context context, ResBean resBean, boolean z) {
        if (resBean == null || resBean.getDefaultResId() <= 0) {
            return null;
        }
        int defaultResId = resBean.getDefaultResId();
        if (z) {
            defaultResId = resBean.getNightResId();
        }
        if (defaultResId <= 0) {
            return null;
        }
        Drawable drawable = context.getResources().getDrawable(defaultResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }
}
