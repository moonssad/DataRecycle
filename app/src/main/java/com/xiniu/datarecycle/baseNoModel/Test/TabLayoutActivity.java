package com.xiniu.datarecycle.baseNoModel.Test;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.xiniu.datarecycle.R;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * 创建者：wyz
 * 创建时间：2020-08-19
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TabLayoutActivity extends AppCompatActivity {
    TabLayout tab1, tab2;
    Button bt, bt2;
    EditText ex;
    TextView tv;
    private String mTitles[] = {"燃油车", "纯电动", "插电式混动", "油气两用"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_test);
        tab1 = (TabLayout) findViewById(R.id.tab1);
        tab2 = (TabLayout) findViewById(R.id.tab2);
        bt = (Button) findViewById(R.id.bt);
        bt2= (Button) findViewById(R.id.bt2);
        tv=(TextView) findViewById(R.id.tv);
        tv.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.drawable.tower), null, null);
        initTab2();
        inttTab2();
        bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inttTab2();
            }
        });
        bt2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                initTab();
            }
        });
        ex = (EditText) findViewById(R.id.ed);
        ex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               int num = s.length();
                if (0<num&& num<6){
                    Log.e( "onTextChanged: ","show");
                }else{
                    if (num>0){
                        String reg ="[0-9]";
                        boolean isNum = Pattern.matches(reg, s.charAt(0)+"");
                        Log.e( "onTextChanged: isNum",isNum+"");
                    }
                }
                Log.e( "afterTextChanged: ",s.toString());
            }
        });
    }

    private void initTab2() {
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab = tab2.newTab();
            tab.setTag(i);
            tab.setText(mTitles[i]);
            LinearLayout layout = tab.view;
            layout.setBackgroundColor(ContextCompat.getColor(TabLayoutActivity.this, R.color.transparent));
            tab2.addTab(tab, false);
        }


        tab2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("onTabSelected: ", (int) tab.getTag() + "");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

//    public void reflex(final TabLayout tabLayout){
//        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //拿到tabLayout的mTabStrip属性
//                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
//
//                    int dp10 = dip2px(tabLayout.getContext(), 10);
//
//                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
//                        View tabView = mTabStrip.getChildAt(i);
//
//                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
//                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
//                        mTextViewField.setAccessible(true);
//
//                        TextView mTextView = (TextView) mTextViewField.get(tabView);
//
//                        tabView.setPadding(0, 0, 0, 0);
//
//                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
//                        int width = 0;
//                        width = mTextView.getWidth();
//                        if (width == 0) {
//                            mTextView.measure(0, 0);
//                            width = mTextView.getMeasuredWidth();
//                        }
//
//                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
//                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
//                        params.width = width ;
//                        params.leftMargin = dp10;
//                        params.rightMargin = dp10;
//                        tabView.setLayoutParams(params);
//
//                        tabView.invalidate();
//                    }
//
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }


    public void initTab() {
        LinearLayout tabStrip = ((LinearLayout) tab2.getChildAt(0));
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
//   tabIndicator="@drawable/bg_blue_radius"
        tab2.setSelectedTabIndicatorColor(getResources().getColor(R.color.transparent));
        tab2.setTabTextColors(R.color.black,R.color.black);

    }

    public void inttTab2() {
        tab2.setTabTextColors(null);
        tab2.setSelectedTabIndicatorColor(getResources().getColor(R.color.bg_tab));
        LinearLayout tabStrip = ((LinearLayout) tab2.getChildAt(0));
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });

        }
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
}
