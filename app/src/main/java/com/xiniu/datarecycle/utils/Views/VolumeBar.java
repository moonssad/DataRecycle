package com.xiniu.datarecycle.utils.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.xiniu.datarecycle.R;

import java.lang.reflect.Field;

/**
 * Data 2020/8/24
 * author wyz
 **/
public class VolumeBar extends View {
    private Paint paintBg, paintCover, paintLine;
    private int progress = 5;
    private int minValue, maxValue;
    private int lineWidth;
    LinearGradient linearGradient;
    Matrix matrix;

    public VolumeBar(Context context) {
        this(context, null);
    }

    public VolumeBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //todo 初始化画笔
        paintBg = new Paint();
        paintBg.setAntiAlias(true);
        paintBg.setStyle(Paint.Style.FILL_AND_STROKE);
        paintBg.setColor(getResources().getColor(R.color.gray_alpha));
        paintCover = new Paint();
        paintCover.setAntiAlias(true);
        paintCover.setColor(getResources().getColor(R.color.auto_color_3f8ee9_60));
        paintCover.setStyle(Paint.Style.FILL);
        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(getResources().getColor(R.color.auto_color_ff0096ff));
        paintLine.setStyle(Paint.Style.FILL_AND_STROKE);
        minValue = 0;
        maxValue = 10;
        lineWidth = 10;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (linearGradient==null){
            linearGradient = new LinearGradient(0, 0,width, height, getResources().getColor(R.color.auto_color_3f8ee9_60), getResources().getColor(R.color.auto_color_ff0096ff), Shader.TileMode.REPEAT);
            matrix = new Matrix();
        }
        if (width < height * 4) {
            setMeasuredDimension(height * 4, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paintBg);
        matrix.setScale( (progress/10f),1f);
        linearGradient.setLocalMatrix(matrix);
        paintCover.setShader(linearGradient);
        canvas.drawRect(0, 0, (float) (getMeasuredWidth() * progress / 10f), getMeasuredHeight(), paintCover);
        if (progress < 10) {
            canvas.drawRect((float) (getMeasuredWidth() * progress / 10f), 0, (float) (getMeasuredWidth() * progress / 10d) + lineWidth, getMeasuredHeight(), paintLine);
        } else {
            canvas.drawRect((float) (getMeasuredWidth() * progress / 10f) - lineWidth, 0, (float) (getMeasuredWidth() * progress / 10d), getMeasuredHeight(), paintLine);
        }

    }

    public void setProgress(int num) {
        progress = ((num - minValue) * 10 / (maxValue - minValue));
        postInvalidate();
    }

    private int progress2RealVolume() {
        return (int) (progress / 10 * (maxValue - minValue));
    }


    OnVolumeChangedListener listener;

    public void setListener(OnVolumeChangedListener listener) {
        this.listener = listener;
    }

    public interface OnVolumeChangedListener {
        void onVolumeChanged(int volume);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                int p = progress2RealVolume();
                if (event.getX() < 0) {
                    progress = 0;
                } else if (event.getX() > getMeasuredWidth()) {
                    progress = 10;
                } else {
                    progress = (int) (event.getX() * 10 / getMeasuredWidth());
                }
                invalidate();
                if (p != progress2RealVolume()) {
                    if (listener != null) {
                        listener.onVolumeChanged(progress2RealVolume());
                    }
                }
                break;
        }
        return true;
    }

}
