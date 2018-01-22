package com.kusofan.seeweather.module.model;

/**
 * Created by heming on 2018/1/22.
 */

public class HourlyModel {

    /**
     * time	预报时间，格式yyyy-MM-dd hh:mm	2013-12-30 13:00
     * tmp	温度	2
     * cond_code	天气状况代码	101
     * cond_txt	天气状况代码	多云
     * wind_deg	风向360角度	290
     * wind_dir	风向	西北
     * wind_sc	风力	3-4
     * wind_spd	风速，公里/小时	15
     * hum	相对湿度	30
     * pres	大气压强	1030
     * dew	露点温度	12
     * cloud	云量	23
     */

    //预报时间
    private String time;
    //温度
    private String tmp;
    //天气状况代码
    private String cond_code;
    //天气状况代码
    private String cond_txt;
    //风向360角度
    private String wind_deg;
    //风向
    private String wind_dir;
    //风力
    private String wind_sc;
    //风速
    private String wind_spd;
    //相对湿度
    private String hum;
    //大气压强
    private String pres;
    //露点温度
    private String dew;
    //云量
    private String cloud;

    public HourlyModel() {
    }

    /**
     * @param time      预报时间
     * @param tmp       温度
     * @param cond_code 天气状况代码
     * @param cond_txt  天气状况代码 多云啥啥啥的
     * @param wind_deg  风向360角度
     * @param wind_dir  风向
     * @param wind_sc   风力
     * @param wind_spd  风速
     * @param hum       相对湿度
     * @param pres      大气压强
     * @param dew       露点温度
     * @param cloud     云量
     */
    public HourlyModel(String time, String tmp, String cond_code, String cond_txt, String wind_deg, String wind_dir, String wind_sc, String wind_spd, String hum, String pres, String dew, String cloud) {
        this.time = time;
        this.tmp = tmp;
        this.cond_code = cond_code;
        this.cond_txt = cond_txt;
        this.wind_deg = wind_deg;
        this.wind_dir = wind_dir;
        this.wind_sc = wind_sc;
        this.wind_spd = wind_spd;
        this.hum = hum;
        this.pres = pres;
        this.dew = dew;
        this.cloud = cloud;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getCond_code() {
        return cond_code;
    }

    public void setCond_code(String cond_code) {
        this.cond_code = cond_code;
    }

    public String getCond_txt() {
        return cond_txt;
    }

    public void setCond_txt(String cond_txt) {
        this.cond_txt = cond_txt;
    }

    public String getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(String wind_deg) {
        this.wind_deg = wind_deg;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getWind_sc() {
        return wind_sc;
    }

    public void setWind_sc(String wind_sc) {
        this.wind_sc = wind_sc;
    }

    public String getWind_spd() {
        return wind_spd;
    }

    public void setWind_spd(String wind_spd) {
        this.wind_spd = wind_spd;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getDew() {
        return dew;
    }

    public void setDew(String dew) {
        this.dew = dew;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }
}
