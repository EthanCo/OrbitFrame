package com.ethanco.simpleframesample.model;

import com.ethanco.simpleframesample.bean.Weather;

import rx.Observable;

/**
 * Created by EthanCo on 2016/4/4.
 */
public interface IWeatherModel {
    Observable<Weather> getWeather(String cityname);
}
