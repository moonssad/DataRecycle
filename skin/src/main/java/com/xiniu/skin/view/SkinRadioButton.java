package com.xiniu.skin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import com.autonavi.auto.common.shadow.IShadowView;
import com.autonavi.auto.common.shadow.ShadowViewController;
import com.xiniu.skin.impl.TextViewSkinAdapter;
import com.xiniu.skin.inter.ISkin;


public class SkinRadioButton extends RadioButton implements ISkin, ISkin.ITextViewSkin, IShadowView {
    private ShadowViewController mShadowController;
    private TextViewSkinAdapter mWrapper;

    public SkinRadioButton(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public SkinRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public SkinRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        this.mWrapper = new TextViewSkinAdapter((View) this, attributeSet);
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

    public void setTextColor(int i, int i2) {
        this.mWrapper.setTextColor(i, i2);
    }

    public void setHintTextColor(int i, int i2) {
        this.mWrapper.setHintTextColor(i, i2);
    }
}
