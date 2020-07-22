package com.xiniu.datarecycle.mvvm;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.xiniu.datarecycle.utils.TestRelative;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建者：wyz
 * 创建时间：2020-07-06
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class BindingUtils {

    @BindingAdapter(value = {"itemHelper"}, requireAll = false)
    public static void ItemBinding(RecyclerView RecyclerView, ItemTouchHelper helper) {
        helper.attachToRecyclerView(RecyclerView);
        //添加这个每次只能一次滑动一个对象
//        PagerSnapHelper h = new PagerSnapHelper();
//        h.attachToRecyclerView(RecyclerView);
        Log.e("ItemBinding: ", helper.toString() + "");
    }

    @BindingAdapter(value = {"dragListener"}, requireAll = false)
    public static void ItemBinding(View view, View.OnDragListener dragListener) {
        view.setOnDragListener(dragListener);
    }

    @BindingAdapter(value = {"ItemDecoration"}, requireAll = false)
    public static void LayoutManager(RecyclerView view, RecyclerView.ItemDecoration decor) {
        view.addItemDecoration(decor);
    }


    @BindingAdapter(value = {"ActivityViewModel"}, requireAll = false)
    public static void onTypeChanged(TestRelative view, final BindingCommand command) {

        
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (command != null) {
                    command.execute();
                }

//                if (releaseCommand!=null){
//                    releaseCommand.execute();
//                }
                return true;
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation animation = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setRepeatCount(1);
                animation.setDuration(100);
                animation.setRepeatMode(Animation.REVERSE);
                v.startAnimation(animation);
            }
        });
    }

    @BindingAdapter(value = {"onchange"}, requireAll = false)
    public static void onTypeChanged(TestRelative view, boolean onchange) {
        if (onchange) {
            Animation animation = new RotateAnimation(-4, 4, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setRepeatCount(6);
            animation.setDuration(60);
            animation.setRepeatMode(Animation.REVERSE);
            view.startAnimation(animation);
        }
    }


    @BindingAdapter(value = {"imageUrl", "name", "chooseName"}, requireAll = false)
    public static void setImageResource(ImageView view, int Drawable, String name, String chooseName) {
        view.setImageResource(Drawable);
        Log.e("setImageResource: ", name + "" + chooseName);
        if (name != null && chooseName != null) {
            if (name.equals(chooseName)) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
    }

}
