package com.xiniu.datarecycle.mvvm;

/**
 * 创建者：wyz
 * 创建时间：2020/4/17
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    public DB binding;
    public VM viewModel;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false);
        initDataBindings();
        return binding.getRoot();
    }

    private void initDataBindings() {
        //反射绑定
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            Class clazz = (Class) types[1];
            viewModel = (VM) ViewModelProviders.of(this).get(clazz);
        }
        binding.setVariable(viewModelId(), viewModel);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    protected abstract int layout();

    protected abstract int viewModelId();

    protected abstract void initView();

    protected abstract void initData();

}
