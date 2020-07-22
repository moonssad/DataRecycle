package com.xiniu.datarecycle.mvvm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * 创建者：wyz
 * 创建时间：2020-07-08
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
@SuppressLint("AppCompatCustomView")
public class ShakeImageView extends ImageView {
    ListenerInfo listenerInfo;

    public ShakeImageView(Context context) {
        super(context);
        init();
    }

    public ShakeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShakeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnLongClickListener(onLongClickListener listener) {
        getListener().mOnLongClickListener = listener;

    }

    public void setOnClickListener(onClickListener listener) {
        getListener().mOnClickListener = listener;


    }


    public void init() {
        //抖动特效
        setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Animation animation = new RotateAnimation(-4, 4, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setRepeatCount(6);
                animation.setDuration(60);
                animation.setRepeatMode(Animation.REVERSE);
                v.startAnimation(animation);
                if (getListener().mOnLongClickListener != null) {
                    return getListener().mOnLongClickListener.onLongClick(v);
                }
                return false;
            }
        });

        //方法缩小的特效
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation animation = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setRepeatCount(1);
                animation.setDuration(100);
                animation.setRepeatMode(Animation.REVERSE);
                v.startAnimation(animation);
                if (getListener().mOnClickListener != null) {
                    getListener().mOnClickListener.onClick(v);
                }
            }
        });
    }


    ListenerInfo getListener() {
        if (listenerInfo != null) {
            return listenerInfo;
        }
        listenerInfo = new ListenerInfo();
        return listenerInfo;
    }


    static class ListenerInfo {
        onLongClickListener mOnLongClickListener;
        onClickListener mOnClickListener;
    }

    public interface onLongClickListener {
        boolean onLongClick(View view);
    }

    public interface onClickListener {
        void onClick(View view);
    }
}
