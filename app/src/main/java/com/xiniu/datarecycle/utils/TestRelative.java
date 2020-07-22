package com.xiniu.datarecycle.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * 创建者：wyz
 * 创建时间：2020-07-08
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TestRelative extends RelativeLayout {

    public TestRelative(Context context) {
        super(context);
        init();
    }

    public TestRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Animation animation = new RotateAnimation(-4,4,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                animation.setRepeatCount(6);
                animation.setDuration(60);
                animation.setRepeatMode(Animation.REVERSE);
                v.startAnimation(animation);
                return false;
            }
        });

    }


}
