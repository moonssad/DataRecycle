package com.xiniu.datarecycle.coordinatorLayoutTest;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.xiniu.datarecycle.baseNoModel.Test.TextRecycleAdapter;
import com.xiniu.datarecycle.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-21
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class CoordinatorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<String>();
    private TextRecycleAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setWindowAnimations(R.style.MyViewStyle);
        setContentView(R.layout.test_coordinator);
        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        list.add("wangyihe");
        list.add("lixiang");
        list.add("xihuan");
        list.add("lalalala");
        list.add("111");
        list.add("2344");
        list.add("4567");
        list.add("123455432");
        list.add("4321098");
        adapter = new TextRecycleAdapter(list);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(adapter);
    }


}
