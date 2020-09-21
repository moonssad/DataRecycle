package com.xiniu.skin.impl;

import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.TextView;

import com.xiniu.skin.ResBean;
import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkinWrapper;


public class SkinWrapper4TextColor implements ISkinWrapper<TextView> {
    private SkinItems skinItems;

    public void init(Context context, SkinItems skinItems2) {
        this.skinItems = skinItems2;
    }

    public void apply(TextView textView, boolean z) {
        SkinItems skinItems2 = this.skinItems;
        if (skinItems2 != null) {
            if (skinItems2.getTextColor() != null) {
                textView.setTextColor(getColor(textView.getContext(), this.skinItems.getTextColor(), z));
            }
            if (this.skinItems.getTextColorHint() != null) {
                textView.setHintTextColor(getColor(textView.getContext(), this.skinItems.getTextColorHint(), z));
            }
        }
    }

    private ColorStateList getColor(Context context, ResBean resBean, boolean z) {
        int defaultResId = resBean.getDefaultResId();
        if (z) {
            defaultResId = resBean.getNightResId();
        }
        return context.getResources().getColorStateList(defaultResId);
    }
}
