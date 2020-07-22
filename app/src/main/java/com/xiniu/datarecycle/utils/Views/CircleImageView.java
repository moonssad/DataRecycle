package com.xiniu.datarecycle.utils.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-08
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class CircleImageView extends AppCompatImageView {

    private Paint mPaint;
    private Paint mPaint2;
    private BitmapShader mBitmapShader;

    int bw = 0;
    int bh = 0;

    public CircleImageView(Context context)
    {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(4);
        mPaint2.setColor(mBorderColor);
    }

    @Override
    public void setImageDrawable(Drawable drawable)
    {
        super.setImageDrawable(drawable);
        Bitmap bmp = getBitmapFromDrawable(drawable);
        if(bmp!=null) {
            mBitmapShader = new BitmapShader(bmp , Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint.setShader(mBitmapShader);
            bw = bmp.getWidth();
            bh = bmp.getHeight();
        }else{
            mBitmapShader = null;
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable)
    {
        if (drawable == null) return null;
        if (drawable instanceof BitmapDrawable)
            return ((BitmapDrawable) drawable).getBitmap();
        try
        {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
            }else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        Bitmap.Config.ARGB_8888);
            }
            bw = bitmap.getWidth();
            bh = bitmap.getHeight();
            return bitmap;
        }
        catch (OutOfMemoryError e)
        {
            return null;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm)
    {
        super.setImageBitmap(bm);
        if(bm!=null && !bm.isRecycled()) {
            mBitmapShader = new BitmapShader(bm, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
            mPaint.setShader(mBitmapShader);
        }else{
            mBitmapShader = null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas)
    {
        if (getDrawable() == null) return;
        final int saveCount = canvas.getSaveCount();
        canvas.save();
        if (getCropToPadding()) {
            final int scrollX = getScrollX();
            final int scrollY = getScrollY();
            canvas.clipRect(scrollX + getPaddingLeft(), scrollY + getPaddingTop(),
                    scrollX + getRight() - getLeft() - getPaddingRight(),
                    scrollY + getBottom() - getTop() - getPaddingBottom());
        }
        canvas.translate(getPaddingLeft(), getPaddingTop());

        if (getImageMatrix() != null && mBitmapShader!=null)
        {
            mBitmapShader.setLocalMatrix(getImageMatrix());
        }
        int h = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int w = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float radius = h > w ? w >>> 1 : h >>> 1;
        canvas.drawCircle(w >>> 1, h >>> 1, radius, mPaint);
        canvas.restoreToCount(saveCount);
        if(isShowBorder){
            canvas.translate(getPaddingLeft(), getPaddingTop());
            canvas.drawCircle(w >>> 1, h >>> 1, radius, mPaint2);
            canvas.translate(-1*getPaddingLeft(), -1*getPaddingTop());
        }
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        invalidate();
    }

    private int mBorderColor = 0xffff0000;
    private boolean isShowBorder = false;
    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        mPaint2.setColor(mBorderColor);
    }

    public void setShowBorder(boolean isShow) {
        isShowBorder = isShow;
        invalidate();
    }
}