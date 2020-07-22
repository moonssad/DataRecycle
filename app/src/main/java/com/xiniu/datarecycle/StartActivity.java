package com.xiniu.datarecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.xiniu.datarecycle.utils.FactoryClass;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-22
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class StartActivity extends AppCompatActivity {
   ImageView tittleImage;
   RecyclerView recyclerView;
    StartActivityAdapter activityAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        tittleImage = (ImageView) findViewById(R.id.iv_tittle);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        List<String>  titles = FactoryClass.getStringList();
        activityAdapter = new StartActivityAdapter(titles);
        activityAdapter.setOnClickListener(new StartActivityAdapter.OnItemClickListener() {
            @Override
            public void onClick(String text) {
                startActivity(new Intent(StartActivity.this,FactoryClass.getClass(text)));
            }
        });
        RecyclerView.LayoutManager LayoutManager =new LinearLayoutManager(CarHandApplication.getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(activityAdapter);
    }
}
