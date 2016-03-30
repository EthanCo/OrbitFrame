package com.ethanco.simpleframe.utils;

import android.util.Log;

import com.ethanco.simpleframe.BaseApplication;
import com.ethanco.simpleframe.BuildConfig;

/**
 * @Description TODO
 * Created by EthanCo on 2016/3/30.
 */
public class M {
    public static void l() {
        Log.i("z-tag", "" + "debug:" + BuildConfig.DEBUG);
        Log.i("z-tag", "" + "-isdebug:" + BaseApplication.IS_DEBUG);
    }
}
