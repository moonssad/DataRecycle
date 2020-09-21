package com.xiniu.skin.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.xiniu.skin.NigthModeGlobal;
import com.xiniu.skin.ResBean;
import com.xiniu.skin.SkinItems;
import com.xiniu.skin.SkinManager;
import com.xiniu.skin.SkinUtil;
import com.xiniu.skin.inter.ISkin;
import com.xiniu.skin.inter.ViewApplyImplListener;


public class ViewSkinAdapter<T extends View> implements ISkin.ISkinAdapter {
    private boolean isInit = false;
    private boolean isNightMode = false;
    private boolean isUpadteRes;
    private SkinWrapper4Background mBackground;
    protected Context mContext;
    protected SkinItems mSkinProperter;
    protected T view;
    private ViewApplyImplListener viewApplyImplListener;

    /* access modifiers changed from: protected */
    public void initSkinImpl(View view2) {
    }

    protected ViewSkinAdapter(Context context, SkinItems skinItems) {
        this.mContext = context;
        this.mSkinProperter = skinItems;
    }

    public ViewSkinAdapter(View view2, AttributeSet attributeSet) {
        Context context = view2.getContext();
        this.mContext = context;
        this.mSkinProperter = SkinUtil.initSkinAttrs(context, attributeSet);
    }

    public void updateView(View view2) {
        if (!this.mSkinProperter.isEmpty()) {
            SkinManager.getInstance().updateView(view2, NigthModeGlobal.isNightMode(), false);
        }
    }

    public static ISkin.ISkinAdapter build(Context context, SkinItems skinItems) {
        return new ViewSkinAdapter(context, skinItems);
    }

    public final void initSkin(View view2) {
        this.isInit = true;
        this.view = (T) view2;
        if (this.mBackground == null) {
            this.mBackground = new SkinWrapper4Background();
        }
        this.mBackground.init(this.mContext, this.mSkinProperter);
        initSkinImpl(view2);
    }

    /* access modifiers changed from: protected */
    public final void onUpdateRes() {
        this.isUpadteRes = true;
    }

    /* access modifiers changed from: protected */
    public void applyImpl(boolean z) {
        ViewApplyImplListener viewApplyImplListener2 = this.viewApplyImplListener;
        if (viewApplyImplListener2 != null) {
            viewApplyImplListener2.onSkinApplyImpl(z);
        }
    }

    public void setViewApplyImplListener(ViewApplyImplListener viewApplyImplListener2) {
        this.viewApplyImplListener = viewApplyImplListener2;
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedChangeRes(boolean z) {
        if (!this.isUpadteRes && this.isNightMode == z) {
            return false;
        }
        return true;
    }

    public final void apply(boolean z) {
        if (isNeedChangeRes(z)) {
            this.isNightMode = z;
            this.isUpadteRes = false;
            SkinWrapper4Background skinWrapper4Background = this.mBackground;
            if (skinWrapper4Background != null) {
                skinWrapper4Background.apply(this.view, z);
            }
            applyImpl(z);
        }
    }

    public void setBackground(int i, int i2) {
        ResBean resBean = new ResBean();
        resBean.setDefaultResId(i);
        resBean.setNightResId(i2);
        this.mSkinProperter.setBackground(resBean);
        if (this.mBackground == null) {
            this.mBackground = new SkinWrapper4Background();
        }
        this.mBackground.init(this.mContext, this.mSkinProperter);
        onUpdateRes();
    }
}
