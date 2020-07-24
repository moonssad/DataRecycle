package com.xiniu.datarecycle.mvvm;

import android.os.Bundle;
import android.view.Window;


import com.xiniu.datarecycle.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

//
///**
// * 创建者：wyz
// * 创建时间：2020/4/16
// * 功能描述：
// * 更新者：
// * 更新时间：
// * 更新描述：
// */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected DB dataBinding;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setWindowAnimations(R.style.MyViewStyle);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = ViewModelProviders.of(this).get(getViewModelClazz());
        dataBinding.setVariable(getViewModelId(), viewModel);
        initData();
        initView();
    }

    protected abstract Class<VM> getViewModelClazz();

    protected abstract int getLayoutId();

    protected abstract int getViewModelId();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
