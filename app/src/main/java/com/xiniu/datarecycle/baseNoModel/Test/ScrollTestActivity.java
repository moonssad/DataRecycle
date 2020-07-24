package com.xiniu.datarecycle.baseNoModel.Test;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.xiniu.datarecycle.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 创建者：wyz
 * 创建时间：2020-07-20
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class ScrollTestActivity extends AppCompatActivity {
   MyImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setWindowAnimations(R.style.MyViewStyle);
        setContentView(R.layout.test_image);
        imageView = (MyImageView) findViewById(R.id.iv_main);
    }

  //  规则：左加右减，上加下减

    public void scrollLeft(View view) {
     imageView.scrollBy(20,0);
    }

    public void scrollUp(View view) {
        imageView.scrollBy(0,20);
    }

    public void scrollRight(View view) {
//        imageView.scrollBy(-20,0);
        imageView.scrollTo( imageView.getScrollX() - 20, imageView .getScrollY()); //参数 2 可以写为 0
    }

    public void scrollDown(View view) {
        imageView.scrollTo(imageView.getScrollX(),imageView.getScrollY()-20);
    }

    public void reset1(View view) {
        imageView.scrollTo(0,0);

    }

    public void reset2(View view) {
        imageView.smoothReset();
    }
}
