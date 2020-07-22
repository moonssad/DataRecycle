package com.xiniu.datarecycle.mvvm;


import android.util.Log;

import com.xiniu.datarecycle.R;
import com.xiniu.datarecycle.databinding.ActivityMainBinding;
import com.xiniu.datarecycle.utils.testcomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MainActivity extends BaseActivity<ActivityMainBinding, ActivityViewModel> {


    @Override
    protected Class<ActivityViewModel> getViewModelClazz() {
        return ActivityViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getViewModelId() {
        return com.xiniu.datarecycle.BR.mainView;
    }

    @Override
    protected void initData() {
        viewModel.initData();
    }

    @Override
    protected void initView() {
//        dataBinding.imageButton.setOnLongClickListener(new ShakeImageView.onLongClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public boolean onLongClick(View view) {
//                dataBinding.imageButton.setSelected(false);
//                viewModel.onImageLongClick(view);
//                return false;
//            }
//        });
//
//        dataBinding.imageButton.setOnClickListener(new ShakeImageView.onClickListener() {
//            @Override
//            public void onClick(View view) {
//                dataBinding.imageButton.setSelected(false);
//                Toast.makeText(MainActivity.this, "点击了一下", Toast.LENGTH_SHORT).show();
//                text mtext = new text();
//                mtext.doText();
//            }
//        });
    }

}
