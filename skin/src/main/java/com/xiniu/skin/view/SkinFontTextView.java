package com.xiniu.skin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class SkinFontTextView extends SkinTextView {
    static Typeface iconfont;
    Paint.FontMetricsInt fontMetricsInt;

    public SkinFontTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinFontTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SkinFontTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* access modifiers changed from: protected */
    public void initTypeface(AttributeSet attributeSet) {
        if (iconfont == null) {
            iconfont = Typeface.createFromAsset(getResources().getAssets(), "icomoon.ttf");
        }
        setTypeface(iconfont);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.fontMetricsInt == null) {
            this.fontMetricsInt = new Paint.FontMetricsInt();
            getPaint().getFontMetricsInt(this.fontMetricsInt);
        }
        canvas.translate(0.0f, (float) (this.fontMetricsInt.top - this.fontMetricsInt.ascent));
    }
}
