package com.xiniu.datarecycle.utils.Views;

/**
 * 创建者：wyz
 * 创建时间：2020-07-31
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class CircleDelta {
    private float delta = (float)Math.PI/90;
    private float startDelta ;

    public CircleDelta() {
        startDelta = 0;
    }


    public float updata(){
        startDelta = startDelta+ delta;
        if (startDelta >= Math.PI) {
            startDelta = 0;
        }
        return startDelta;
    }

}
