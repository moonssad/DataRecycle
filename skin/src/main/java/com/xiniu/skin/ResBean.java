package com.xiniu.skin;

/**
 * 创建者：wyz
 * 创建时间：2020-09-21
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class ResBean {
    private int defaultResId;
    private int nightResId;
    private ResType resType;
    public enum ResType {
        UNKOWN,
        COLOR,
        DRAWABLE,
        COLOR_SELECTOR
    }

    public ResBean() {
    }

    public ResBean(int defaultResId, int nightResId) {
        this.defaultResId = defaultResId;
        this.nightResId = nightResId;
    }

    public int getDefaultResId() {
        return defaultResId;
    }

    public void setDefaultResId(int defaultResId) {
        this.defaultResId = defaultResId;
    }

    public int getNightResId() {
        return nightResId;
    }

    public void setNightResId(int nightResId) {
        this.nightResId = nightResId;
    }

    public ResType getResType() {
        return resType;
    }

    public void setResType(ResType resType) {
        this.resType = resType;
    }
}
