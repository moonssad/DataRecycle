package com.xiniu.datarecycle.MyViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiniu.datarecycle.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 创建者：wyz
 * 创建时间：2020-07-23
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class MyViewPagerActivity extends AppCompatActivity {
    private MyViewPager view;
    private LinearLayout LinearLayout;
    private View itemView;
    private LinearLayout.LayoutParams params;
    private int dotDistance = 30;
    private List<Integer> mData = new ArrayList<>();
    private View view_img;
    private boolean isAuto;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    view.autoChange();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            handler.obtainMessage(0, 0).sendToTarget();
                        }
                    }, 5000);
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setWindowAnimations(R.style.MyViewStyle);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.viewpager_activity);
        view = (MyViewPager) findViewById(R.id.myviewpager);
        LinearLayout = (LinearLayout) findViewById(R.id.ll_point_list);
        itemView = (View) findViewById(R.id.view_dot);
        initCirlce();
        view.setOnPageScrollListener(new MyViewPager.OnPageScrollListener() {
            @Override
            public void onPageScrolled(float offsetPercent, int position) {
                float leftMargin = offsetPercent * dotDistance;
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) itemView.getLayoutParams();
                params.leftMargin = (int) leftMargin; //滑动后更新距离
                itemView.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                float leftMargin = position * dotDistance;
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) itemView.getLayoutParams();
                params.leftMargin = (int) leftMargin; //滑动后更新距离
                itemView.setLayoutParams(params);
            }
        });
        view_img = (View) findViewById(R.id.view_img);
        view_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.obtainMessage(0, 0).sendToTarget();
                TestDialog testDialog = new TestDialog(MyViewPagerActivity.this);
                testDialog.showAutoDismiss(3000);
            }

/**
 * 添加新的view，给viewgroup
 */
//                ImageView iv = new ImageView(MyViewPagerActivity.this);
//                iv.setBackgroundResource(R.drawable.background2);
//                view.addView(iv);
//                View point = new View(MyViewPagerActivity.this);
//                point.setBackgroundResource(R.drawable.bg_point_selector);
//                params = new LinearLayout.LayoutParams(20, 20);
//                params.leftMargin = 10;
//                point.setEnabled(false);
//                point.setLayoutParams(params);
//                LinearLayout.addView(point);

        });

    }

    private void initCirlce() {
        for (int i = 0; i < 4; i++) {
            mData.add(i);
        }
        for (int i = 0; i < mData.size(); i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.bg_point_selector);
            params = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                params.leftMargin = 10;
            }
            point.setEnabled(false);
            point.setLayoutParams(params);
            LinearLayout.addView(point);
        }
    }
}
