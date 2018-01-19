package com.kusofan.seeweather.component;

import com.kusofan.seeweather.common.Const;
import com.kusofan.seeweather.module.api.WeatherInterface;

/**
 * Created by heming on 2018/1/19.
 */

public class RequestManager {
    /**
     * public <R> R getService(Class<R> c) {
     * return this.getService(this.mDefaultBaseUrl, c);
     * }
     * <p>
     * public <R> R getService(String endPoint, Class<R> c) {
     * return this.getRetrofit(endPoint).create(c);
     * }
     *
     * @param city
     */
    private void getWeather(String city) {
        RetrofitManager.getInstance().getService(WeatherInterface.class).mWeatherAPI(city, Const.KEY_WEATHER);

    }
}
