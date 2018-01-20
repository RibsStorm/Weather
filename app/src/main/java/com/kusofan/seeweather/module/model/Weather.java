package com.kusofan.seeweather.module.model;

import java.util.List;

/**
 * Created by heming on 2018/1/19.
 */

public class Weather extends BaseMode {

    private BaseWeatherModel basic;

    private List<DailyForecastModel> daily_forecast;

    private UpdateModel update;

    public BaseWeatherModel getBasic() {
        return basic;
    }

    public void setBasic(BaseWeatherModel basic) {
        this.basic = basic;
    }

    public List<DailyForecastModel> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecastModel> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public UpdateModel getUpdate() {
        return update;
    }

    public void setUpdate(UpdateModel update) {
        this.update = update;
    }
}
