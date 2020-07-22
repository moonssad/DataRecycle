package com.xiniu.datarecycle.baseNoModel.Test;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xiniu.datarecycle.baseNoModel.Base.BaseRecycleAdapter;
import com.xiniu.datarecycle.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-17
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class TextRecycleAdapter extends BaseRecycleAdapter<String, TextRecycleAdapter.Adapter> {


    public TextRecycleAdapter(List<String> list) {
        super(list);
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_a;
    }

    @Override
    public Adapter viewHolder(View view) {
        return new Adapter(view);
    }

    @Override
    public void BindView(Adapter holder, int position) {
        holder.textView.setText(getItem(position));
        holder.position = position;
    }

    public class Adapter extends RecyclerView.ViewHolder {
        TextView textView;
        int position;

        public Adapter(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv1);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e( "onClick: ",lists.get(position));
                }
            });
        }
    }
}
