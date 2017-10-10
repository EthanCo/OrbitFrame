package cn.nbhope.smarthome

import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lib.frame.view.simple.BaseSimpleActivity
import kotlinx.android.synthetic.main.activity_home.*


@Route(path = "/app/home")
class HomeActivity : BaseSimpleActivity() {
    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_home)
        initToolbar(R.string.app_name, false)
    }

    override fun initEvent() {
        super.initEvent()
        navigation_home.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.menu_device -> switchFragment("/device/device_fragment", R.string.title_device)
                R.id.menu_scene -> switchFragment("/scene/scene_fragment", R.string.title_scene)
                R.id.menu_settings -> switchFragment("/settings/settings_fragment", R.string.title_settings)
            }
            true
        }
    }

    override fun initDoing() {
        super.initDoing()
        navigation_home.selectedItemId = R.id.menu_device
    }

    private fun switchFragment(path: String, titleRes: Int) {
        val result = ARouter.getInstance().build(path).navigation()
        result?.let {
            val fragment = result as Fragment
            changeFragment(fragment, R.id.layout_home_container)
            initToolbar(titleRes, false)
        }
    }
}
