package com.xiniu.skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.xiniu.skin.inter.ISkin;


public class SkinUtil {
    public static SkinItems initSkinAttrs(Context context, AttributeSet attributeSet) {
        SkinItems skinItems = new SkinItems();
        if (attributeSet == null) {
            return skinItems;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.autoSkin);
        skinItems.setTextColor(getResBean(obtainStyledAttributes, R.styleable.autoSkin_textColor4Skin, R.styleable.autoSkin_textColor4Night));
        skinItems.setBackground(getResBean(obtainStyledAttributes, R.styleable.autoSkin_background4Skin, R.styleable.autoSkin_background4Night));
        skinItems.setSrc(getResBean(obtainStyledAttributes, R.styleable.autoSkin_src4Skin, R.styleable.autoSkin_src4Night));
        skinItems.setDrawableBottom(getResBean(obtainStyledAttributes, R.styleable.autoSkin_drawableBottom4Skin, R.styleable.autoSkin_drawableBottom4Night));
        skinItems.setDrawableLeft(getResBean(obtainStyledAttributes, R.styleable.autoSkin_drawableLeft4Skin, R.styleable.autoSkin_drawableLeft4Night));
        skinItems.setDrawableTop(getResBean(obtainStyledAttributes, R.styleable.autoSkin_drawableTop4Skin, R.styleable.autoSkin_drawableTop4Night));
        skinItems.setDrawableRight(getResBean(obtainStyledAttributes, R.styleable.autoSkin_drawableRight4Skin, R.styleable.autoSkin_drawableRight4Night));
        skinItems.setTextColorHint(getResBean(obtainStyledAttributes, R.styleable.autoSkin_textColorHint4Skin, R.styleable.autoSkin_textColorHint4Night));
        skinItems.setProgressDrawable(getResBean(obtainStyledAttributes, R.styleable.autoSkin_progressDrawable4Skin, R.styleable.autoSkin_progressDrawable4Night));
        skinItems.setThumb(getResBean(obtainStyledAttributes, R.styleable.autoSkin_thumb4Skin, R.styleable.autoSkin_thumb4Night));
        obtainStyledAttributes.recycle();
        return skinItems;
    }

    private static ResBean getResBean(TypedArray typedArray, int i, int i2) {
        int resourceId;
        int resourceId2 = typedArray.getResourceId(i, -1);
        if (resourceId2 == -1 || (resourceId = typedArray.getResourceId(i2, -1)) == -1) {
            return null;
        }
        ResBean resBean = new ResBean();
        resBean.setDefaultResId(resourceId2);
        resBean.setNightResId(resourceId);
        return resBean;
    }

    public static void setTextColor(View view, int i, int i2) {
        if (view != null && (view instanceof ISkin.ITextViewSkin)) {
            ((ISkin.ITextViewSkin) view).setTextColor(i, i2);
        }
    }

    public static void setBackgroudResource(View view, int i, int i2) {
        if (view != null && (view instanceof ISkin.IViewSkin)) {
            ((ISkin.IViewSkin) view).setBackground(i, i2);
        }
    }

    public static void setImageResource(View view, int i, int i2) {
        if (view != null && (view instanceof ISkin.IImageViewSkin)) {
            ((ISkin.IImageViewSkin) view).setImageResource(i, i2);
        }
    }
}
