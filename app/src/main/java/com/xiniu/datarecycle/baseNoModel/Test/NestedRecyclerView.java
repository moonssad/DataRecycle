package com.xiniu.datarecycle.baseNoModel.Test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import com.xiniu.datarecycle.CarHandApplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-17
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */


public class NestedRecyclerView extends RecyclerView {


    private float topPadding = dp2Px(50);
    private float scrollSlop = dp2Px(50); //滑动超过这个距离松手后自动自动滑动到顶部
    private float downY;
    private float moveY;
    private float deltaMoveY;
    private float deltaDownMoveY;
    private float lastMoveY;
    public OverScroller mScroller;
    private int firstCompletelyVisiblePosition;

    public NestedRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public NestedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new OverScroller(context);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("NestedRecyclerView", "ACTION_DOWN");
                downY = event.getRawY();
                lastMoveY = event.getRawY();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("NestedRecyclerView", "ACTION_DOWN");
                downY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = event.getRawY();
                deltaMoveY = moveY - lastMoveY;
                deltaDownMoveY = moveY - downY;
                Log.e("ACTION_MOVE", "data params "+deltaMoveY+"|"+event.getY()+"|"+
                        getTop()+"|"+((View) getParent()).getScrollY()+"|");

                //向上滑动 手指滑动到 recyclerview 顶部或者以上  触发嵌套滑动
                //event.getY() <= 0 且 getTop() - ((View) getParent()).getScrollY() > topPadding
                if (deltaMoveY < 0) {
                    Log.e("ACTION_MOVE", "deltaMoveY < 0 ");
                    if (event.getY() <= 0) {
                        Log.e("ACTION_MOVE", "event.getY() <= 0");
                        if (getTop() - ((View) getParent()).getScrollY() > topPadding) {
                            Log.e("ACTION_MOVE", "getTop() - ((View) getParent()).getScrollY() > topPadding");
                            setNestedScrollingEnabled(true);
                            startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                        } else {
                            Log.e("ACTION_MOVE", "getTop() - ((View) getParent()).getScrollY() <=topPadding");
                            setNestedScrollingEnabled(false);
                        }
                    } else {
                        Log.e("ACTION_MOVE", "event.getY() >0");
                        setNestedScrollingEnabled(false);
                    }
                } else {
                    //向下滑动
                    //先向下滑动 recyclerview，等待 recyclerview 全部展现后触发嵌套滑动
                    Log.e("ACTION_MOVE", "deltaMoveY >0 ");
                    LinearLayoutManager layoutManager = ((LinearLayoutManager) getLayoutManager());
                    firstCompletelyVisiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if (firstCompletelyVisiblePosition == 0 && ((View) getParent()).getScrollY() > 0) {
                        Log.e("ACTION_MOVE", "firstCompletelyVisiblePosition == 0 && ((View) getParent()).getScrollY() > 0 ");
                        setNestedScrollingEnabled(true);
                        startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                    } else {
                        Log.e("ACTION_MOVE", "firstCompletelyVisiblePosition == 0 && ((View) getParent()).getScrollY() <= 0 ");
                        setNestedScrollingEnabled(false);
                    }
                }
                lastMoveY = moveY;
                break;
            case MotionEvent.ACTION_UP:

                int parentScrollY = ((View) getParent()).getScrollY();
                int startY = parentScrollY;
                int dy;

                if (deltaDownMoveY < 0) {
                    //向上滑动切滑动距离超过 scrollSlop 滑动到顶部
                    //向上滑动切滑动距离不超过 scrollSlop 滑回到底部

                    if (parentScrollY >= scrollSlop) {
                        dy = (int) (getTop() - ((View) getParent()).getScrollY() - topPadding);
                    } else {
                        dy = -parentScrollY;
                    }
                } else {
                    //向下滑动切滑动距离超过 scrollSlop 滑动到底部
                    //向下滑动切滑动距离不超过 scrollSlop 滑回到顶部
                    if (getTop() - ((View) getParent()).getScrollY() > scrollSlop + topPadding) {
                        dy = -parentScrollY;
                    } else {
                        dy = (int) (getTop() - ((View) getParent()).getScrollY() - topPadding);
                    }
                }

                Log.e("ACTION_UP: ", startY + "|" + dy);

                mScroller.startScroll(0, startY, 0
                        , dy, 300);
                ((View) getParent()).invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    public OverScroller getScroller() {
        return mScroller;
    }

    public static int dp2Px(float px) {
        float step = CarHandApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (step * px + 0.5f);
    }

}


