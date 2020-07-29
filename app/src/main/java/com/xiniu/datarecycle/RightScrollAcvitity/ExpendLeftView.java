package com.xiniu.datarecycle.RightScrollAcvitity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.xiniu.datarecycle.R;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * 创建者：wyz
 * 创建时间：2020-07-28
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class ExpendLeftView extends ConstraintLayout {
    public ExpendLeftView(Context context) {
        this(context, null);
    }

    public ExpendLeftView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpendLeftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
      LayoutInflater.from(context).inflate(R.layout.idenfind, this);
    }

}
