package com.xiniu.skin.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.xiniu.skin.ResBean;
import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkin;


public class ProgressBarSkinAdapter extends ViewSkinAdapter<ProgressBar> {
    protected SkinWrapper4ProgressDrawable mProgressDrawableWrapper;

    protected ProgressBarSkinAdapter(Context context, SkinItems skinItems) {
        super(context, skinItems);
    }

    public ProgressBarSkinAdapter(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
    }

    public static ISkin.ISkinAdapter build(Context context, SkinItems skinItems) {
        return new ProgressBarSkinAdapter(context, skinItems);
    }

    public void initSkinImpl(View view) {
        if (this.mSkinProperter != null) {
            SkinWrapper4ProgressDrawable skinWrapper4ProgressDrawable = new SkinWrapper4ProgressDrawable();
            this.mProgressDrawableWrapper = skinWrapper4ProgressDrawable;
            skinWrapper4ProgressDrawable.init(this.mContext, this.mSkinProperter);
        }
    }

    public void applyImpl(boolean z) {
        super.applyImpl(z);
        SkinWrapper4ProgressDrawable skinWrapper4ProgressDrawable = this.mProgressDrawableWrapper;
        if (skinWrapper4ProgressDrawable != null) {
            skinWrapper4ProgressDrawable.apply((ProgressBar) this.view, z);
        }
    }

    public void setProgressDrawable(int i, int i2) {
        ResBean resBean = new ResBean();
        resBean.setDefaultResId(i);
        resBean.setNightResId(i2);
        this.mSkinProperter.setProgressDrawable(resBean);
        if (this.mProgressDrawableWrapper == null) {
            this.mProgressDrawableWrapper = new SkinWrapper4ProgressDrawable();
        }
        this.mProgressDrawableWrapper.init(this.mContext, this.mSkinProperter);
        onUpdateRes();
    }
}
