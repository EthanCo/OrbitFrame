package com.ethanco.simpleframesample.view;

import com.ethanco.simpleframesample.bean.User;

/**
 * Created by EthanCo on 2016/4/2.
 */
public interface ILoginView extends IWaitDialogOper{

    void loginSuccess(User u);

    void loginFailed(String error);
}
