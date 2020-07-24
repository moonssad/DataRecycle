package com.xiniu.datarecycle.baseNoModel.Base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import com.xiniu.datarecycle.R;

import androidx.annotation.NonNull;

/**
 * 创建者：wyz
 * 创建时间：2020-07-24
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public abstract class BaseDialog extends Dialog {
    private static final int DISMISS = 0x43;
    private static final int SHOW = 0x45;
    private boolean doOntouchSelf = false;
    private Context context;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case DISMISS:
                    dismiss();
                    break;
                case SHOW:
                    show();
                    break;
            }
            return true;
        }
    });


    public BaseDialog(@NonNull Activity context) {
        this(context, R.style.baseDialogStyle);//通过style来设置window的属性
    }

    public BaseDialog(@NonNull Activity context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initParams();
    }


    public void initParams() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) getWindow().getAttributes();
            params.gravity = Gravity.CENTER;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
    }

    public void setOnTouchSelf(boolean onTouch) {
        this.doOntouchSelf = onTouch;
    }

    public abstract int getLayoutId();

    public abstract void initView();

    //显示后自动小时
    public void showAutoDismiss(int time) {
        super.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = handler.obtainMessage();
                message.what = DISMISS;
                message.sendToTarget();
            }
        }, time);
    }

    public void show() {
        super.show();
    }


    public abstract void onTouchEventSelf(@NonNull MotionEvent event);


    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (!doOntouchSelf) {
            if (isOutOfBounds(event)) {
                dismiss();
            }
        } else {
            onTouchEventSelf(event);
        }
        return super.onTouchEvent(event);
    }


    public boolean isOutOfBounds(MotionEvent event) {
        int xPosition = (int) event.getX();
        int yPosition = (int) event.getY();
        int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        if (getWindow() != null) {
            View decor = getWindow().getDecorView();
            return (xPosition < -slop || xPosition > (decor.getWidth() + slop) || yPosition < -slop || yPosition > (decor.getHeight() + slop));
        } else {
            return true;
        }

    }
}
