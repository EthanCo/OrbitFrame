package cn.nbhope.smarthome

import android.databinding.DataBindingUtil
import android.os.Bundle
import cn.nbhope.smarthome.databinding.ActivityHomeBinding
import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.frame.view.simple.BaseSimpleActivity
import com.lib.kotlinex.extension.toast

@Route(path = "/app/main")
class HomeActivity : BaseSimpleActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun initVarAndView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil
                .setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
    }

    override fun initEvent() {
        super.initEvent()
        binding.navigationHome.setOnNavigationItemSelectedListener {
            item ->
            toast(item.itemId.toString())
            true
        }
    }
}
