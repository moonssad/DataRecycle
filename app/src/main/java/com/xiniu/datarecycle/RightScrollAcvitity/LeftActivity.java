package com.xiniu.datarecycle.RightScrollAcvitity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.rxbinding3.view.RxView;
import com.xiniu.datarecycle.R;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.reactivex.functions.Consumer;

/**
 * 创建者：wyz
 * 创建时间：2020-07-28
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class LeftActivity extends AppCompatActivity {
    private String TAG = "left";
    ImageView left_check;
    ConstraintLayout midefindView;
    int width;
    ValueAnimator valueAnimator;
    private float startPosition;
    private float moved;
    private float lastPosition, currentPosition;
    private int startAnimationX;
    VelocityTracker velocity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.left_activity);
        initView();

    }

    @SuppressLint({"ClickableViewAccessibility", "CheckResult"})
    public void initView() {
        left_check = (ImageView) findViewById(R.id.left_check);
        midefindView = (ConstraintLayout) findViewById(R.id.idefindView);
        valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(200);
        valueAnimator.addUpdateListener(listener);

        RxView.clicks(left_check).throttleFirst(400, TimeUnit.MICROSECONDS).
                subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object Object) throws Exception {
                        Log.e(TAG, "accept: ");
                        if (midefindView.getX() < 0) {
                            valueAnimator.setIntValues(startAnimationX, startAnimationX+ width);
                        } else {
                            valueAnimator.setIntValues((int) (left_check.getX()),startAnimationX);
                        }
                        valueAnimator.start();
                    }
                });


        left_check.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startPosition = event.getRawX();
                        lastPosition = event.getRawX();
                        moved = 0;
                       velocity = VelocityTracker.obtain();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        currentPosition = event.getRawX();
                        velocity.addMovement(event);
                        if (Math.abs(currentPosition - lastPosition) > 5) {
                            moved = event.getRawX() - startPosition;//moved表示移动的距离
                            if (Math.abs(moved) < width) {
                                left_check.setX(left_check.getX() + currentPosition - lastPosition);
                                midefindView.setX(midefindView.getX() + currentPosition - lastPosition);
                                lastPosition = currentPosition;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onTouch: "+moved+"|"+width);

                        if (Math.abs(moved) < 10) {
                            return LeftActivity.super.onTouchEvent(event);
                        }
                        velocity.computeCurrentVelocity(10);
                        float xVelocity = velocity.getXVelocity();//速度
                        velocity.recycle();
                        if (Math.abs(xVelocity)>5){
                            if (xVelocity>0){
                                valueAnimator.setIntValues((int) (left_check.getX()), startAnimationX+ width);
                                valueAnimator.start();
                                return true;
                            }else{
                                valueAnimator.setIntValues((int) (left_check.getX()), startAnimationX);
                                valueAnimator.start();
                                return true;
                            }
                        }

                        if ((Math.abs(moved) < width)) {
                            if (moved > 0) {
                                if (moved > (float) width / 2) {
                                    valueAnimator.setIntValues((int) (left_check.getX()), startAnimationX+ width);
                                    valueAnimator.start();
                                    return true;
                                } else {
                                    valueAnimator.setIntValues((int) (left_check.getX()), startAnimationX);
                                    valueAnimator.start();
                                    return true;
                                }
                            }
                            else {
                                if (Math.abs(moved) > (float) width / 2) {
                                    valueAnimator.setIntValues((int) (left_check.getX()), startAnimationX);
                                    valueAnimator.start();
                                    return true;
                                } else {
                                    valueAnimator.setIntValues((int) (left_check.getX()), startAnimationX+width);
                                    valueAnimator.start();
                                    return true;
                                }
                            }
                        } else {
                            return true;
                        }
                    default:
                        break;
                }
                return LeftActivity.super.onTouchEvent(event);
            }
        });

    }

    ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            int position = (int) animation.getAnimatedValue();
            left_check.setX(position);
            midefindView.setX(position - width);
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            startAnimationX =(int) left_check.getX();
            width= midefindView.getWidth();
        }
    }
}
