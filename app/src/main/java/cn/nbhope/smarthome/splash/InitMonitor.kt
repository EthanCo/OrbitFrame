package cn.nbhope.smarthome.splash

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.alibaba.android.arouter.launcher.ARouter
import com.lib.utils.app.MainThreadUtil
import java.lang.ref.WeakReference

/**
 * 监视初始化，当所有初始化完毕，跳转到主页面
 *
 * @author EthanCo
 * @since 2017/9/21
 */
class InitMonitor(looper: Looper, private val list: ArrayList<InitItem>,
                  private val activityRef: WeakReference<SplashActivity>) : Handler(looper) {

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)

        if (isAllInited()) {
            val activity = activityRef.get()
            activity?.let {
                ARouter.getInstance().build("/app/home").navigation()
                MainThreadUtil.getInstance().postDelayed({
                        activity.finish()
                }, 500)
            }
        } else {
            sendEmptyMessageDelayed(0, 500)
        }
    }

    private fun isAllInited(): Boolean {
        for (initItem in list) {
            if (!initItem.initComplete) {
                return false
            }
        }
        return true
    }
}