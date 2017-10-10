package com.lib.frame.view;

import android.support.annotation.StringRes;
import android.view.MenuItem;
import android.view.View;

import com.lib.frame.view.toolbar.TitleBarControlCenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * @Description toolbar Activity - 沉侵式
 * Created by EthanCo on 2016/4/14.
 */
public abstract class ToolbarActivity extends RxAppCompatActivity {
    //Activity的Toolbar是否已启用
    protected boolean activityToolbarEnable = false;


    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />或有id为@+id/toolbar的TitleBar)
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @return
     */
    protected View initToolbar(String title, boolean displayHomeEnable) {
        activityToolbarEnable = displayHomeEnable;
        return TitleBarControlCenter.initTitle(this, getWindow().getDecorView(), title, displayHomeEnable);
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />或有id为@+id/toolbar的TitleBar)
     * 或
     * 有id为toolbar的TitleBar View
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @return
     */
    protected View initToolbar(@StringRes int title, boolean displayHomeEnable) {
        return initToolbar(getString(title), displayHomeEnable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (activityToolbarEnable) {
                finish();
            }
            return activityToolbarEnable;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TitleBarControlCenter.destory(this);
    }
}
