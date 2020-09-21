package com.xiniu.skin.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SkinIndexBarView extends SkinConstraintLayout {
    private TextView indexTv;
    private int magnifierBackgroundId;
    private int magnifierMarginRightId;
    private int magnifierMarginTopId;
    private int magnifierTextColorId;
    private int magnifierTextSizeId;
    private TextView magnifierTv;
    private int magnifierWidthId;
    private int textColorId;
    private int textSizeId;

    public SkinIndexBarView(Context context) {
        super(context);
    }

    public SkinIndexBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public SkinIndexBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.autoui);
        if (obtainStyledAttributes != null) {
            this.magnifierBackgroundId = obtainStyledAttributes.getResourceId(R.styleable.autoui_magnifierBackground, -1);
            this.textSizeId = obtainStyledAttributes.getResourceId(R.styleable.autoui_textSize, -1);
            this.textColorId = obtainStyledAttributes.getResourceId(R.styleable.autoui_textColor, -1);
            this.magnifierTextSizeId = obtainStyledAttributes.getResourceId(R.styleable.autoui_magnifierTextSize, -1);
            this.magnifierTextColorId = obtainStyledAttributes.getResourceId(R.styleable.autoui_magnifierTextColor, -1);
            this.magnifierWidthId = obtainStyledAttributes.getResourceId(R.styleable.autoui_magnifierWidth, -1);
            this.magnifierMarginTopId = obtainStyledAttributes.getResourceId(R.styleable.autoui_magnifierMarginTop, -1);
            this.magnifierMarginRightId = obtainStyledAttributes.getResourceId(R.styleable.autoui_magnifierMarginRight, -1);
            obtainStyledAttributes.recycle();
        }
        Context context = getContext();
        Resources resources = context.getResources();
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_index_bar, this);
        this.indexTv = (TextView) inflate.findViewById(R.id.index_text);
        this.magnifierTv = (TextView) inflate.findViewById(R.id.magnifier_text);
        this.indexTv.setTextColor(resources.getColor(this.textColorId));
        this.indexTv.setTextSize(0, (float) resources.getDimensionPixelSize(this.textSizeId));
        this.magnifierTv.setTextColor(resources.getColorStateList(this.magnifierTextColorId));
        this.magnifierTv.setTextSize(0, (float) resources.getDimensionPixelSize(this.magnifierTextSizeId));
        this.magnifierTv.setBackgroundResource(this.magnifierBackgroundId);
        ViewGroup.LayoutParams layoutParams = this.magnifierTv.getLayoutParams();
        layoutParams.height = resources.getDimensionPixelSize(this.magnifierWidthId);
        layoutParams.width = resources.getDimensionPixelSize(this.magnifierWidthId);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.rightMargin = resources.getDimensionPixelSize(this.magnifierMarginRightId);
        marginLayoutParams.topMargin = resources.getDimensionPixelSize(this.magnifierMarginTopId);
        this.magnifierTv.setLayoutParams(layoutParams);
    }
}
