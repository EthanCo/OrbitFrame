package com.ethanco.simpleframe;


import com.ethanco.simpleframe.frame.BaseApplication;
import com.ethanco.simpleframe.frame.rxjava.net.RetrofitFactory;

/**
 * @Description TODO
 * Created by EthanCo on 2016/3/30.
 */
public class MyApplication extends BaseApplication {
    private static final String BUGLY_APPID = "900024404";

    @Override
    protected String getBuglyAppID() {
        return BUGLY_APPID;
    }

    @Override
    protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitFactory.createSINARetrofit(Constants.BASE_URL_JUHE);
    }
}
