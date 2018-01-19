package com.kusofan.seeweather.module.model;

import java.util.List;

/**
 * Created by heming on 2018/1/19.
 */

public class Weather extends BaseMode {

    private BaseWeatherModel basic;

    private List<DailyForecastModel> daily_forecast;

    private UpdateModel update;
}
