package com.bocai.boc_tongxing.simpleframesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ethanco.simpleframe.utils.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CrashReport.testJavaCrash(); CrashReport测试

//        Log.i("z-tag", "zhk-" + BuildConfig.DEBUG);
//        Log.i("z-tag", "zhk-" + "MyApplication.IS_DEBUG:" + MyApplication.IS_DEBUG);

        L.v("z-tag","vvvvvvv");
        L.w("z-tag","wwwwwww");
        L.e("z-tag","eeeeeeee");
        L.i("z-tag","iiiiiiii");
        L.d("z-tag","dddddddd");
        L.v("vvvvvvv");
        L.w("wwwwwww");
        L.e("eeeeeeee");
        L.i("iiiiiiii");
        L.d("dddddddd");

        L.json("{ \"glossary\": { \"title\": \"example glossary\", \"GlossDiv\": { \"title\": \"S\", \"GlossList\": { \"GlossEntry\": { \"ID\": \"SGML\", \"SortAs\": \"SGML\", \"GlossTerm\": \"Standard Generalized Markup Language\", \"Acronym\": \"SGML\", \"Abbrev\": \"ISO 8879:1986\", \"GlossDef\": { \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\", \"GlossSeeAlso\": [ \"GML\", \"XML\" ] }, \"GlossSee\": \"markup\" } } } } }");
    }
}
