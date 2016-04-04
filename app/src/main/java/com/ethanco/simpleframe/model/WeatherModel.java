package com.ethanco.simpleframe.model;


import com.ethanco.simpleframe.Constants;
import com.ethanco.simpleframe.bean.Weather;
import com.ethanco.simpleframe.frame.rxjava.net.RetrofitFactory;
import com.ethanco.simpleframe.model.service.ApiService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EthanCo on 2016/4/4.
 */
public class WeatherModel implements IWeatherModel {
    @Override
    public Observable<Weather> getWeather(String cityname) {
        return RetrofitFactory.createSINARetrofit().create(ApiService.class)
                .loadWeather(cityname, Constants.JUHE_WEATHER_APIKEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
