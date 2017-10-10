package com.nbhope.hopelauncher.lib.network.sbscribe;

import android.text.TextUtils;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * @Description RxJava 帮助类
 * Created by EthanCo on 2016/8/12.
 */
public class RxUtil {

    /**
     * 检查是否未空
     *
     * @param object
     * @return 如果为空抛出异常，不为空返回true
     */
    public static boolean checkEmpty(Object object) {
        if (object != null) {
            return true;
        } else {
            throw new IllegalStateException("传入值为空");
        }
    }

    /**
     * 检查是否未空
     *
     * @param text
     * @return 如果为空抛出异常，不为空返回true
     */
    public static boolean checkEmpty(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            return true;
        } else {
            throw new IllegalStateException("传入值为空");
        }
    }

    /**
     * 检查是否未空
     *
     * @param object
     * @return 如果为空retrun false，不为空返回true
     */
    public static boolean checkEmptyQuietly(Object object) {
        if (object != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查是否未空
     *
     * @param text
     * @return 如果为空retrun false，不为空返回true
     */
    public static boolean checkEmptyQuietly(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较
     *
     * @param expected 预期值
     * @param actual   实际值
     * @return 如果相等返回true，否则爆出异常
     */
    public static boolean assertEquals(Object expected, Object actual) {
        checkEmpty(expected);
        checkEmpty(actual);

        if (expected == expected || expected.equals(actual)) {
            return true;
        } else {
            throw new IllegalStateException("预期值与实际值不相等。 expected:" + expected + " actual:" + actual);
        }
    }

    /**
     * 比较 不抛出异常
     *
     * @param expected 预期值
     * @param actual   实际值
     * @return 如果相等返回true，否则返回false
     */
    public static boolean assertEqualsQuietly(Object expected, Object actual) {
        checkEmpty(expected);
        checkEmpty(actual);

        if (expected == expected || expected.equals(actual)) {
            return true;
        } else {
            return false;
        }
    }

    private static <T> Flowable<T> createData(final T data) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> subscriber) throws Exception {
                try {
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
