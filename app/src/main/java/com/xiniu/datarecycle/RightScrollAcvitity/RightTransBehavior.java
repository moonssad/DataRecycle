package com.xiniu.datarecycle.RightScrollAcvitity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.xiniu.datarecycle.R;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 创建者：wyz
 * 创建时间：2020-07-27
 * 功能描述：
 * 更新者：
 * 更新时间：CoordinatorLayout.Behavior
 * 更新描述：
 */
public class RightTransBehavior extends CoordinatorLayout.Behavior<View> {
    private int mOriginalHeaderX = 0;


    public RightTransBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof ImageView;
    }


    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        if (mOriginalHeaderX == 0) {
            this.mOriginalHeaderX = -300;
        }
        float percent = (float)dependency.getScrollX() / (float)mOriginalHeaderX;
        Log.e( "dependency: ",dependency.getX()+"|"+dependency.getScrollX()+percent);
        if (percent >= 1) {
            percent = 1f;
        }
        child.setTranslationX(mOriginalHeaderX - mOriginalHeaderX * percent);

        Log.e( "onDependentViewChanged",""+dependency.getScrollX());
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
