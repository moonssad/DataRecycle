package com.xiniu.datarecycle;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.xiniu.datarecycle.utils.FactoryClass;
import com.xiniu.datarecycle.utils.GrideItemDecoration;

import java.util.List;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
                restartApp();
                startActivity(new Intent(StartActivity.this,FactoryClass.getClass(text)));
            }
        });
//        RecyclerView.LayoutManager grideLayoutManager = new GridLayoutManager(CarHandApplication.getContext(),3,RecyclerView.VERTICAL,false);
        RecyclerView.LayoutManager LayoutManager =new LinearLayoutManager(CarHandApplication.getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(LayoutManager);
//        recyclerView.addItemDecoration(new GrideItemDecoration(3,50,50 ,false));
        recyclerView.setAdapter(activityAdapter);
    }

    private void restartApp(){
        Intent i = getPackageManager().getLaunchIntentForPackage(getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
