package com.ethanco.simpleframe.compat;

import android.content.Context;
import android.os.Environment;

/**
 * 路径的兼容
 * <p>
 * Created by Zhk on 2015/12/24.
 */
public class DirCompat {
    public static String getCacheDir(Context context) {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return path;
    }
}
