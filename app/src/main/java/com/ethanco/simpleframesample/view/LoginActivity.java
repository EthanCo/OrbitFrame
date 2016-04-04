package com.ethanco.simpleframesample.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.ethanco.simpleframe.utils.T;
import com.ethanco.simpleframe.view.BaseActivity;
import com.ethanco.simpleframesample.R;
import com.ethanco.simpleframesample.bean.User;
import com.ethanco.simpleframesample.databinding.ActivityLoginBinding;
import com.ethanco.simpleframesample.viewmodel.LoginViewModel;

/**
 * Created by EthanCo on 2016/4/1.
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    private ActivityLoginBinding binding;
    private ProgressDialog waitDialog;

    //@Inject
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVar();
        initToolbar();
        initEvent();
    }

    private void initEvent() {
        binding.btnLogin.setOnClickListener(v -> {
            String userName = binding.etUserName.getText().toString();
            String password = binding.etPassWord.getText().toString();
            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password))
                T.show(getApplication(), "账号或密码不能为空", Toast.LENGTH_SHORT);
            else
                loginViewModel.login(new User(userName, password));
        });
    }

    private void initVar() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        //DaggerLoginActivityComponent.
    }

    private void initToolbar() {
        binding.toolbar.setTitle(getString(R.string.login));
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public void showWaitDailog() {
        dismissDialog();
        waitDialog = ProgressDialog.show(this, "正在登陆", "请稍等...");
        waitDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (null != waitDialog && waitDialog.isShowing()) {
            waitDialog.dismiss();
        }
    }

    @Override
    public void loginSuccess(User u) {
        T.show(getApplication(), "登陆成功", Toast.LENGTH_SHORT);
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra(WeatherActivity.EXTRA_USER, u);
        ActivityCompat.startActivity(this, intent, null);
        finish();
    }

    @Override
    public void loginFailed(String error) {
        T.show(getApplication(), "登陆失败:" + error, Toast.LENGTH_SHORT);
    }
}
