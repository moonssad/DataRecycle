package com.xiniu.datarecycle.coordinatorLayoutTest;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.xiniu.datarecycle.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 创建者：wyz
 * 创建时间：2020-07-21
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class nestScrollTest extends AppCompatActivity {
    ImageView imageView;
    ImageView img1, img2;
    TransitionDrawable transitionDrawable;
    Drawable[] drawables;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setWindowAnimations(R.style.MyViewStyle);
        setContentView(R.layout.test_22);
        imageView = (ImageView) findViewById(R.id.iv);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
            case R.id.img2:
                if (v instanceof ImageView) {
                    setImageDrawable((ImageView) v);
                }
                break;
            default:
                break;
        }
    }


    private void setImageDrawable(ImageView v) {
        drawables = new Drawable[2];
        drawables[0] = imageView.getDrawable();
        drawables[1] = v.getDrawable();
        if (drawables[0] != null && drawables[1] != null) {
            transitionDrawable = new TransitionDrawable(drawables);
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(1000);
        } else {
            drawables[0] = getResources().getDrawable(R.drawable.backgroud);
            drawables[1] = getResources().getDrawable(R.drawable.background2);
            transitionDrawable = new TransitionDrawable(drawables);
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(1000);
        }
    }
}
