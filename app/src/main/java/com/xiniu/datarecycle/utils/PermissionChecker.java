package com.xiniu.datarecycle.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;


import com.xiniu.datarecycle.R;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wang.weiyang on 2017/5/24.
 */

public class PermissionChecker {
    /**
     * 获取缺少的权限
     *
     * @param appliContext applicationContext
     * @return
     */
    public static Map<String, String> lackOfPermissions(Context appliContext){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            Map<String, String> permissionsMap = new LinkedHashMap<>();
            permissionsMap.put(appliContext.getString(R.string.permission_phone), Manifest.permission.READ_PHONE_STATE);
            permissionsMap.put(appliContext.getString(R.string.permission_location), Manifest.permission.ACCESS_FINE_LOCATION);
            permissionsMap.put(appliContext.getString(R.string.permission_storage), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissionsMap.put(appliContext.getString(R.string.permission_audio), Manifest.permission.RECORD_AUDIO);
            Map<String, String> autoPermissions = new LinkedHashMap<>();
            Iterator iter = permissionsMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String permissionName = (String) entry.getKey();
                String permission = (String) entry.getValue();
                if (appliContext.checkSelfPermission(permission) ==
                    PackageManager.PERMISSION_DENIED) {
                    autoPermissions.put(permissionName, permission);
                }
            }
            return autoPermissions;
        }
        return null;
    }

    /**
     * 检测是否有缺少的权限
     *
     * @param appContext applicationContext
     * @param permissions 需要的权限
     * @return
     */
    public static boolean checkPermissions(Context appContext, String... permissions){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        for (String permission : permissions) {
            if(appContext.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

}
