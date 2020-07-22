package com.xiniu.datarecycle.mvvm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.xiniu.datarecycle.BR;
import com.xiniu.datarecycle.CarHandApplication;
import com.xiniu.datarecycle.utils.GrideItemDecoration;
import com.xiniu.datarecycle.R;
import com.xiniu.datarecycle.utils.TittleAdapter;
import com.xiniu.datarecycle.utils.TittleBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.ItemTouchHelper;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

import static android.view.View.DRAG_FLAG_GLOBAL;


/**
 * 创建者：wyz
 * 创建时间：2020-07-06
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class ActivityViewModel extends BaseViewModel implements CustomItemTouchCallback.onSwipedListener, View.OnDragListener {
    public ObservableList<TittleBean> tittles;
    public ItemBinding<TittleBean> itemBinding;
    public ObservableField<String> chooseName;
    public ObservableField<Boolean> onchange;
    private List<TittleBean> listtittles;
    public TittleAdapter adapter;
    public ItemTouchHelper helper;
    //grideView的recycleView
    public ItemBinding<TittleBean> grideItemBing;
    public ObservableList<TittleBean> grideTittles;

    public GrideItemDecoration grideItemDectoration;


    boolean isEnter = false;

    @Override
    public void onCreate() {
        tittles = new ObservableArrayList<>();
        itemBinding = ItemBinding.of(com.xiniu.datarecycle.BR.titleItem, R.layout.tittle_item);
        itemBinding.bindExtra(BR.mainView, this);

        //grideRecycle
        grideTittles = new ObservableArrayList<>();
        grideItemBing = ItemBinding.of(BR.grideTittleItem, R.layout.tittle_gride_item);
        grideItemBing.bindExtra(BR.mainView, this);

        listtittles = new ArrayList<>();
        helper = new ItemTouchHelper(new CustomItemTouchCallback(this));
        adapter = new TittleAdapter();
        chooseName = new ObservableField<>();
        onchange = new ObservableField<>();
        grideItemDectoration = new GrideItemDecoration(3, 100, 100, true);
    }

    @Override
    public void onDestroy() {
        for (TittleBean tt : tittles) {
            Log.e("itemMoved: ", tt.getName());
        }
        Log.e("onDestroy: ", "destory");
    }

    @RequiresApi(api = 26)
    public void initData() {
        String[] names = CarHandApplication.getContext().getResources().getStringArray(R.array.tittle_name);
        TypedArray typeArray = CarHandApplication.getContext().getResources().obtainTypedArray(R.array.item_drawable);
        for (int i = 0; i < names.length; i++) {
            listtittles.add(new TittleBean(typeArray.getResourceId(i, 0), names[i]));
        }

        for (int i = 0; i < typeArray.length(); i++) {
            Log.e("initData: ", typeArray.getResourceId(i, 0) + "");
        }
        typeArray.recycle();
        tittles.addAll(listtittles);
        grideTittles.addAll(listtittles);
    }

    TittleBean bean;
    int num;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onImageLongClick(View v) {
        num += 1;
        bean = new TittleBean(R.drawable.ic_launcher_background, "hello world" + num);
        v.startDragAndDrop(null, new View.DragShadowBuilder(v), bean, DRAG_FLAG_GLOBAL);
    }

    @Override
    public void ondeleted(int position) {
        tittles.remove(position);
    }

    @Override
    public void itemMoved(int fromPosition, int toPosition) {
        //todo 更新位置。需要将位置也进行更新

        TittleBean t = tittles.get(toPosition);
        tittles.set(toPosition, tittles.get(fromPosition));
        tittles.set(fromPosition, t);
        for (TittleBean tt : tittles) {
            Log.e("itemMoved: ", tt.getName());
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                isEnter = false;
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                isEnter = true;
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                isEnter = false;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if (isEnter) {
                    if (event.getLocalState() instanceof TittleBean)
                        if (!tittles.contains((TittleBean) event.getLocalState())) {
                            tittles.add(bean);
                        }
                }
                isEnter = false;
                break;
            default:
                break;
        }
        return true;
    }

    public void setString(String name) {
        chooseName.set(name);
    }

    public BindingCommand onCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onchange.set(false);
            onchange.set(true);

//            Intent intent = new Intent("com.xiniu.autovoice");
//            ComponentName comp = new ComponentName("com.byd.autovoice", "com.byd.di3l.setting.VoiceSetActivity");
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.setComponent(comp);
//            CarHandApplication.getContext().startActivity(intent);
        }
    });

//      public BindingCommand onReleaseCommand = new BindingCommand(new BindingAction() {
//          @Override
//          public void call() {
//         onchange.set(true);
//          }
//      });
}
