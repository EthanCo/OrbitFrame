package cn.nbhope.smarthome.splash

/**
 * 用于第三方库初始化 封装至List中
 *
 * @author EthanCo
 * @since 2017/9/21
 */
class InitItem(val callback: () -> Unit) {
    @Volatile var initComplete = false

    fun init() {
        callback.invoke()
        initComplete = true
    }
}