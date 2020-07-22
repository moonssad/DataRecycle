package com.xiniu.datarecycle.coordinatorLayoutTest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 创建者：wyz
 * 创建时间：2020-07-21
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    private int mToolbarHeight = 0;

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //这里是显示以来
    //在xml里书写app:layout_anchor="@id/iv" 是隐式依赖
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency instanceof ImageView;
    }

    /**
     * 必须要加上  layout_anchor，对方也要layout_collapseMode才能使用
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        // 初始化高度
        if (mToolbarHeight == 0) {
            mToolbarHeight = child.getBottom() * 2;//为了更慢的
        }
        //
        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY() / mToolbarHeight;

        //百分大于1，直接赋值为1
        if (percent >= 1) {
            percent = 1f;
        }
        // 计算alpha通道值
        float alpha = percent * 255;
        //设置背景颜色
        child.setBackgroundColor(Color.argb((int) alpha, 63, 81, 181));
        return true;
    }
}

