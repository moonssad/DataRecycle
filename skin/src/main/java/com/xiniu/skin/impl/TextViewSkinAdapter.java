package com.xiniu.skin.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.xiniu.skin.ResBean;
import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkin;


public class TextViewSkinAdapter extends ViewSkinAdapter<TextView> {
    protected SkinWrapper4DrawableCompound mDrawableCompound;
    protected SkinWrapper4TextColor mTextColorWrapper;

    protected TextViewSkinAdapter(Context context, SkinItems skinItems) {
        super(context, skinItems);
        this.mContext = context;
        this.mSkinProperter = skinItems;
    }

    public TextViewSkinAdapter(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
    }

    public static ISkin.ISkinAdapter build(Context context, SkinItems skinItems) {
        return new TextViewSkinAdapter(context, skinItems);
    }

    public void initSkinImpl(View view) {
        if (this.mSkinProperter != null) {
            SkinWrapper4TextColor skinWrapper4TextColor = new SkinWrapper4TextColor();
            this.mTextColorWrapper = skinWrapper4TextColor;
            skinWrapper4TextColor.init(this.mContext, this.mSkinProperter);
            SkinWrapper4DrawableCompound skinWrapper4DrawableCompound = new SkinWrapper4DrawableCompound();
            this.mDrawableCompound = skinWrapper4DrawableCompound;
            skinWrapper4DrawableCompound.init(this.mContext, this.mSkinProperter);
        }
    }

    public void applyImpl(boolean z) {
        super.applyImpl(z);
        SkinWrapper4TextColor skinWrapper4TextColor = this.mTextColorWrapper;
        if (skinWrapper4TextColor != null) {
            skinWrapper4TextColor.apply((TextView) this.view, z);
        }
        SkinWrapper4DrawableCompound skinWrapper4DrawableCompound = this.mDrawableCompound;
        if (skinWrapper4DrawableCompound != null) {
            skinWrapper4DrawableCompound.apply((TextView) this.view, z);
        }
    }

    public void setTextColor(int i, int i2) {
        ResBean resBean = new ResBean();
        resBean.setDefaultResId(i);
        resBean.setNightResId(i2);
        this.mSkinProperter.setTextColor(resBean);
        if (this.mTextColorWrapper == null) {
            this.mTextColorWrapper = new SkinWrapper4TextColor();
        }
        this.mTextColorWrapper.init(this.mContext, this.mSkinProperter);
        onUpdateRes();
    }

    public void setHintTextColor(int i, int i2) {
        ResBean resBean = new ResBean();
        resBean.setDefaultResId(i);
        resBean.setNightResId(i2);
        this.mSkinProperter.setTextColorHint(resBean);
        if (this.mTextColorWrapper == null) {
            this.mTextColorWrapper = new SkinWrapper4TextColor();
        }
        this.mTextColorWrapper.init(this.mContext, this.mSkinProperter);
        onUpdateRes();
    }
}
