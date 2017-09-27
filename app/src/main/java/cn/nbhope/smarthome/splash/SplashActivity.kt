package cn.nbhope.smarthome.splash

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import cn.nbhope.smarthome.App
import cn.nbhope.smarthome.BuildConfig
import cn.nbhope.smarthome.R
import com.alibaba.android.arouter.launcher.ARouter
import com.ethanco.loggerex.LoggerTrace
import com.ethanco.tracelog.TraceLog
import com.ethanco.tracelog.logs.DiskLogTrace
import com.lib.kotlinex.KotlinGlobal
import com.lib.utils.crash.CrashHandler
import com.lib.utils.print.L
import com.lib.utils.print.T
import com.nbhope.hopelauncher.lib.network.NetFacade
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val initItems = generateInitItems()
        initMonitorAndScheduledTime(initItems)
        initLibAsync(initItems)
    }

    private fun generateInitItems(): ArrayList<InitItem> {
        val initItems = ArrayList<InitItem>()
        initItems.add(InitItem { initARouter() })
        initItems.add(InitItem { initNetwork() })
        initItems.add(InitItem { initLog() })
        initItems.add(InitItem { initToast() })
        initItems.add(InitItem { initBugly() })
        initItems.add(InitItem { initKotlinEx() })
        return initItems
    }

    private fun initMonitorAndScheduledTime(initItems: ArrayList<InitItem>) {
        val monitor = InitMonitor(Looper.getMainLooper(), initItems, WeakReference(this))
        monitor.sendEmptyMessageDelayed(0, 1500)
    }

    private fun initLibAsync(list: ArrayList<InitItem>) {
        for (item in list) {
            Schedulers.io().scheduleDirect({
                item.init()
            })
        }
    }

    private fun initNetwork() {
        NetFacade.init(App.getInstance())
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(App.getInstance())
    }

    private fun initLog() {
        val traceLog = TraceLog.Builder()
                .addTrace(LoggerTrace(2, 6))
                .addTrace(DiskLogTrace(App.getInstance()))
                .setDefaultTag(getString(R.string.app_name))
                .build()
        L.init(traceLog)
    }

    private fun initToast() {
        T.init(App.getInstance())
    }

    private fun initBugly() {
        if (!BuildConfig.DEBUG) {
            val context = App.getInstance()
            val explicitIntent = Intent(context, SplashActivity::class.java)
            CrashHandler.getInstance().initRestart(BuildConfig.DEBUG,
                    context, 1000, explicitIntent)
            initBugly()
        }
    }
    
    private fun initKotlinEx() {
        KotlinGlobal.init(App.getInstance())
    }
}
