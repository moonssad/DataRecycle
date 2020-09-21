package com.xiniu.skin.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.xiniu.skin.ResBean;
import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkin;


public class ImageViewSkinAdapter extends ViewSkinAdapter<ImageView> {
    protected SkinWrapper4Src mSrcWrapper;

    protected ImageViewSkinAdapter(Context context, SkinItems skinItems) {
        super(context, skinItems);
    }

    public ImageViewSkinAdapter(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
    }

    public static ISkin.ISkinAdapter build(Context context, SkinItems skinItems) {
        return new ImageViewSkinAdapter(context, skinItems);
    }

    public void initSkinImpl(View view) {
        if (this.mSkinProperter != null) {
            SkinWrapper4Src skinWrapper4Src = new SkinWrapper4Src();
            this.mSrcWrapper = skinWrapper4Src;
            skinWrapper4Src.init(this.mContext, this.mSkinProperter);
        }
    }

    public void applyImpl(boolean z) {
        SkinWrapper4Src skinWrapper4Src = this.mSrcWrapper;
        if (skinWrapper4Src != null) {
            skinWrapper4Src.apply((ImageView) this.view, z);
        }
    }

    public void setImageResource(int i, int i2) {
        ResBean resBean = new ResBean();
        resBean.setDefaultResId(i);
        resBean.setNightResId(i2);
        this.mSkinProperter.setSrc(resBean);
        if (this.mSrcWrapper == null) {
            this.mSrcWrapper = new SkinWrapper4Src();
        }
        this.mSrcWrapper.init(this.mContext, this.mSkinProperter);
        onUpdateRes();
    }
}
