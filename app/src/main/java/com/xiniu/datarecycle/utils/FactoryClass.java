package com.xiniu.datarecycle.utils;

import com.xiniu.datarecycle.MyViewPager.MyViewPagerActivity;
import com.xiniu.datarecycle.RightScrollAcvitity.LeftActivity;
import com.xiniu.datarecycle.RightScrollAcvitity.RightActivity;
import com.xiniu.datarecycle.baseNoModel.Test.ScrollTestActivity;
import com.xiniu.datarecycle.baseNoModel.Test.TestActivity;
import com.xiniu.datarecycle.coordinatorLayoutTest.CoordinatorActivity;
import com.xiniu.datarecycle.coordinatorLayoutTest.nestScrollTest;
import com.xiniu.datarecycle.mvvm.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建者：wyz
 * 创建时间：2020-07-22
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class FactoryClass {
    public static HashMap<String, Class> caches;
    static {
        caches = new HashMap<String, Class>();
        caches.put("mvvm的recycleView", MainActivity.class);
        caches.put("滑动原理demo", ScrollTestActivity.class);
        caches.put("Coordinator 子view协调demo1", CoordinatorActivity.class);
        caches.put("Coordinator 子view协调demo2", nestScrollTest.class);
        caches.put("mvp 模型的activity", TestActivity.class);
        caches.put("自定义viewpager", MyViewPagerActivity.class);
        caches.put("右侧滑动Activity", RightActivity.class);
        caches.put("zuo侧滑动Activity", LeftActivity.class);
    }

    public static Class getClass(String key) {
        for (Map.Entry<String, Class> cache : caches.entrySet()) {
            if (cache.getKey().equals(key)) {
                return cache.getValue();
            }
        }
        return null;
    }


    public static List<String> getStringList() {
        List<String> list = new ArrayList<String>();
        for (Map.Entry<String, Class> cache : caches.entrySet()) {
            list.add(cache.getKey());
        }
        return list;
    }
}
