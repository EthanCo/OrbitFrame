package com.lib.utils.time;

import android.util.Log;

/**
 * @Description 频率追踪器
 * Created by YOLANDA on 2015-12-07.
 */
public class FrequencyTracker {
    private static final String TAG = "Z-FrequencyTracker";
    private long rateTime = 500;//500毫秒内无效，可自己调整频率
    private volatile long lastClickTime;

    public FrequencyTracker() {
    }

    public FrequencyTracker(long rateTime) {
        this.rateTime = rateTime;
    }

    public boolean isFeasibleAutoRecord() {
        long time = System.currentTimeMillis();
        boolean result = isFeasible(time);
        if (result) setLastTime(time);
        return result;
    }

    public boolean isFeasible() {
        long time = System.currentTimeMillis();
        return isFeasible(time);
    }

    public boolean isFeasible(long time) {
        long timeD = time - lastClickTime;
        Log.i(TAG, "timeD:" + timeD + " lastClickTime:" + lastClickTime + " time:" + time + " rateTime:" + rateTime);
        if (0 < timeD && timeD < rateTime) {
            Log.i(TAG, "return false");
            return false;
        }
        Log.i(TAG, "return true");
        return true;
    }

    public void setLastTime(long time) {
        lastClickTime = time;
    }
}
