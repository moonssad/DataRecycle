package com.xiniu.datarecycle.mvvm;

import androidx.lifecycle.ViewModel;



/**
 * 创建者：wyz
 * 创建时间：2020/4/16
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public abstract class BaseViewModel extends ViewModel {


    public BaseViewModel() {
        super();
        onCreate();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        onDestroy();

    }

    //添加在异步操作。主进程回掉的方法。
    public abstract void onCreate();

    public abstract void onDestroy();



}
