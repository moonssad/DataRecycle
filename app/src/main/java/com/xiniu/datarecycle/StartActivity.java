package com.xiniu.datarecycle;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xiniu.datarecycle.utils.Const;
import com.xiniu.datarecycle.utils.FactoryClass;
import com.xiniu.datarecycle.utils.PermissionChecker;
import com.xiniu.datarecycle.utils.ShareUtil;
import com.yey.library_restartapp.YRestartAPP;

import java.lang.ref.WeakReference;
import java.security.Permission;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.security.auth.login.LoginException;

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
    private String[] permissions =new String[]{ Manifest.permission.KILL_BACKGROUND_PROCESSES};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetWindowState();
        setContentView(R.layout.activity_start);
        tittleImage = (ImageView) findViewById(R.id.iv_tittle);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        tittleImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                restartApp();
            }
        });
        List<String>  titles = FactoryClass.getStringList();
        activityAdapter = new StartActivityAdapter(titles);

        activityAdapter.setOnClickListener(new StartActivityAdapter.OnItemClickListener() {
            @Override
            public void onClick(String text) {
                startActivity(new Intent(StartActivity.this,FactoryClass.getClass(text)));
            }
        });
//        RecyclerView.LayoutManager grideLayoutManager = new GridLayoutManager(CarHandApplication.getContext(),3,RecyclerView.VERTICAL,false);
        RecyclerView.LayoutManager LayoutManager =new LinearLayoutManager(CarHandApplication.getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(LayoutManager);
//        recyclerView.addItemDecoration(new GrideItemDecoration(3,50,50 ,false));
        recyclerView.setAdapter(activityAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void restartApp(){
        boolean isopen = (boolean)ShareUtil.get(this,Const.OPEN_STATE,Const.OPEN_STATE_VALUE);
        ShareUtil.put(this, Const.OPEN_STATE,!isopen);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (PermissionChecker.checkPermissions(this, permissions)){
            YRestartAPP.restartAPP(this,50);
        }else{
                requestPermissions(permissions,10001);
        }


    }
    protected  void SetWindowState(){
        boolean isopen = (boolean)ShareUtil.get(this,Const.OPEN_STATE,Const.OPEN_STATE_VALUE);
        if (isopen) {
            WindowManager.LayoutParams param = getWindow().getAttributes();
            param.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(param);
        }else{
            WindowManager.LayoutParams param = getWindow().getAttributes();
            param.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(param);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==10001){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ActivityManager activityManager =(ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
                activityManager.restartPackage(this.getPackageName());
            }
        }
    }


}
