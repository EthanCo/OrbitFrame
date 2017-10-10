package com.lib.frame.view.simple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.frame.view.BaseChangeFragment;
import com.lib.frame.view.abs.ICreate;
import com.lib.frame.viewmodel.BaseViewModel;

/**
 * @Description Fragment 基类，用作给Kotlin继承用
 * Created by EthanCo on 2017/6/2.
 */
public abstract class BaseSimpleFragment extends BaseChangeFragment implements ICreate {
    @Override
    public View initVarAndView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }
}
