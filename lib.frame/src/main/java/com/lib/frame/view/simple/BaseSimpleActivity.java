package com.lib.frame.view.simple;

import com.lib.frame.view.BaseActivity;
import com.lib.frame.view.abs.ICreate;
import com.lib.frame.viewmodel.BaseViewModel;


/**
 * @Description Created by EthanCo on 2017/6/2.
 */
public abstract class BaseSimpleActivity extends BaseActivity implements ICreate {


    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }
}
