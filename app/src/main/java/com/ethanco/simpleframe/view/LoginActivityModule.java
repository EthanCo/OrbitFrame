package com.ethanco.simpleframe.view;

import com.ethanco.simpleframe.viewmodel.LoginViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/4/4.
 */
@Module
public class LoginActivityModule {
    @Provides
    public LoginViewModel provideLoginViewModel(LoginActivity activity) {
        return new LoginViewModel(activity);
    }
}
