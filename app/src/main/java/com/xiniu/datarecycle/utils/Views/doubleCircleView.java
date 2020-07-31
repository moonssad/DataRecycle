package com.xiniu.datarecycle.utils.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.xiniu.datarecycle.R;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 创建者：wyz
 * 创建时间：2020-07-31
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class doubleCircleView extends View {
    private Paint leftPaint, RightPaint, linePaint;
    private int width, height;
    private int dimission = 800;
    private int radius = 50;
    CircleDelta circleDelta;
    private float leftPoint, rightPoint;
    private float leftRadius, rightRadius;


    public doubleCircleView(Context context) {
        this(context, null);
    }

    public doubleCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public doubleCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    public void initView() {
        leftPaint = new Paint();
        leftPaint.setAntiAlias(true);
        leftPaint.setStrokeWidth(10);
        leftPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        leftPaint.setColor(getContext().getResources().getColor(R.color.colorAccent));

        RightPaint = new Paint();
        RightPaint.setAntiAlias(true);
        RightPaint.setStrokeWidth(10);
        RightPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        RightPaint.setColor(getContext().getResources().getColor(R.color.colorPrimary));


        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setColor(getContext().getResources().getColor(R.color.color_918f8e));
        circleDelta = new CircleDelta();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        double delta = circleDelta.updata();
        leftPoint = width / 2 - dimission / 2 + dimission * (float) Math.sin(delta);
        rightPoint = width / 2 + dimission / 2 - dimission * (float) Math.sin(delta);

        if (delta > Math.PI / 2) {
            leftRadius = radius + radius * (float) Math.sin(delta*2)/4;
            rightRadius = radius - radius * (float) Math.sin(delta*2)/4;
            canvas.drawCircle(leftPoint, height / 2, leftRadius, leftPaint);
            canvas.drawCircle(rightPoint, height / 2, rightRadius, RightPaint);
        }
        else {
            leftRadius = radius +radius * (float) Math.sin(delta*2)/4;
            rightRadius = radius - radius * (float) Math.sin(delta*2)/4;
            canvas.drawCircle(rightPoint, height / 2, rightRadius, RightPaint);
            canvas.drawCircle(leftPoint, height / 2, leftRadius, leftPaint);
        }
        if (Math.abs(rightPoint - leftPoint) > radius * 2) {
            float[] positions = getLinePosition(leftPoint, rightPoint);
            RectF rect = new RectF(positions[0] - leftRadius, height / 2 - 5, positions[1] + rightRadius, height / 2 + 5);
            canvas.drawRoundRect(rect, 5, 5, linePaint);
        }
        invalidate();
    }

    private float[] getLinePosition(float left, float right) {
        float[] positions = new float[2];
        positions[0] = Math.max(left, right);
        positions[1] = Math.min(left, right);
        return positions;
    }

}
