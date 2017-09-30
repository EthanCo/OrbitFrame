package cn.nbhope.smarthome

import android.databinding.DataBindingUtil
import android.os.Bundle
import cn.nbhope.imageproxylib.ImageProxy
import cn.nbhope.smarthome.databinding.ActivityMainBinding
import cn.nbhope.smarthome.logtest.LTest
import cn.nbhope.smarthome.logtest.LTestKotlin
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.lib.frame.view.simple.BaseSimpleActivity
import com.lib.kotlinex.extension.toast
import com.lib.utils.io.SPUtil
import com.lib.utils.print.L
import com.nbhope.hopelauncher.lib.network.AppCommandType
import com.nbhope.hopelauncher.lib.network.NetFacade
import com.nbhope.hopelauncher.lib.network.bean.request.CmdRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Route(path = "/app/main")
class MainActivity : BaseSimpleActivity() {
    private lateinit var binding: ActivityMainBinding
    private val imagePath = "http://desk.fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/01/00/ChMkJlmhG0yISd-jAEwxSgHE_aIAAf_JAKgfXUATDFi295.jpg?downfile=1503974314159.jpg"

    override fun initVarAndView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        initToolbar(R.string.app_name, false)
    }

    override fun initEvent() {
        super.initEvent()

        binding.btnSecond.setOnClickListener { navigateToSecondActivity() }
        binding.btnTestJava.setOnClickListener { navigateToTestJavaActivity() }
        binding.btnScene.setOnClickListener { navigateToSceneActivity() }
        binding.btnGetServiceTime.setOnClickListener { getServiceTime() }
        binding.btnSettings.setOnClickListener { navigateToSettingsActivity() }
        binding.btnPrintLog.setOnClickListener { testLogPrint() }
    }

    private fun navigateToSettingsActivity() {
        ARouter.getInstance().build("/settings/home")
                .navigation(MainActivity@ this)
    }

    private fun navigateToSecondActivity() {
        ARouter.getInstance().build("/app/second")
                .withString("info", "== Second ==")
                .navigation(MainActivity@ this)
    }

    private fun navigateToSceneActivity() {
        ARouter.getInstance().build("/scene/home")
                .withString("explain", ">> Scene <<")
                .navigation(MainActivity@ this)
    }

    private fun navigateToTestJavaActivity() {
        ARouter.getInstance().build("/scene/test_java")
                .navigation(MainActivity@ this)
    }

    private fun testLogPrint() {
        L.i("开始打印")
        LTest.print()
        LTestKotlin.print()
        L.i("结束打印")
    }

    override fun initDoing() {
        super.initDoing()
        loadImage()
        testSettingsModule()
    }

    private fun loadImage() {
        //ImageProxy 现在基于Glide 4.0
        ImageProxy.with(this)
                .load(imagePath)
                .into(binding.imgTest)
    }

    private fun testSettingsModule() {
        val isShowToast = SPUtil.getBoolean(this, SPUtil.KEY_SHOW_TOAST)
        if (isShowToast) toast("Settings模块测试")
    }

    private fun getServiceTime() {
        val cmd = CmdRequest()
        cmd.cmd = AppCommandType.GET_SERVICE_TIME

        NetFacade.getInstance()
                .provideDefaultService()
                .getServerTime(cmd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { response ->
                            val json = Gson().toJson(response)
                            L.i("result:" + json)
                            toast(json)
                        }, { error ->
                    L.i("error:" + error)
                }, {})
    }
}
