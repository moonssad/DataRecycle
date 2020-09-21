package com.xiniu.skin;

public class SkinItems {
    private ResBean background;
    private ResBean drawableBottom;
    private ResBean drawableLeft;
    private ResBean drawableRight;
    private ResBean drawableTop;
    private ResBean progressDrawable;
    private ResBean src;
    private ResBean textColor;
    private ResBean textColorHint;
    private ResBean thumb;

    public ResBean getBackground() {
        return this.background;
    }

    public void setBackground(ResBean resBean) {
        this.background = resBean;
    }

    public ResBean getTextColor() {
        return this.textColor;
    }

    public void setTextColor(ResBean resBean) {
        this.textColor = resBean;
    }

    public ResBean getSrc() {
        return this.src;
    }

    public void setSrc(ResBean resBean) {
        this.src = resBean;
    }

    public ResBean getDrawableBottom() {
        return this.drawableBottom;
    }

    public void setDrawableBottom(ResBean resBean) {
        this.drawableBottom = resBean;
    }

    public ResBean getDrawableLeft() {
        return this.drawableLeft;
    }

    public void setDrawableLeft(ResBean resBean) {
        this.drawableLeft = resBean;
    }

    public ResBean getDrawableRight() {
        return this.drawableRight;
    }

    public void setDrawableRight(ResBean resBean) {
        this.drawableRight = resBean;
    }

    public ResBean getDrawableTop() {
        return this.drawableTop;
    }

    public void setDrawableTop(ResBean resBean) {
        this.drawableTop = resBean;
    }

    public ResBean getTextColorHint() {
        return this.textColorHint;
    }

    public void setTextColorHint(ResBean resBean) {
        this.textColorHint = resBean;
    }

    public ResBean getProgressDrawable() {
        return this.progressDrawable;
    }

    public void setProgressDrawable(ResBean resBean) {
        this.progressDrawable = resBean;
    }

    public ResBean getThumb() {
        return this.thumb;
    }

    public void setThumb(ResBean resBean) {
        this.thumb = resBean;
    }

    public boolean isEmpty() {
        return this.background == null && this.textColor == null && this.src == null && this.drawableLeft == null && this.drawableBottom == null && this.drawableRight == null && this.drawableTop == null && this.textColorHint == null && this.progressDrawable == null && this.thumb == null;
    }
}
