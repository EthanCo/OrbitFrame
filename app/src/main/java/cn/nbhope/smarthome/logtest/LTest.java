package cn.nbhope.smarthome.logtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.lib.utils.print.L;

/**
 * L.java 测试
 *
 * @author EthanCo
 * @since 2017/9/20
 */

public class LTest {
    public static final String TAG = "Z-LTest";

    //日志同时将输出至本地，路径在/data/data/包名/cache/TraceLog目录下
    public static void print() {
        //普通String
        L.v("vvvvvvvv");
        L.t(TAG).i("自定义TAG");
        L.i(TAG, "自定义TAG2");

        // 打印Json
        L.json(DataHelper.getJson());
        // 打印XML
        L.xml(DataHelper.getXml());
        // 打印类
        L.d(DataHelper.getObject());
        // 打印List<String>
        L.i(DataHelper.getStringList());
        // 支持List<Object>
        L.d(DataHelper.getObjectList());
        // 支持Map
        L.d(DataHelper.getObjectMap());
        // 打印Bundle
        L.d(getBundle());
        // 打印Intent
        L.d(getIntent());
        // 打印Message
        L.d(getMessage());

        Handler handler = new Handler() {

        };
        L.d(handler.obtainMessage(1));
    }

    private static Message getMessage() {
        Message message = new Message();
        message.setData(new Bundle());
        message.obj = new Man(20);
        message.arg1 = 1;
        message.arg2 = 2;
        message.what = 1232;
        return message;
    }

    private static Intent getIntent() {
        Intent intent = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("aaaa", "12345");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        return intent;
    }

    private static Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("abc", 1);
        bundle.putString("abc2", "text");
        bundle.putSerializable("abc3", DataHelper.getObject());
        bundle.putStringArray("abc4", DataHelper.getStringArray());
        return bundle;
    }
}
