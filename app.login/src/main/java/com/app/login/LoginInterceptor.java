package com.app.login;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lib.utils.app.MainThreadUtil;
import com.lib.utils.print.L;
import com.lib.utils.print.T;

/**
 * LoginInterceptor
 *
 * @author EthanCo
 * @since 2017/9/22
 */

@Interceptor(priority = 10, name = "LoginInterceptor")
public class LoginInterceptor implements IInterceptor {
    private static final String TAG = "Z-LoginInterceptor";
    private Context context;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        L.t(TAG).i("process:");
        callback.onContinue(null);
        LoginService loginService = LoginService.getInstance();
        LoginService.LoginStatus loginStatus = loginService.isLogin();
        L.i(TAG,"isLogin:" + loginStatus);
        if (loginStatus == LoginService.LoginStatus.LOGIN) {
            callback.onContinue(postcard);
        } else {
            callback.onInterrupt(null);
            ARouter.getInstance().build("/login/home").greenChannel().navigation();
            if (loginStatus == LoginService.LoginStatus.EXPIRED) {
                MainThreadUtil.getInstance().runOnUiThread(() -> T.show("登录过时，请重新登录"));
            }
        }
    }

    @Override
    public void init(Context _context) {
        Log.i(TAG, "init:");
        context = _context;
    }
}
