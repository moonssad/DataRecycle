package com.xiniu.datarecycle.RightScrollAcvitity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

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
public class MyIMageViewTouch extends ImageView {
    public Context context;
    private int position;

    public MyIMageViewTouch(Context context) {
        this(context,null);
    }

    public MyIMageViewTouch(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyIMageViewTouch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context) {
        this.context = context;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
