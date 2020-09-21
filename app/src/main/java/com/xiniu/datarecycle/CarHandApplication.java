package com.xiniu.datarecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.xiniu.datarecycle.utils.MMKVUtil;


/**
 * 创建者：wyz
 * 创建时间：2020/4/16
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class CarHandApplication extends Application implements Application.ActivityLifecycleCallbacks {
    public static Context context;
    public static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        this.context = this;
        registerActivityLifecycleCallbacks(this);
        MMKVUtil.init(this);

//        //启用Log日志
//        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        okHttpClientBuilder.addInterceptor(loggingInterceptor);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public static Context getContext() {
        return context;
    }

    public static Application getApp() {
        return app;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                // 进行资源释放操作
                break;
        }

        Log.e("onTrimMemory:", "level"+level);
    }
}
