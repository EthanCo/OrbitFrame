package com.bocai.boc_tongxing.simpleframesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ethanco.simpleframe.utils.M;
import com.ethanco.simpleframe.utils.log.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CrashReport.testJavaCrash(); CrashReport测试

        Log.i("z-tag", "zhk-" + BuildConfig.DEBUG);
        Log.i("z-tag", "zhk-" + "MyApplication.IS_DEBUG:" + MyApplication.IS_DEBUG);
        M.l();
        L.i("sdlfjsdlfjsdflksdjlfk");
        L.i("z-sssssss", "dfdfsdfsadfsadfsad");
        L.json("{ \"glossary\": { \"title\": \"example glossary\", \"GlossDiv\": { \"title\": \"S\", \"GlossList\": { \"GlossEntry\": { \"ID\": \"SGML\", \"SortAs\": \"SGML\", \"GlossTerm\": \"Standard Generalized Markup Language\", \"Acronym\": \"SGML\", \"Abbrev\": \"ISO 8879:1986\", \"GlossDef\": { \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\", \"GlossSeeAlso\": [ \"GML\", \"XML\" ] }, \"GlossSee\": \"markup\" } } } } }");
        L.xml("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Root>\n" +
                "  <user id=\"001\">\n" +
                "    <admin>\n" +
                "      <name>fred</name>\n" +
                "      <password>150150</password>\n" +
                "      <age>22</age>\n" +
                "    </admin>\n" +
                "    <admin>\n" +
                "      <name>yucai</name>\n" +
                "      <password>123456</password>\n" +
                "      <age>21</age>\n" +
                "    </admin>    \n" +
                "  </user> \n" +
                "</Root>");

        Log.i("z-tag", "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Root>\n" +
                "  <user id=\"001\">\n" +
                "    <admin>\n" +
                "      <name>fred</name>\n" +
                "      <password>150150</password>\n" +
                "      <age>22</age>\n" +
                "    </admin>\n" +
                "    <admin>\n" +
                "      <name>yucai</name>\n" +
                "      <password>123456</password>\n" +
                "      <age>21</age>\n" +
                "    </admin>    \n" +
                "  </user> \n" +
                "</Root>");
    }
}
