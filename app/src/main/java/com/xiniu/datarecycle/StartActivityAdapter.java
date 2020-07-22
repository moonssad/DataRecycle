package com.xiniu.datarecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.xiniu.datarecycle.baseNoModel.Base.BaseRecycleAdapter;
import com.xiniu.datarecycle.utils.MyDiffCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-22
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class StartActivityAdapter extends BaseRecycleAdapter<String, StartActivityAdapter.ViewHolder> {

    private List<String> mNewList;

    OnItemClickListener listener;

    public StartActivityAdapter(List<String> list) {
        super(list);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_start_item;
    }

    @Override
    public ViewHolder viewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void BindView(ViewHolder holder, int position) {
        holder.textView.setText(lists.get(position));
        holder.context = lists.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()) {
            onBindViewHolder(holder, position);
            return;
        }
        //如果不为空，说明有部分数据发生了更改，那么只要根据数据去更新变更的UI即可
        Bundle bundle = (Bundle) payloads.get(0);
        String content = bundle.getString("content");
        holder.textView.setText(content);
        holder.context = content;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        String context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.activity_name);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(context);}
//                    mNewList = new ArrayList<>(lists);
//                    mNewList.remove(context);
//                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(lists, mNewList), true);
//                    diffResult.dispatchUpdatesTo(StartActivityAdapter.this);
//                    lists = mNewList;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(String text);
    }
}
