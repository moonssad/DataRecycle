package com.xiniu.datarecycle.RightScrollAcvitity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.xiniu.datarecycle.R;

import androidx.annotation.Nullable;

/**
 * 创建者：wyz
 * 创建时间：2020-07-27
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class RightActivity extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right);
        imageView = (ImageView) findViewById(R.id.map_icon);

    }


}
