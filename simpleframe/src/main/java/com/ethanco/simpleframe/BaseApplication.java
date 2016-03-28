package com.ethanco.simpleframe;

import android.app.Application;

import com.ethanco.simpleframe.handler.CrashFileSaveListener;
import com.ethanco.simpleframe.handler.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Zhk on 2015/12/22.
 */
public abstract class BaseApplication extends Application implements CrashFileSaveListener {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!isDebug()) {
            CrashHandler crashHandler = CrashHandler.getInstance(this);
            crashHandler.init(this);
            crashHandler.sendPreviousReportsToServer();
        }
        LeakCanary.install(this);
    }

    /**
     * 是否是Debug状态
     *
     * @return
     */
    protected abstract boolean isDebug();

    /**
     * 捕捉到异常后，可以进行自己的一些处理
     *
     * @param filePath 异常文件文件名
     */
    @Override
    public abstract void crashFileSaveTo(String filePath);
}
