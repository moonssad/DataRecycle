package com.xiniu.skin.impl;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.SeekBar;

import com.xiniu.skin.SkinItems;
import com.xiniu.skin.inter.ISkinWrapper;
import com.xiniu.skin.view.SkinSeekBar;


public class SkinWrapper4Thumb implements ISkinWrapper<SeekBar> {
    private Context mContext;
    private SkinItems skinItems;

    public void init(Context context, SkinItems skinItems2) {
        this.mContext = context;
        this.skinItems = skinItems2;
    }

    public void apply(SeekBar seekBar, boolean z) {
        int i;
        Drawable thumbSKin;
        SkinItems skinItems2 = this.skinItems;
        if (skinItems2 != null && skinItems2.getThumb() != null) {
            if (z) {
                i = this.skinItems.getThumb().getNightResId();
                seekBar.getThumbOffset();
                seekBar.getThumbOffset();
            } else {
                i = this.skinItems.getThumb().getDefaultResId();
                seekBar.getThumbOffset();
                seekBar.getThumbOffset();
            }
            if (i > 0) {
                Drawable drawable = this.mContext.getResources().getDrawable(i);
                int thumbOffset = seekBar.getThumbOffset();
                if (Build.VERSION.SDK_INT >= 16) {
                    int i2 = seekBar.getThumb().getBounds().left;
                    int i3 = seekBar.getThumb().getBounds().right;
                    int i4 = seekBar.getThumb().getBounds().top;
                    int i5 = seekBar.getThumb().getBounds().bottom;
                    seekBar.setThumb(drawable);
                    drawable.setBounds(i2, i4, i3, i5);
                    seekBar.setThumbOffset(thumbOffset);
                } else if ((seekBar instanceof SkinSeekBar) && (thumbSKin = ((SkinSeekBar) seekBar).getThumbSKin()) != null) {
                    Rect bounds = thumbSKin.getBounds();
                    int i6 = bounds.left;
                    int i7 = bounds.right;
                    int i8 = bounds.top;
                    int i9 = bounds.bottom;
                    seekBar.setThumb(drawable);
                    drawable.setBounds(i6, i8, i7, i9);
                    seekBar.setThumbOffset(thumbOffset);
                }
            }
        }
    }
}
