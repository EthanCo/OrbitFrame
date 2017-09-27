package com.app.scene

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.app.scene.databinding.SceneActivitySceneBinding
import com.lib.frame.view.simple.BaseSimpleActivity

@Route(path = "/scene/home")
class SceneActivity : BaseSimpleActivity() {
    @Autowired
    @JvmField var explain: String? = null

    private lateinit var binding: SceneActivitySceneBinding

    override fun initVarAndView(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        binding = DataBindingUtil.setContentView<SceneActivitySceneBinding>(
                this, R.layout.scene_activity_scene)
        initToolbar("Scene", true)
        
        binding.tvExplain.text = explain
    }
}
