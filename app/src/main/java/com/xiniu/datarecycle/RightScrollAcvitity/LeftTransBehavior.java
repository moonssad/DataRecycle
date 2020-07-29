package com.xiniu.datarecycle.RightScrollAcvitity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import static com.xiniu.datarecycle.CarHandApplication.getContext;

/**
 * 创建者：wyz
 * 创建时间：2020-07-28
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class LeftTransBehavior extends CoordinatorLayout.Behavior<View> {
    private int mOriginalHeaderX = 0;

    public LeftTransBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof ImageView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (mOriginalHeaderX == 0) {
            WindowManager window = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            window.getDefaultDisplay().getMetrics(displayMetrics);
            this.mOriginalHeaderX = displayMetrics.heightPixels + 300;
        }
        float percent = (float) dependency.getScrollX() / (float) 300;
        Log.e("dependency: ", dependency.getX() + "|" + dependency.getScrollX() + percent);
        if (percent >= 1) {
            percent = 1f;
        }
        child.setTranslationX(mOriginalHeaderX - mOriginalHeaderX * percent);

        Log.e("onDependentViewChanged", mOriginalHeaderX - mOriginalHeaderX * percent + "");
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
