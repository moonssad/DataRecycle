package com.xiniu.datarecycle.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;



public class ScaleLineView extends View {
    public static final int COLOR_TEXT_DAY_MODE = -1726276835;
    public static final int COLOR_TEXT_NIGHT_MODE = -2134321693;
    public static final int COLOR_TEXT_OUTLINE_DAY_MODE = 1728053247;
    public static final int COLOR_TEXT_OUTLINE_NIGHT_MODE = 1714896208;
    private static final String TAG = ScaleLineView.class.getName();
    private int colorText;
    private int colorTextOutline;
    public float cur_level;
    public int currentScalelineLength;
    private Bitmap logoBitmap;
    public boolean mAlignRight;
    Context mContext;
    private String scaleDesc;
    private int textLineMargin;

    public ScaleLineView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public ScaleLineView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScaleLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.cur_level = 0.0f;
        this.currentScalelineLength = 0;
        this.mAlignRight = true;
        this.logoBitmap = null;
        this.colorText = COLOR_TEXT_DAY_MODE;
        this.colorTextOutline = COLOR_TEXT_OUTLINE_DAY_MODE;
        this.textLineMargin = 0;
        this.mContext = context;
//        this.textLineMargin = getResources().getDimensionPixelSize(R.dimen.auto_dimen2_3);
        this.textLineMargin = 3;
    }

    public void setBitmap(Bitmap bitmap) {
        this.logoBitmap = bitmap;
    }

    public void setScaleLineColor(int i, int i2) {
        this.colorText = i;
        this.colorTextOutline = i2;
        postInvalidate();
    }

    public void setIsNightMode(boolean z) {
        if (z) {
            this.colorText = COLOR_TEXT_NIGHT_MODE;
            this.colorTextOutline = COLOR_TEXT_OUTLINE_NIGHT_MODE;
        } else {
            this.colorText = COLOR_TEXT_DAY_MODE;
            this.colorTextOutline = COLOR_TEXT_OUTLINE_DAY_MODE;
        }
        postInvalidate();
    }

    public void refresh(float f, int i) {
        float f2 = this.cur_level;
        if (f2 != f) {
            float f3 = f % 1.0f;
            if (f3 != 0.0f) {
                try {
                    if (Math.abs(f2 - f) < 0.001f) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (f3 == 0.0f || Math.abs(this.currentScalelineLength - i) >= 4 || !getScaleLineLengthDesc().equals(this.scaleDesc)) {
                this.currentScalelineLength = i;
                this.cur_level = f;
                postInvalidate();
            }
        }
    }

    public void onDraw(Canvas canvas) {
        paintScaleLine(canvas);
        paintLogoBitmap(canvas);
        super.onDraw(canvas);
    }

    private void paintScaleLine(Canvas canvas) {
        int i;
        Bitmap bitmap = this.logoBitmap;
        int height = ((bitmap == null || bitmap.isRecycled()) ? 0 : this.logoBitmap.getHeight()) + getPaddingBottom() + dipToPixel(this.mContext, 2);
        int height2 = getHeight();
        int width = getWidth();
        int i2 = this.currentScalelineLength;
        this.scaleDesc = getScaleLineLengthDesc();
        Paint paint = new Paint();
        paint.setTextSize(20);
        paint.setColor(this.colorTextOutline);
        paint.setAntiAlias(true);
        int measureText = (int) paint.measureText(this.scaleDesc);
        if (i2 < measureText) {
            i2 = measureText + 6;
        }
        if (this.mAlignRight) {
            i = (width - getPaddingRight()) - measureText;
        } else {
            i = getPaddingLeft();
        }
        paint.setColor(this.colorText);
        canvas.drawText(this.scaleDesc, (float) i, (float) (((height2 - height) - 5) - this.textLineMargin), paint);
        paint.setStrokeWidth((float) 2);
        paint.setColor(this.colorTextOutline);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        if (this.mAlignRight) {
            paint.setColor(this.colorText);
            float paddingRight = ((float) width) - ((float) (getPaddingRight() + 2));
            float f = paddingRight - ((float) i2);
            float f2 = ((float) height2) - ((float) height);
            Canvas canvas2 = canvas;
            float f3 = f2 - 2.0f;
            float f4 = f2 + 2.0f;
            Paint paint2 = paint;
            canvas2.drawLine(f, f3, f, f4, paint2);
            float f5 = paddingRight;
            canvas2.drawLine(paddingRight, f3, f5, f4, paint2);
            canvas2.drawLine(f, f2, f5, f2, paint2);
            return;
        }
        int paddingLeft = getPaddingLeft() + 1;
        paint.setColor(this.colorText);
        float min = (float) Math.min(width - 2, paddingLeft + i2);
        float f6 = ((float) height2) - ((float) height);
        Canvas canvas3 = canvas;
        float f7 = f6 - 2.0f;
        float f8 = f6 + 2.0f;
        Paint paint3 = paint;
        canvas3.drawLine(min, f7, min, f8, paint3);
        float f9 = (float) paddingLeft;
        float f10 = f9;
        canvas3.drawLine(f9, f7, f10, f8, paint3);
        canvas3.drawLine(f9 + ((float) i2), f6, f10, f6, paint3);
    }

    private void paintLogoBitmap(Canvas canvas) {
        int i;
        Bitmap bitmap = this.logoBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            Paint paint = new Paint();
            if (this.mAlignRight) {
                i = (getWidth() - this.logoBitmap.getWidth()) - getPaddingRight();
            } else {
                i = getPaddingLeft();
            }
            canvas.drawBitmap(this.logoBitmap, (float) i, (float) ((getHeight() - getPaddingBottom()) - this.logoBitmap.getHeight()), paint);
        }
    }

    private String getScaleLineLengthDesc() {
        return "demo KM";
//        int currentScale = MapController.getInstance().getMapView(1).getOperatorScale().getCurrentScale();
//        if (currentScale % 1000 == 0) {
//            return (currentScale / 1000) + this.mContext.getString(R.string.km);
//        }
//        return currentScale + this.mContext.getString(R.string.meter);
    }

    public static int dipToPixel(Context context, int i) {
        if (context == null) {
            return i;
        }
        try {
            return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }
}
