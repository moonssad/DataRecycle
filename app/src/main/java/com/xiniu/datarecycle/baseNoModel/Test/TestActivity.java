package com.xiniu.datarecycle.baseNoModel.Test;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.FrameLayout;

import com.xiniu.datarecycle.baseNoModel.Base.IPresenter;
import com.xiniu.datarecycle.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 创建者：wyz
 * 创建时间：2020-07-15
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TestActivity extends AppCompatActivity {
    TestFragment testFragment;
    FrameLayout fragment;
    private static String TAG = "TestActivity";
    IPresenter iPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setWindowAnimations(R.style.MyViewStyle);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test_activity);
        fragment = (FrameLayout)findViewById(R.id.fm);
        testFragment = new TestFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fms = fm.beginTransaction();
        fms.add(R.id.fm, testFragment);
        fms.commitAllowingStateLoss();
        iPresenter = new IPresenter() {
            @Override
            public void onCreate() {
                Log.e(TAG, "onCreate: ");
            }

            @Override
            public void onStart() {
                Log.e(TAG, "onStart: ");
            }

            @Override
            public void onPause() {
                Log.e(TAG, "onPause: ");
            }

            @Override
            public void onStop() {
                Log.e(TAG, "onStop: ");
            }

            @Override
            public void onResume() {
                Log.e(TAG, "onResume: ");
            }

            @Override
            public void onDestroy() {
                Log.e(TAG, "onDestroy: ");
            }
        };
        getLifecycle().addObserver(iPresenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(iPresenter);
    }

}
