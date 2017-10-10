package com.lib.frame.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.frame.view.abs.ICreate;
import com.lib.frame.viewmodel.BaseViewModel;


/**
 * @param <V> View
 * @param <T> ViewModel
 * @Description Created by EthanCo on 2016/6/13.
 */
@SuppressWarnings("unchecked")
public abstract class BaseActivity<V, T extends BaseViewModel<V>> extends ToolbarActivity implements ICreate {

    private static final String TAG = "Z-BaseActivity";
    protected T mViewModel; //ViewModel对象
    private static final boolean IS_PRINT_LIFECYCLE = false; //是否打印Activity生命周期
    private View decorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printLifeCycle("onCreate : ");
        decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        mViewModel = createViewModel(); //创建ViewModel
        if (mViewModel != null) {
            mViewModel.attachView((V) this); //View与ViewModel建立关系
        }

        initVarAndView(null, null, savedInstanceState);
        midfield();
        initEvent();
        initDoing();
    }

    //创建ViewModel
    protected abstract T createViewModel();

    @Override
    public final View initVarAndView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initVarAndView(savedInstanceState);
        return null;
    }

    //初始化变量和界面
    public abstract void initVarAndView(Bundle savedInstanceState);

    //用于继承自BaseActivity的基类 进行一些初始化，一般情况下，不用重写
    public void midfield() {
    }

    //初始化事件
    public void initEvent() {

    }

    //开始执行
    public void initDoing() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        printLifeCycle("onStart : ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        printLifeCycle("onResume : ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        printLifeCycle("onPause : ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printLifeCycle("onStop : ");
    }

    @Override
    protected void onDestroy() {
        printLifeCycle("onDestroy : ");
        if (mViewModel != null) {
            mViewModel.detachView();
        }
        super.onDestroy();
    }

    private void printLifeCycle(String msg) {
        if (IS_PRINT_LIFECYCLE) {
            Log.i(TAG, getClass().getSimpleName() + " - " + msg);
        }
    }

    public <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }

}
