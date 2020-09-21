package com.xiniu.datarecycle.utils;

import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;

import java.security.PublicKey;

/**
 * Data 2020/8/28
 * author wyz
 **/
public class MMKVUtil {
    static MMKV kv;

    public static void init(Context context) {
        String dir = context.getFilesDir().getAbsolutePath() + "/mmkv_1";
        String rootDir = MMKV.initialize(dir);
        Log.i("init: ", rootDir + "");
    }

    private static MMKV getMMKV() {
        if (kv == null) {
            kv = MMKV.mmkvWithID("MMKVUtil", MMKV.MULTI_PROCESS_MODE);
        }
        return kv;
    }

    public static void put(String key, Object object) {
        if (object instanceof String) {
            getMMKV().encode(key, (String) object);
        } else if (object instanceof Integer) {
            getMMKV().encode(key, (Integer) object);
        } else if (object instanceof Boolean) {
            getMMKV().encode(key, (Boolean) object);
        } else if (object instanceof Float) {
            getMMKV().encode(key, (Float) object);
        } else if (object instanceof Long) {
            getMMKV().encode(key, (Long) object);
        } else {
            getMMKV().encode(key, object.toString());
        }
    }

    public static Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return getMMKV().decodeString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return getMMKV().decodeInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return getMMKV().decodeBool(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return getMMKV().decodeFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return getMMKV().decodeLong(key, (Long) defaultObject);
        }
        return null;
    }

    public static void remove(String key) {
        getMMKV().remove(key);
    }

    public boolean isContain(String key) {
        for (String s : getMMKV().allKeys()) {
            if (s.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static void clear(Context context) {
        getMMKV().clearAll();
    }

}
