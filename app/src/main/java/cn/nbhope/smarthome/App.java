package cn.nbhope.smarthome;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * App
 *
 * @author EthanCo
 * @since 2017/8/29
 */

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //第三方库的初始化尽量放到SplashActivity中
        initLeakCanary();
    }

    private void initLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this))
            LeakCanary.install(this);
    }
}
