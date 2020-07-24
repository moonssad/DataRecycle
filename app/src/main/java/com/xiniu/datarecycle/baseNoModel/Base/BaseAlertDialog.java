package com.xiniu.datarecycle.baseNoModel.Base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.xiniu.datarecycle.R;

import androidx.annotation.NonNull;

/**
 * 创建者：wyz
 * 创建时间：2020-07-24
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public abstract class BaseAlertDialog extends AlertDialog {

    public BaseAlertDialog(@NonNull Activity context) {
        this(context, R.style.baseDialogStyle);//通过style来设置window的属性
    }

    public BaseAlertDialog(Activity context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }


    public abstract int getLayoutId();

    //设置点击时间
    public abstract void initView();


}
