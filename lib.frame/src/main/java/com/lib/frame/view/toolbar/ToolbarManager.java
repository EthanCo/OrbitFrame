package com.lib.frame.view.toolbar;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Toolbar 管理类
 *
 * @author EthanCo
 * @since 2016/12/6
 */

public class ToolbarManager {
    private WeakReference<Activity> contextRef;

    public ToolbarManager(Activity context) {
        this.contextRef = new WeakReference(context);
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @param toolbar           Toolbar或其子类
     * @return
     */
    public Toolbar initToolbar(Toolbar toolbar, String title, boolean displayHomeEnable) {
        if (toolbar != null) {
            TextView tvTitle = (TextView) toolbar.findViewById(com.lib.frame.R.id.title);
            tvTitle.setText(title);

            Activity context = contextRef.get();
            if (context == null) return null;

            //设置toolbar高度和内边距
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                toolbar.getLayoutParams().height = getAppBarHeight(context);
                toolbar.setPadding(toolbar.getPaddingLeft(),
                        getStatusBarHeight(context),
                        toolbar.getPaddingRight(),
                        toolbar.getPaddingBottom());
            }


            ((AppCompatActivity) context).setSupportActionBar(toolbar);

            ActionBar actionbar = ((AppCompatActivity) context).getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(displayHomeEnable);
            actionbar.setDisplayShowHomeEnabled(displayHomeEnable);
            actionbar.setDisplayShowTitleEnabled(true);
        }
        return toolbar;
    }

    private int getAppBarHeight(Context context) {
        return dip2px(40, context) + getStatusBarHeight(context);
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private int dip2px(float dipValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
