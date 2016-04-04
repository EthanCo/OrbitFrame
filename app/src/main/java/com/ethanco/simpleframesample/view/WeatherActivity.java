package com.ethanco.simpleframesample.view;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.ethanco.simpleframe.view.BaseActivity;
import com.ethanco.simpleframesample.R;
import com.ethanco.simpleframesample.bean.User;
import com.ethanco.simpleframesample.bean.Weather;
import com.ethanco.simpleframesample.databinding.ActivityWeatherBinding;
import com.ethanco.simpleframesample.view.adapter.WeatherAdapter;
import com.ethanco.simpleframesample.viewmodel.WeatherViewModel;

/**
 * Created by EthanCo on 2016/4/2.
 */
public class WeatherActivity extends BaseActivity implements IWeatherView {
    public static final String EXTRA_USER = "EXTRA_USER";

    private ActivityWeatherBinding binding;
    private ProgressDialog waitDialog;
    private WeatherViewModel weatherViewModel;
    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);

        User user = (User) getIntent().getExtras().getSerializable(EXTRA_USER);
        binding.setUser(user);
        weatherViewModel = new WeatherViewModel(this);
        weatherViewModel.loadWeather("杭州");
    }

    @Override
    public void loadWeatherSuccess(Weather weather) {
        Toast.makeText(getApplication(), "获取天气成功:" + weather.getResult().getData().getWeather().get(0).getDate(), Toast.LENGTH_SHORT).show();
        weatherAdapter = new WeatherAdapter(weather.getResult().getData().getWeather());
        binding.listWeather.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.listWeather.setAdapter(weatherAdapter);
    }

    @Override
    public void loadWeatherFaild(String localizedMessage) {
        Toast.makeText(getApplication(), "获取天气失败:" + localizedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWaitDailog() {
        dismissDialog();
        waitDialog = ProgressDialog.show(this, "正在登陆", "请稍等...");
        waitDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (null != waitDialog && waitDialog.isShowing()) {
            waitDialog.dismiss();
        }
    }
}
