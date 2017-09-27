package com.nbhope.hopelauncher.lib.network.sbscribe;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;


/**
 * 对加载失败进行重新尝试，默认最多尝试3次，第一次3秒，第二次6秒，第三次9秒
 */
public class RetryFunction implements Function<Observable<? extends Throwable>, Observable<?>> {
    private int count = 3;//retry count
    private long delay = 3000;//delay time

    public RetryFunction() {

    }

    public RetryFunction(int count) {
        this.count = count;
    }

    public RetryFunction(int count, long delay) {
        this.count = count;
        this.delay = delay;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable
                .zipWith(Observable.range(1, count + 1), new BiFunction<Throwable, Integer, Wrapper>() {
                    @Override
                    public Wrapper apply(Throwable throwable, Integer integer) throws Exception {
                        return new Wrapper(throwable, integer);
                    }
                }).flatMap(new Function<Wrapper, Observable<?>>() {
                    @Override
                    public Observable<?> apply(Wrapper wrapper) throws Exception {
                        if ((wrapper.throwable instanceof ConnectException
                                || wrapper.throwable instanceof SocketTimeoutException
                                || wrapper.throwable instanceof TimeoutException)
                                && wrapper.index < count + 1) {
                            return Observable.timer(delay + (wrapper.index - 1) * delay, TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(wrapper.throwable);
                    }
                });
    }

    private class Wrapper {
        private int index;
        private Throwable throwable;

        public Wrapper(Throwable throwable, int index) {
            this.index = index;
            this.throwable = throwable;
        }
    }
}
