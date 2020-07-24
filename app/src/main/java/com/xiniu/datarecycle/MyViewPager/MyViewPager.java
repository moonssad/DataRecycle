package com.xiniu.datarecycle.MyViewPager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.TextView;

import com.xiniu.datarecycle.R;

/**
 * 创建者：wyz
 * 创建时间：2020-07-23
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class MyViewPager extends ViewGroup {
    private Context context;
    private GestureDetector gestureDetector;
    private int[] images = new int[]{R.drawable.backgroud, R.drawable.background2, R.drawable.background3, R.drawable.background4};
    private OverScroller scroller;
    private int ScrollX, position;
    private int startX;
    private int startY;
    private int scrollPosition;

    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setBackgroundResource(images[i]);
            this.addView(iv);
        }
        gestureDetector = new GestureDetector(context, new SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX, 0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        scroller = new OverScroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(), t, (i + 1) * getWidth(), b);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 如果左右滑动, 就需要拦截, 上下滑动,不需要拦截
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                gestureDetector.onTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int dx = endX - startX;
                int dy = endY - startY;
                if (Math.abs(dx) > Math.abs(dy)) {
                    // 左右滑动
                    return true;// 中断事件传递, 不允许孩子响应事件了, 由父控件处理
                }
                break;
            default:
                break;
        }
        return false;// 不拦截事件,优先传递给孩子(也就是ScrollView，让它正常处理上下滑动事件)处理

    }


    VelocityTracker velocityTracker;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ScrollX = getScrollX();//相对于初始位置滑动的距离
                velocityTracker = VelocityTracker.obtain();
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                ScrollX = getScrollX();//相对于初始位置滑动的距离
//                position = (getScrollX() + getWidth() / 2) / getWidth();
//                if (position >= getChildCount()) {
//                    position = getChildCount() - 1;
//                }
//                if (position < 0) {
//                    position = 0;
//                }
                if (mOnPageScrollListener != null) {
                    mOnPageScrollListener.onPageScrolled(getScrollX() * 1.0f / getWidth());
                }
                break;
            case MotionEvent.ACTION_UP:
                scrollPosition = (getScrollX() + getWidth() / 2) / getWidth();
                if (scrollPosition >= getChildCount()) {
                    scrollPosition = getChildCount() - 1;
                }
                if (scrollPosition < 0) {
                    scrollPosition = 0;
                }
                velocityTracker.computeCurrentVelocity(10);
                float xVelocity = velocityTracker.getXVelocity();//速度
                if (xVelocity < 0) {
                    if (Math.abs(xVelocity) > 50) {
                        //todo 左滑
                        position = position + 1;
                        if (position >= getChildCount()) {
                            position = getChildCount() - 1;
                        }
                    } else {
                        position = scrollPosition;
                    }
                } else {
                    if (xVelocity > 50) {
                        //todo 优化
                        position = position - 1;
                        if (position < 0) {
                            position = 0;
                        }
                    } else {
                        position = scrollPosition;
                    }
                }
                scroller.startScroll(ScrollX, 0, -(ScrollX - position * getWidth()), 0);
                invalidate();//使用
                if (mOnPageScrollListener != null) {
                    mOnPageScrollListener.onPageSelected(position);
                }
                break;
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
    }

    public void autoChange() {
        if (position > getChildCount() - 2) {
            position = -1;
        }
        if (position == -1) {
            scroller.startScroll((getChildCount() - 1) * getWidth(), 0, -(getChildCount() - 1) * getWidth(), 0);
        } else {
            scroller.startScroll((position) * getWidth(), 0, getWidth(), 0, 1000);

        }
        position = position + 1;
        invalidate();
        if (mOnPageScrollListener != null) {
            mOnPageScrollListener.onPageSelected(position);
        }

    }

    public interface OnPageScrollListener {
        /**
         * @param offsetPercent offsetPercent：getScrollX滑动的距离占屏幕宽度的百分比
         */
        void onPageScrolled(float offsetPercent);

        void onPageSelected(int position);
    }

    private OnPageScrollListener mOnPageScrollListener;

    public void setOnPageScrollListener(OnPageScrollListener onPageScrollListener) {
        this.mOnPageScrollListener = onPageScrollListener;
    }
}
