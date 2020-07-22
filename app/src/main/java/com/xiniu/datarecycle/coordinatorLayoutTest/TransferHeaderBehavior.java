package com.xiniu.datarecycle.coordinatorLayoutTest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 创建者：wyz
 * 创建时间：2020-07-21
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TransferHeaderBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private int mOriginalHeaderX = 0;

    private int mOriginalHeaderY = 0;


    public TransferHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {

        return dependency instanceof ImageView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {

        if (mOriginalHeaderX == 0) {
            this.mOriginalHeaderX = dependency.getWidth()/2  - child.getWidth() /2;
        }
        // 计算Y轴坐标
        if (mOriginalHeaderY == 0) {
            mOriginalHeaderY = dependency.getHeight() - child.getHeight();
        }
        //X轴百分比
        float mPercentX = dependency.getY() / mOriginalHeaderX;
        if (mPercentX >= 1) {
            mPercentX = 1;
        }
        //Y轴百分比
        float mPercentY = dependency.getY() / mOriginalHeaderY;
        if (mPercentY >= 1) {
            mPercentY = 1;
        }

        float x = mOriginalHeaderX - mOriginalHeaderX * mPercentX;
        //设置到达最高点事x轴和decorview的padding
        if (x <= 20) {
            x = 20;
        }
        // TODO 头像的放大和缩小没做

        child.setX(x);
        child.setY(mOriginalHeaderY - mOriginalHeaderY * mPercentY);
        child.setScaleX(1.5f - mPercentY/2);
        child.setScaleY(1.5f - mPercentY/2);

        return super.onDependentViewChanged(parent, child, dependency);
    }
}
