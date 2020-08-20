package com.xiniu.datarecycle.baseNoModel.Test;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.xiniu.datarecycle.R;

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
    private String mTitles[] = {"燃油车", "纯电动", "插电式混动", "油气两用"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_test);
        tab1 = (TabLayout) findViewById(R.id.tab1);
        tab2 = (TabLayout) findViewById(R.id.tab2);
        initTab2();
    }

    private void initTab2() {
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab = tab2.newTab();
            tab.setTag(i);
            tab.setText(mTitles[i]);
            LinearLayout layout = tab.view;
            layout.setBackgroundColor(ContextCompat.getColor(TabLayoutActivity.this, R.color.transparent));
            tab2.addTab(tab);
        }
        tab2.getTabAt(2).select();
        tab2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

}
