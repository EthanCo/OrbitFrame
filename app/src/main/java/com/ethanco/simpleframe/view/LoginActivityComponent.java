package com.ethanco.simpleframe.view;

import dagger.Component;

/**
 * Created by EthanCo on 2016/4/4.
 */
@Component(modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
}
