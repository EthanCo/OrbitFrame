package com.app.scene;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lib.frame.view.BaseActivity;
import com.lib.frame.viewmodel.BaseViewModel;

/**
 * Test Java Activity
 *
 * @author EthanCo
 * @since 2017/8/29
 */
@Route(path = "/scene/test_java")
public class TestJavaActivity extends BaseActivity {

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    public void initVarAndView(Bundle savedInstanceState) {
        setContentView(R.layout.scene_activity_java_test);
        initToolbar("Test Java", true);
    }
}
