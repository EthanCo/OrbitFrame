package com.app.settings

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.app.settings.databinding.SettingsActivitySettingsBinding
import com.lib.frame.view.simple.BaseSimpleActivity
import com.lib.utils.io.SPUtil

@Route(path = "/settings/home")
class SettingsActivity : BaseSimpleActivity() {
    private lateinit var binding: SettingsActivitySettingsBinding

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.settings_activity_settings)
        binding = DataBindingUtil.setContentView<
                SettingsActivitySettingsBinding>(this, R.layout.settings_activity_settings)
        initToolbar("设置", true)
        binding.switchShowToast.isChecked = SPUtil.getBoolean(this, SPUtil.KEY_SHOW_TOAST)
        binding.switchShowToast.setOnCheckedChangeListener({
            _, checked ->
            SPUtil.putBoolean(SettingsActivity@ this, SPUtil.KEY_SHOW_TOAST, checked)
        })
    }
}
