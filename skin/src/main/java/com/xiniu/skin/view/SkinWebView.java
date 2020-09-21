package com.xiniu.skin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

import com.xiniu.skin.impl.ViewSkinAdapter;
import com.xiniu.skin.inter.ISkin;


public class SkinWebView extends WebView implements ISkin, IShadowView {
    private ShadowViewController mShadowController;
    private ViewSkinAdapter mWrapper;
    private BaseWebViewListener webViewListener = null;

    public interface BaseWebViewListener {
        void onScrollChanged(SkinWebView skinWebView, int i, int i2, int i3, int i4);
    }

    public SkinWebView(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public SkinWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public SkinWebView(Context context, AttributeSet attributeSet, int i) {
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

    public void setWebViewListener(BaseWebViewListener baseWebViewListener) {
        this.webViewListener = baseWebViewListener;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        BaseWebViewListener baseWebViewListener = this.webViewListener;
        if (baseWebViewListener != null) {
            baseWebViewListener.onScrollChanged(this, i, i2, i3, i4);
        }
    }

    public ISkin.ISkinAdapter getAdpter() {
        return this.mWrapper;
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        this.mWrapper.initSkin(this);
    }
}
