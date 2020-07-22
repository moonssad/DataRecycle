package com.xiniu.datarecycle.utils;

/**
 * 创建者：wyz
 * 创建时间：2020-07-06
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TittleBean  {

    private  int drawable;
    private String name;

    public TittleBean() {
    }

    public TittleBean(int drawable, String name) {
        this.drawable = drawable;
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
