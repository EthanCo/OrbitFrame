package com.ethanco.simpleframesample.viewmodel;

import com.ethanco.simpleframesample.model.IWeatherModel;
import com.ethanco.simpleframesample.model.WeatherModel;
import com.ethanco.simpleframesample.view.IWeatherView;

/**
 * Created by EthanCo on 2016/4/4.
 */
public class WeatherViewModel {
    private IWeatherModel model;
    private IWeatherView view;

    public WeatherViewModel(IWeatherView view) {
        this.view = view;
        this.model = new WeatherModel();
    }

    public void loadWeather(String cityname) {
        view.showWaitDailog();
        model.getWeather(cityname).subscribe(weather -> view.loadWeatherSuccess(weather),
                throwable -> {
                    view.dismissDialog();
                    view.loadWeatherFaild(throwable.getLocalizedMessage());
                },
                () -> view.dismissDialog());
    }
}
