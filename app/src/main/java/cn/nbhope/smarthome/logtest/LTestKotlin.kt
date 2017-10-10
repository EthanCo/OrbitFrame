package cn.nbhope.smarthome.logtest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.lib.utils.print.L

/**
 * L.java 测试

 * @author EthanCo
 * *
 * @since 2017/9/20
 */

object LTestKotlin {
    val TAG = "Z-LTest-Kotlin"

    //日志同时将输出至本地，路径在/data/data/包名/cache/TraceLog目录下
    fun print() {
        var person = PersonK()
        person.age = 10;
        person.name = "heiko"
        person.score = 3.1415926F
        L.d(person)

        //普通String
        L.v("vvvvvvvv")
        L.t(TAG).d("自定义TAG")
        L.d(TAG, "自定义TAG2")

        // 打印Json
        L.json(DataHelper.getJson())
        // 打印XML
        L.xml(DataHelper.getXml())
        // 打印类
        L.d(DataHelper.getObject())
        // 打印List<String>
        L.i(DataHelper.getStringList())
        // 支持List<Object>
        L.d(DataHelper.getObjectList())
        // 支持Map
        L.d(DataHelper.getObjectMap())
        // 打印Bundle
        L.d(bundle)
        // 打印Intent
        L.d(intent)
        // 打印Message
        L.d(message)

        val handler = object : Handler() {

        }
        L.d(handler.obtainMessage(1))
    }

    private val message: Message
        get() {
            val message = Message()
            message.data = Bundle()
            message.obj = Man(20)
            message.arg1 = 1
            message.arg2 = 2
            message.what = 1232
            return message
        }

    private val intent: Intent
        get() {
            val intent = Intent(
                    Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra("aaaa", "12345")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            return intent
        }

    private val bundle: Bundle
        get() {
            val bundle = Bundle()
            bundle.putInt("abc", 1)
            bundle.putString("abc2", "text")
            bundle.putSerializable("abc3", DataHelper.getObject())
            bundle.putStringArray("abc4", DataHelper.getStringArray())
            return bundle
        }
}
