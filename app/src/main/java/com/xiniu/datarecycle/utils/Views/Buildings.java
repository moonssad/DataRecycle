package com.xiniu.datarecycle.utils.Views;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * 创建者：wyz
 * 创建时间：2020-08-04
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class Buildings {
    public RectF rect;
    public Bitmap bitmap;
    public int maxPosition;
    public int buildingsWidth;

    public Buildings(RectF position, Bitmap bitmap,int maxposition,int buildingsWidth) {
        this.rect = position;
        this.bitmap = bitmap;
        this.maxPosition = maxposition;
        this.buildingsWidth = buildingsWidth;
    }

    public RectF getPosition() {
        return rect;
    }

    public void setPosition(RectF rectF) {
        this.rect = rectF;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void updata(int step){
        rect.left= rect.left-step;
        rect.right =rect.left+buildingsWidth;
        if (rect.left<0){
            rect.left = maxPosition;
            rect.right =rect.left+buildingsWidth;
        }
    }
}
