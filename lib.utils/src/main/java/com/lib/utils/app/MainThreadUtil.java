package com.lib.utils.app;

import android.os.Handler;
import android.os.Looper;

/**
 * Main Handler
 *
 * @author EthanCo
 * @since 2017/9/25
 */

public class MainThreadUtil extends Handler {
    private MainThreadUtil() {
    }

    private static class SingleTonHolder {
        private static MainThreadUtil sInstance = new MainThreadUtil(Looper.getMainLooper());
    }

    public static MainThreadUtil getInstance() {
        return SingleTonHolder.sInstance;
    }

    protected MainThreadUtil(Looper looper) {
        super(looper);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            runnable.run();
        } else {
            SingleTonHolder.sInstance.post(runnable);
        }
    }
}