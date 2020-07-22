package com.xiniu.datarecycle.baseNoModel.Test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.xiniu.datarecycle.R;

import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-17
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class LinearLayoutNestScrollParent extends LinearLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper mParentHelper;
    private NestedRecyclerView recyclerview;

    public LinearLayoutNestScrollParent(Context context) {
        this(context, null);
    }

    public LinearLayoutNestScrollParent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerview = findViewById(R.id.rv_photos);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//         imgHeight = imgView.getMeasuredHeight();
//        Log.e( "onMeasure: ",imgHeight+"");
        //重新测量高度
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        if (target instanceof RecyclerView) {
            return true;
        }
        return false;
    }


    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        mParentHelper.onStopNestedScroll(target);
    }

    //先于 child 滚动
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        scrollBy(0, dy);//滚动
        consumed[1] = dy;//告诉child我消费了多少
    }

    //后于child滚动
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    //返回值：是否消费了fling
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    //返回值：是否消费了fling
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mParentHelper.getNestedScrollAxes();
    }



    //滑动
    @Override
    public void computeScroll() {
        OverScroller scroller = recyclerview.getScroller();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}

