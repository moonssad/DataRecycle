package com.xiniu.skin;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import com.xiniu.skin.inter.ISkin;

import java.util.LinkedList;
import java.util.List;

public class SkinManager {
    private static SkinManager instance;
    /* access modifiers changed from: private */
    public Handler mMainLooper1 = new Handler(Looper.getMainLooper());
    private Handler mSkinHandler = null;
    private onNightModeListener modeListener;

    public interface SkinTask extends Runnable {
        void cancel();
    }

    public interface onNightModeListener {
        boolean isNightMode();
    }

    private SkinManager() {
        HandlerThread handlerThread = new HandlerThread("skinThread");
        handlerThread.start();
        this.mSkinHandler = new Handler(handlerThread.getLooper());
    }

    public static synchronized SkinManager getInstance() {
        SkinManager skinManager;
        synchronized (SkinManager.class) {
            if (instance == null) {
                instance = new SkinManager();
            }
            skinManager = instance;
        }
        return skinManager;
    }

    public void registerNightModeListener(onNightModeListener onnightmodelistener) {
        this.modeListener = onnightmodelistener;
    }

    public boolean isNightMode() {
        onNightModeListener onnightmodelistener = this.modeListener;
        if (onnightmodelistener != null) {
            return onnightmodelistener.isNightMode();
        }
        return false;
    }

    public static synchronized void destroy() {
        synchronized (SkinManager.class) {
            if (instance != null) {
                instance.mSkinHandler.getLooper().quit();
                instance.mSkinHandler = null;
            }
        }
    }

    public SkinTask updateView(View view) {
        return updateView(view, NigthModeGlobal.isNightMode(), false);
    }

    public SkinTask updateView(View view, boolean z) {
        return updateView(view, NigthModeGlobal.isNightMode(), z);
    }

    public SkinTask updateView(View view, final boolean z, boolean z2) {
        if (view == null) {
            return null;
        }
        final LinkedList linkedList = new LinkedList();
        restore(view, linkedList, z, z2);
        updateView((List<ISkin.ISkinAdapter>) linkedList, z);
        return new SkinTask() {
            private boolean isCancel = false;

            public void cancel() {
                this.isCancel = true;
            }

            public void run() {
                if (!this.isCancel) {
                    SkinManager.this.mMainLooper1.post(new Runnable() {
                        public void run() {
                            SkinManager.this.updateView((List<ISkin.ISkinAdapter>) linkedList, z);
                        }
                    });
                }
            }
        };
    }

    public void removeTask(SkinTask skinTask) {
        skinTask.cancel();
        Handler handler = this.mSkinHandler;
        if (handler != null) {
            handler.removeCallbacks(skinTask);
        }
    }

    private void restore(View view, List<ISkin.ISkinAdapter> list, boolean z, boolean z2) {
        ISkin.ISkinAdapter adpter;
        if ((view instanceof ISkin) && (adpter = ((ISkin) view).getAdpter()) != null) {
            adpter.initSkin(view);
            list.add(adpter);
        }
        if (z2 && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                restore(viewGroup.getChildAt(i), list, z, z2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateView(List<ISkin.ISkinAdapter> list, boolean z) {
        for (ISkin.ISkinAdapter apply : list) {
            apply.apply(z);
        }
    }
}
