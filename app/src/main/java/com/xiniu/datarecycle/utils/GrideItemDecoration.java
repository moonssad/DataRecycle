package com.xiniu.datarecycle.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-10
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class GrideItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacingH;
    private int spacingV;
    private boolean includeEdge;

    public GrideItemDecoration(int spanCount, int spacingH, int spacingV, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacingH = spacingH;
        this.spacingV = spacingV;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;
        if (includeEdge) {
            outRect.left = spacingH - column * spacingH / spanCount;
            outRect.right = (column + 1) * spacingH / spanCount;
            if (position < spanCount) {
                outRect.top = spacingV;
            }
            outRect.bottom = spacingV;
        } else {
            outRect.left = column * spacingH / spanCount;
            outRect.right = spacingH - (column + 1) * spacingH / spanCount;
        }
        if (position >= spanCount) {
            outRect.top = spacingV;
        }
    }

}
