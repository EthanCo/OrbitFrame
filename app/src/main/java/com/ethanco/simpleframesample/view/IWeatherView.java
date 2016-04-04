package com.ethanco.simpleframesample.view;

import com.ethanco.simpleframesample.bean.Weather;

/**
 * Created by EthanCo on 2016/4/4.
 */
public interface IWeatherView extends IWaitDialogOper {
    void loadWeatherSuccess(Weather weather);

    void loadWeatherFaild(String localizedMessage);

}
