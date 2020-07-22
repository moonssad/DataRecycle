package com.xiniu.datarecycle.baseNoModel.Test;

import com.xiniu.datarecycle.baseNoModel.Base.BasePresenter;

import java.util.List;

/**
 * 创建者：wyz
 * 创建时间：2020-07-15
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TestPresenter extends BasePresenter<TestView,testModel> {

    public TestPresenter(TestView view) {
        super(view);
    }

    @Override
    public testModelIml getModel() {
        return new testModelIml();
    }


    public void setText() {
        double num = Math.random();
        mView.showtext(num + " ");
    }

    @Override
    public void onCreate() {
        showLog("onCreate");
    }

    @Override
    public void onStart() {
        showLog("onstart");
    }

    @Override
    public void onPause() {
        showLog("onPause");

    }

    @Override
    public void onStop() {
        showLog("onStop");
    }

    @Override
    public void onResume() {
        showLog("onresume");
    }

    @Override
    public void onDestroy() {
        showLog("onDestory");

    }

    public void setString(List<String> string){
        string.addAll(mModel.getList());
        mView.notifyData();

    }
}
