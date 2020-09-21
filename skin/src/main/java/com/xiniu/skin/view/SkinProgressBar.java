package com.xiniu.skin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import com.autonavi.auto.common.shadow.IShadowView;
import com.autonavi.auto.common.shadow.ShadowViewController;
import com.xiniu.skin.impl.ProgressBarSkinAdapter;
import com.xiniu.skin.inter.ISkin;


public class SkinProgressBar extends ProgressBar implements ISkin, ISkin.IProgressBarViewSkin, IShadowView {
    private ShadowViewController mShadowController;
    private ProgressBarSkinAdapter mWrapper;

    public SkinProgressBar(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public SkinProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public SkinProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        ProgressBarSkinAdapter progressBarSkinAdapter = new ProgressBarSkinAdapter((View) this, attributeSet);
        this.mWrapper = progressBarSkinAdapter;
        progressBarSkinAdapter.updateView(this);
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

    public void setProgressDrawable(int i, int i2) {
        this.mWrapper.setProgressDrawable(i, i2);
    }
}
