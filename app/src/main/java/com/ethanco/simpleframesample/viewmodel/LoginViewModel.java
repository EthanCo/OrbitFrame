package com.ethanco.simpleframesample.viewmodel;

import com.ethanco.simpleframesample.bean.User;
import com.ethanco.simpleframesample.model.ILoginModel;
import com.ethanco.simpleframesample.model.LoginModel;
import com.ethanco.simpleframesample.view.ILoginView;

/**
 * Created by EthanCo on 2016/4/2.
 */
public class LoginViewModel {
    private ILoginView view;
    private ILoginModel model;

    public LoginViewModel(ILoginView view) {
        this.view = view;
        this.model = new LoginModel();
    }

    public void login(User user) {
        view.showWaitDailog();
        model.login(user).subscribe(
                r -> {
                    if (r.getSuccess())
                        view.loginSuccess(r.getEntity());
                    else
                        view.loginFailed(r.getError());
                }, t -> view.loginFailed(t.toString()),
                () -> view.dismissDialog());
    }
}
