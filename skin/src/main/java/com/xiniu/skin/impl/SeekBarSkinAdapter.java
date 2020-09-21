package com.xiniu.skin.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.xiniu.skin.ResBean;
import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkin;


public class SeekBarSkinAdapter extends ViewSkinAdapter<SeekBar> {
    protected SkinWrapper4ProgressDrawable mProgressDrawableWrapper;
    protected SkinWrapper4Thumb mThumbWrapper;

    protected SeekBarSkinAdapter(Context context, SkinItems skinItems) {
        super(context, skinItems);
    }

    public SeekBarSkinAdapter(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
    }

    public static ISkin.ISkinAdapter build(Context context, SkinItems skinItems) {
        return new SeekBarSkinAdapter(context, skinItems);
    }

    public void initSkinImpl(View view) {
        if (this.mSkinProperter != null) {
            SkinWrapper4ProgressDrawable skinWrapper4ProgressDrawable = new SkinWrapper4ProgressDrawable();
            this.mProgressDrawableWrapper = skinWrapper4ProgressDrawable;
            skinWrapper4ProgressDrawable.init(this.mContext, this.mSkinProperter);
            SkinWrapper4Thumb skinWrapper4Thumb = new SkinWrapper4Thumb();
            this.mThumbWrapper = skinWrapper4Thumb;
            skinWrapper4Thumb.init(this.mContext, this.mSkinProperter);
        }
    }

    public void applyImpl(boolean z) {
        super.applyImpl(z);
        SkinWrapper4ProgressDrawable skinWrapper4ProgressDrawable = this.mProgressDrawableWrapper;
        if (skinWrapper4ProgressDrawable != null) {
            skinWrapper4ProgressDrawable.apply((ProgressBar) this.view, z);
        }
        SkinWrapper4Thumb skinWrapper4Thumb = this.mThumbWrapper;
        if (skinWrapper4Thumb != null) {
            skinWrapper4Thumb.apply((SeekBar) this.view, z);
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

    public void setThumb(int i, int i2) {
        ResBean resBean = new ResBean();
        resBean.setDefaultResId(i);
        resBean.setNightResId(i2);
        this.mSkinProperter.setThumb(resBean);
        if (this.mThumbWrapper == null) {
            this.mThumbWrapper = new SkinWrapper4Thumb();
        }
        this.mThumbWrapper.init(this.mContext, this.mSkinProperter);
        onUpdateRes();
    }
}
