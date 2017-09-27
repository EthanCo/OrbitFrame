package com.lib.frame.view;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.MenuItem;
import android.view.View;

import com.lib.frame.view.toolbar.TitleBarControlCenter;
import com.lib.frame.view.toolbar.ToolbarManager;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Fragment Toolbar 基类
 * Created by EthanCo on 2016/12/6.
 */
@SuppressWarnings("unchecked")
public abstract class ToolbarFragment extends RxFragment {
    private ToolbarManager toolbarManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        toolbarManager = new ToolbarManager(getActivity());
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     * 或
     * 有id为toolbar的 TitleBar View
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @param rootView          fragment rootView
     * @return
     */
    protected View initToolbar(View rootView, String title, boolean displayHomeEnable) {
        setHasOptionsMenu(true); //使 fragment的onOptionsItemSelected 启用
        return TitleBarControlCenter.initTitle(getActivity(), rootView, title, displayHomeEnable);
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     * 或
     * 有id为toolbar的 TitleBar View
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @param rootView          fragment rootView
     * @return
     */
    protected View initToolbar(View rootView, @StringRes int title, boolean displayHomeEnable) {
        return initToolbar(rootView, getString(title), displayHomeEnable);
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     * 或
     * 有id为toolbar的 TitleBar View
     * <p>
     * 从Fragment中查找titlebar
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @return
     */
    protected void initToolbar(final String title, final boolean displayHomeEnable) {
        getActivity().getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                initToolbar(getView(), title, displayHomeEnable);
            }
        });
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     * 或
     * 有id为toolbar的 titleBar View
     * <p>
     * 从Fragment中查找titlebar
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @return
     */
    protected void initToolbar(@StringRes final int title, final boolean displayHomeEnable) {
        getActivity().getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                initToolbar(getView(), getString(title), displayHomeEnable);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
