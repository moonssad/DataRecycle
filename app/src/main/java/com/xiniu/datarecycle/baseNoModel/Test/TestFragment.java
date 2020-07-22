package com.xiniu.datarecycle.baseNoModel.Test;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiniu.datarecycle.baseNoModel.Base.BaseFragment;
import com.xiniu.datarecycle.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-15
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TestFragment extends BaseFragment<TestPresenter> implements TestView {
    private RecyclerView recyclerView;
    private ImageView imgView;
    private List<String> strings = new ArrayList<String>();
    private TextRecycleAdapter adapter;

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_photos);
        imgView = (ImageView) view.findViewById(R.id.img);
        adapter = new TextRecycleAdapter(strings);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(adapter);
        showLog("initVIew");
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strings.add("你好呀");
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {
        p.setString(strings);

    }

    @Override
    public int getLayoutId() {
        return R.layout.test_b;
    }

    @Override
    public TestPresenter getPresenter() {
        return new TestPresenter(this);
    }


    @Override
    public void showtext(String text) {
    }

    @Override
    public void notifyData() {
        adapter.notifyDataSetChanged();

    }

    @Override
    public void ShowToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

}
