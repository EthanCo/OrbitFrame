package com.app.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.app.login.databinding.ActivityLoginBinding
import com.lib.frame.view.simple.BaseSimpleActivity
import com.lib.utils.app.MainThreadUtil

@Route(path = "/login/home")
class LoginActivity : BaseSimpleActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun initVarAndView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initToolbar("登录", false)
    }

    override fun initEvent() {
        super.initEvent()
        binding.buttonLogin.setOnClickListener({
            val loginSuccess = LoginService.getInstance().login()
            if (loginSuccess) navigateToHomePage()
        })
    }

    private fun navigateToHomePage() {
        ARouter.getInstance().build("/app/home").navigation()
        MainThreadUtil.getInstance().postDelayed({
            this@LoginActivity.finish()
        },500)
    }
}
