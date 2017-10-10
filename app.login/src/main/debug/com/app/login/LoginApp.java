package com.app.login;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 登录模块 Debug阶段的Application
 *
 * @author EthanCo
 * @since 2017/9/22
 */

public class LoginApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        T.init(this);
        val traceLog = TraceLog.Builder()
                .addTrace(LoggerTrace(2, 6))
                .addTrace(DiskLogTrace(this))
                .setDefaultTag(getString(R.string.app_name))
                .build()
        L.init(traceLog)
    }
}
