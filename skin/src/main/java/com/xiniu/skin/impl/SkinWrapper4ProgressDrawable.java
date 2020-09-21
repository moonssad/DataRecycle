package com.xiniu.skin.impl;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkinWrapper;


public class SkinWrapper4ProgressDrawable implements ISkinWrapper<ProgressBar> {
    private Context mContext;
    private SkinItems skinItems;

    public void init(Context context, SkinItems skinItems2) {
        this.mContext = context;
        this.skinItems = skinItems2;
    }

    public void apply(ProgressBar progressBar, boolean z) {
        int i;
        SkinItems skinItems2 = this.skinItems;
        if (skinItems2 != null && skinItems2.getProgressDrawable() != null) {
            if (z) {
                i = this.skinItems.getProgressDrawable().getNightResId();
            } else {
                i = this.skinItems.getProgressDrawable().getDefaultResId();
            }
            if (i > 0) {
                Drawable drawable = this.mContext.getResources().getDrawable(i);
                Rect bounds = progressBar.getProgressDrawable().getBounds();
                progressBar.setProgressDrawable(drawable);
                if (progressBar instanceof SeekBar) {
                    progressBar.getProgressDrawable().setBounds(bounds);
                }
            }
        }
    }
}
