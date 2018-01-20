package com.kusofan.seeweather.module.api;

import com.kusofan.seeweather.module.model.Weather;
import com.kusofan.seeweather.module.model.WeatherAPI;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by heming on 2018/1/19.
 */

public interface WeatherInterface {

    @GET("/s6/weather/forecast")
    Observable<WeatherAPI> mWeatherAPI(@Query("location") String location,
                                       @Query("key") String key);
}
