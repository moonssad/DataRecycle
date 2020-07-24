package com.xiniu.datarecycle.MyViewPager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.xiniu.datarecycle.R;
import com.xiniu.datarecycle.baseNoModel.Base.BaseDialog;

import androidx.annotation.NonNull;

/**
 * 创建者：wyz
 * 创建时间：2020-07-24
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TestDialog extends BaseDialog {

    public TestDialog(@NonNull Activity context) {
        super(context);
    }
    @Override
    public int getLayoutId() {
        return R.layout.test_a;
    }
    @Override
    public void initView() {
        Window window = getWindow();
        if (window != null) {
            View decor = getWindow().getDecorView();
            TextView tv = (TextView) decor.findViewById(R.id.tv1);
            tv.setText("你好呀，哈哈哈");
        }else{
            super.dismiss();
        }
    }
}
