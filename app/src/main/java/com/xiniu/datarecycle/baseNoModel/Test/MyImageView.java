package com.xiniu.datarecycle.baseNoModel.Test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.OverScroller;

import androidx.annotation.Nullable;

/**
 * 创建者：wyz
 * 创建时间：2020-07-20
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    private OverScroller scroller;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        scroller = new OverScroller(context);
    }


    //  规则：左加右减，上加下减

    public void smoothReset() {
        scroller.startScroll(this.getScrollX(), this.getScrollY(), -getScrollX(), -getScrollY(), 1000);
        invalidate(); //强制重绘 --->draw()--->computeScroll()
    }


    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            // 小幅度的移动
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate(); // 强制重绘 --->draw()--->computeScroll()

        }

    }
}
