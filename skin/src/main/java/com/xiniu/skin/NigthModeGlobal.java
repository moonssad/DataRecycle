package com.xiniu.skin;

/**
 * 创建者：wyz
 * 创建时间：2020-09-21
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class NigthModeGlobal {
    public static final boolean IS_SUPPORT_DAY_NIGHT = true;
    private static boolean isNight;

    public static boolean isNightMode() {
        return isNight;
    }

    public static void setNightMode(boolean z) {
        isNight = z;
    }
}
