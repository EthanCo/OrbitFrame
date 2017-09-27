package com.lib.frame.view.simple;

import com.lib.frame.view.BaseFragmentActivity;
import com.lib.frame.viewmodel.BaseViewModel;

/**
 * @author EthanCo
 * @since 2017/6/19
 */

public abstract class BaseSimpleFragmentActivity extends BaseFragmentActivity {
    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }
}
