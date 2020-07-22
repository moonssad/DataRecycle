package com.xiniu.datarecycle.baseNoModel.Base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 创建者：wyz
 * 创建时间：2020-07-15
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    public P p;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), container, false);
        p = getPresenter();
        initView(view);
        getLifecycle().addObserver(p);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(p);
    }

    public void showLog(String logs) {
        Log.e(this.getClass().getSimpleName(), logs);
    }


    protected abstract void initView(View view);

    protected abstract void initData();

    public abstract int getLayoutId();

    public abstract P getPresenter();


}
