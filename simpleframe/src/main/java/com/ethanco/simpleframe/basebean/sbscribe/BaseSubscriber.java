package com.ethanco.simpleframe.basebean.sbscribe;

import com.ethanco.simpleframe.utils.LogUtils;

import rx.Subscriber;

/**
 * Created by Zhk on 2016/1/3.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        //do nothing
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.i("SimpleSubscriber", "onError: " + e.getMessage());
    }
}
