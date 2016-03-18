package com.ethanco.simpleframe.compat;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

/**
 * Created by EthanCo on 2016/1/10.
 */
public class ColorCompat {
    @ColorInt
    public static int getColor(Context context,@ColorRes int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(color,null);
        }else{
            return context.getResources().getColor(color);
        }
    }
}
