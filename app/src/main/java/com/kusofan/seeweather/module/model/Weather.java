package com.kusofan.seeweather.module.model;

import java.util.List;

/**
 * Created by heming on 2018/1/19.
 */

public class Weather extends BaseMode {

    private BasicWeatherModel basic;

    private List<DailyForecastModel> daily_forecast;

    private UpdateModel update;

    private NowModel now;

    private List<HourlyModel> hourly;

    private List<LifeStyleModel> lifestyle;

    public Weather() {
    }

    public Weather(BasicWeatherModel basic, List<DailyForecastModel> daily_forecast, UpdateModel update, NowModel now, List<HourlyModel> hourly, List<LifeStyleModel> lifestyle) {
        this.basic = basic;
        this.daily_forecast = daily_forecast;
        this.update = update;
        this.now = now;
        this.hourly = hourly;
        this.lifestyle = lifestyle;
    }

    public BasicWeatherModel getBasic() {
        return basic;
    }

    public void setBasic(BasicWeatherModel basic) {
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

    public NowModel getNow() {
        return now;
    }

    public void setNow(NowModel now) {
        this.now = now;
    }

    public List<HourlyModel> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyModel> hourly) {
        this.hourly = hourly;
    }

    public List<LifeStyleModel> getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(List<LifeStyleModel> lifestyle) {
        this.lifestyle = lifestyle;
    }
}
