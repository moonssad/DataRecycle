package com.xiniu.datarecycle.baseNoModel.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：wyz
 * 创建时间：2020-07-17
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class testModelIml implements testModel {
    @Override
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("wangyihe");
        list.add("lixiang");
        list.add("xihuan");
        list.add("lalalala");
        list.add("111");
        list.add("2344");
        list.add("4567");
        list.add("123455432");
        list.add("4321098");
        return list;
    }
}
