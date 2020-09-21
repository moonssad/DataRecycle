package com.xiniu.skin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.reflect.Field;

public class SkinStrokeTextView extends SkinTextView {
    private int StrokeTextWidth;
    private int mInnerColor;
    private int mInnerTextColorDay;
    private int mInnerTextColorNight;
    private int mStrokeTextColor;
    private int mStrokeTextColorDay;
    private int mStrokeTextColorNight;
    private TextPaint mTextPaint;

    public SkinStrokeTextView(Context context) {
        super(context);
        this.mTextPaint = getPaint();
    }

    public SkinStrokeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinStrokeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setStrokeTextColor(int i, int i2) {
        this.mStrokeTextColorDay = i;
        this.mStrokeTextColorNight = i2;
    }

    public void setInnerTextColor(int i, int i2) {
        this.mInnerTextColorDay = i;
        this.mInnerTextColorNight = i2;
    }

    public void setStrokeTextWidth(int i) {
        this.StrokeTextWidth = i;
    }

    public void updateStrokeTextColor(boolean z) {
        if (z) {
            this.mStrokeTextColor = this.mStrokeTextColorNight;
        } else {
            this.mStrokeTextColor = this.mStrokeTextColorDay;
        }
    }

    public void updateInnerTextColor(boolean z) {
        if (z) {
            this.mInnerColor = this.mInnerTextColorNight;
        } else {
            this.mInnerColor = this.mInnerTextColorDay;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        setTextColorUseReflection(this.mStrokeTextColor);
        this.mTextPaint.setStrokeWidth((float) this.StrokeTextWidth);
        this.mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setShadowLayer(1.0f, 0.0f, 0.0f, 0);
        super.onDraw(canvas);
        setTextColorUseReflection(this.mInnerColor);
        this.mTextPaint.setStrokeWidth(0.0f);
        this.mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mTextPaint.setFakeBoldText(true);
        this.mTextPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        super.onDraw(canvas);
    }

    private void setTextColorUseReflection(int i) {
        try {
            Field declaredField = TextView.class.getDeclaredField("mCurTextColor");
            declaredField.setAccessible(true);
            declaredField.set(this, Integer.valueOf(i));
            declaredField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
        this.mTextPaint.setColor(i);
    }
}
