package com.app.device

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.app.device.databinding.DeviceActivityDeviceBinding
import com.lib.frame.view.simple.BaseSimpleActivity

class DeviceActivity : BaseSimpleActivity() {
    private lateinit var binding: DeviceActivityDeviceBinding

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.device_activity_device)
        binding = DataBindingUtil.setContentView<DeviceActivityDeviceBinding>(this, R.layout.device_activity_device)
        initToolbar("设备", true)
        val result = ARouter.getInstance().build("/device/device_fragment").navigation()
        result?.let {
            val fragment = result as Fragment
            changeFragment(fragment, R.id.layout_container)
        }
    }

    override fun initDoing() {
        super.initDoing()
    }

    override fun initEvent() {
        super.initEvent()
    }
}
