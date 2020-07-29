package com.xiniu.datarecycle.RightScrollAcvitity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.ImageView;
import android.widget.OverScroller;

import com.xiniu.datarecycle.R;

import androidx.annotation.Nullable;

/**
 * 创建者：wyz
 * 创建时间：2020-07-28
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */


@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    private GestureDetector gestureDetector;
    private OverScroller scroller;
    private int ScrollX;
    private float firstPosition;

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
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                setX(getX()-distanceX);
                scrollBy((int)distanceX,0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        scroller = new OverScroller(context);
        firstPosition = getX();
    }

    VelocityTracker velocityTracker;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ScrollX = getScrollX();//相对于初始位置滑动的距离
                velocityTracker = VelocityTracker.obtain();
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                ScrollX = getScrollX();//相对于初始位置滑动的距离
                break;
            case MotionEvent.ACTION_UP:
                Log.e( " onTouchEvent: ",getScrollX()+"");
                if (Math.abs(getScrollX()) > 150) {
                    if (getScrollX()>150){
                        scroller.startScroll(ScrollX, 0, 300, 0);
                    }else{
                        scroller.startScroll(ScrollX, 0, -300, 0);
                    }
                } else {
                    velocityTracker.computeCurrentVelocity(10);
                    float xVelocity = velocityTracker.getXVelocity();//速度
                    if (xVelocity < 0) {
                        if (Math.abs(xVelocity) > 10) {
                            scroller.startScroll(ScrollX, 0,  - 300, 0);
                        } else {
                            scroller.startScroll(ScrollX, 0, 300, 0);
                        }
                    } else {
                        if (xVelocity > 10) {
                            scroller.startScroll(ScrollX, 0, - 300, 0);

                        } else {
                            scroller.startScroll(ScrollX, 0, 300, 0);
                        }
                    }
                }

                invalidate();//使用

                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
    }

    public void onDestroy() {
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
    }

}
