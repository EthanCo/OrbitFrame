package cn.nbhope.smarthome;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lib.frame.view.BaseActivity;
import com.lib.frame.viewmodel.BaseViewModel;

import cn.nbhope.smarthome.databinding.ActivitySecondBinding;

/**
 * Second Activity
 *
 * @author EthanCo
 * @since 2017/8/29
 */
@Route(path = "/app/second")
public class SecondActivity extends BaseActivity {

    private ActivitySecondBinding binding;

    @Autowired
    String info;

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    public void initVarAndView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        initToolbar("Second", true);
        if (!TextUtils.isEmpty(info)) {
            binding.tvInfo.setText(info);
        }
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }
}
