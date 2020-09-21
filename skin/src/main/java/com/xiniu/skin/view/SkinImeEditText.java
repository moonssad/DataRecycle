package com.xiniu.skin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.autonavi.auto.common.view.AutoEditText;
import com.xiniu.skin.impl.TextViewSkinAdapter;
import com.xiniu.skin.inter.ISkin;

public class SkinImeEditText extends AutoEditText implements ISkin, ISkin.ITextViewSkin {
    private TextViewSkinAdapter mWrapper;

    public SkinImeEditText(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public SkinImeEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public SkinImeEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        this.mWrapper = new TextViewSkinAdapter((View) this, attributeSet);
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
