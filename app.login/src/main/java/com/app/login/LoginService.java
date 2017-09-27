package com.app.login;

import com.lib.utils.print.L;

/**
 * LoginService
 *
 * @author EthanCo
 * @since 2017/9/22
 */
public class LoginService {
    private LoginService() {
    }

    private static class SingleTonHolder {
        private static LoginService sInstance = new LoginService();
    }

    public static LoginService getInstance() {
        return SingleTonHolder.sInstance;
    }

    private long lastTime;

    public boolean login() {
        lastTime = System.currentTimeMillis();
        return true;
    }

    public synchronized LoginStatus isLogin() {
        long currTime = System.currentTimeMillis();
        long dTime = currTime - lastTime;
        L.i("Z-Login", "currTime:" + currTime + " lastTime:" + lastTime + " dTime:" + dTime);
        if (lastTime <= 0) {
            return LoginStatus.LOGOUT;
        } else if (dTime < 10 * 1000) {
            return LoginStatus.LOGIN;
        } else {
            return LoginStatus.EXPIRED;
        }
    }

    public enum LoginStatus {
        LOGIN, //1已登录
        LOGOUT, //未登录
        EXPIRED //登录过时
    }
}
