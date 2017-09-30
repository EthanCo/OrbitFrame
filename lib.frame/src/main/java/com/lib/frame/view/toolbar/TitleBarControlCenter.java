package com.lib.frame.view.toolbar;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lib.frame.R;

/**
 * TitleBar 控制中心
 *
 * @author EthanCo
 * @since 2017/7/6
 */

public class TitleBarControlCenter {

    @Nullable
    public static <T> T initTitle(final Activity activity, View rootView, String title, boolean displayHomeEnable) {
        View barView = rootView.findViewById(R.id.toolbar);
        if (barView instanceof Toolbar) {
            ToolbarManager toolbarManager = new ToolbarManager(activity);
            return (T) toolbarManager.initToolbar((Toolbar) barView, title, displayHomeEnable);
        } else if (barView.getClass().getName().toLowerCase().contains("titlebar")) {
            throw new IllegalStateException("请依赖TitleBar后取消这里的注释");
//        } else if(barView instanceof TitleBar){
//            TitleBarManager titleBarManager = new TitleBarManager();
//            return (T) titleBarManager.initTitleBar(activity, (TitleBar) barView, title, displayHomeEnable);
        }
        return null;
    }
}
