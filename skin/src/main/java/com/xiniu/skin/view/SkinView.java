package com.xiniu.skin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.autonavi.auto.common.shadow.IShadowView;
import com.autonavi.auto.common.shadow.ShadowViewController;
import com.xiniu.skin.impl.ViewSkinAdapter;
import com.xiniu.skin.inter.ISkin;


public class SkinView extends View implements ISkin, ISkin.IViewSkin, IShadowView {
    private ShadowViewController mShadowController;
    private ViewSkinAdapter mWrapper;

    public SkinView(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public SkinView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public SkinView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        ViewSkinAdapter viewSkinAdapter = new ViewSkinAdapter((View) this, attributeSet);
        this.mWrapper = viewSkinAdapter;
        viewSkinAdapter.updateView(this);
        initShadowView(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw(canvas, this);
    }

    public void initShadowView(AttributeSet attributeSet) {
        if (this.mShadowController == null) {
            this.mShadowController = new ShadowViewController(this, attributeSet);
        }
    }

    public void draw(Canvas canvas, View view) {
        ShadowViewController shadowViewController = this.mShadowController;
        if (shadowViewController != null) {
            shadowViewController.draw(canvas, view);
        }
    }

    public void setShadowVisibility(int i) {
        ShadowViewController shadowViewController = this.mShadowController;
        if (shadowViewController != null) {
            shadowViewController.setVisibility(i);
        }
    }

    public ISkin.ISkinAdapter getAdpter() {
        return this.mWrapper;
    }

    public void setBackgroundResource(int i) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        super.setBackgroundResource(i);
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public void setBackground(int i, int i2) {
        this.mWrapper.setBackground(i, i2);
    }
}
