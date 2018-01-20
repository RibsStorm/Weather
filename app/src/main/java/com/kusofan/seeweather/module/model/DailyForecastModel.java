package com.kusofan.seeweather.module.model;

/**
 * Created by heming on 2018/1/19.
 */

public class DailyForecastModel {
    /**
     * "cond_code_d":"103",
     * "cond_code_n":"101",
     * "cond_txt_d":"晴间多云",
     * "cond_txt_n":"多云",
     * "date":"2017-10-26",
     * "hum":"57",
     * "pcpn":"0.0",
     * "pop":"0",
     * "pres":"1020",
     * "tmp_max":"16",
     * "tmp_min":"8",
     * "uv_index":"3",
     * "vis":"16",
     * "wind_deg":"0",
     * "wind_dir":"无持续风向",
     * "wind_sc":"微风",
     * "wind_spd":"5"
     */
    //	白天天气状况代码
    private String cond_code_d;
    //晚间天气状况代码
    private String cond_code_n;
    //白天天气状况描述
    private String cond_txt_d;
    //晚间天气状况描述
    private String cond_txt_n;
    //预报日期
    private String date;
    //相对湿度
    private String hum;
    //降水量
    private String pcpn;
    //降水概率
    private String pop;
    //大气压强
    private String pres;
    //最高温度
    private String tmp_max;
    //最低温度
    private String tmp_min;
    //紫外线强度指数
    private String uv_index;
    //能见度，单位：公里
    private String vis;
    //风向360角度
    private String wind_deg;
    //风向
    private String wind_dir;
    //风力
    private String wind_sc;
    //风速，公里/小时
    private String wind_spd;

    public DailyForecastModel() {
    }

    /**
     * @param cond_code_d 白天天气状况代码
     * @param cond_code_n 晚间天气状况代码
     * @param cond_txt_d  白天天气状况描述
     * @param cond_txt_n  晚间天气状况描述
     * @param date        预报日期
     * @param hum         相对湿度
     * @param pcpn        降水量
     * @param pop         降水概率
     * @param pres        大气压强
     * @param tmp_max     最高温度
     * @param tmp_min     最低温度
     * @param uv_index    紫外线强度指数
     * @param vis         能见度，单位：公里
     * @param wind_deg    风向360角度
     * @param wind_dir    风向
     * @param wind_sc     风力
     * @param wind_spd    风速，公里/小时
     */
    public DailyForecastModel(String cond_code_d, String cond_code_n, String cond_txt_d, String cond_txt_n, String date, String hum, String pcpn, String pop, String pres, String tmp_max, String tmp_min, String uv_index, String vis, String wind_deg, String wind_dir, String wind_sc, String wind_spd) {
        this.cond_code_d = cond_code_d;
        this.cond_code_n = cond_code_n;
        this.cond_txt_d = cond_txt_d;
        this.cond_txt_n = cond_txt_n;
        this.date = date;
        this.hum = hum;
        this.pcpn = pcpn;
        this.pop = pop;
        this.pres = pres;
        this.tmp_max = tmp_max;
        this.tmp_min = tmp_min;
        this.uv_index = uv_index;
        this.vis = vis;
        this.wind_deg = wind_deg;
        this.wind_dir = wind_dir;
        this.wind_sc = wind_sc;
        this.wind_spd = wind_spd;
    }

    public String getCond_code_d() {
        return cond_code_d;
    }

    public void setCond_code_d(String cond_code_d) {
        this.cond_code_d = cond_code_d;
    }

    public String getCond_code_n() {
        return cond_code_n;
    }

    public void setCond_code_n(String cond_code_n) {
        this.cond_code_n = cond_code_n;
    }

    public String getCond_txt_d() {
        return cond_txt_d;
    }

    public void setCond_txt_d(String cond_txt_d) {
        this.cond_txt_d = cond_txt_d;
    }

    public String getCond_txt_n() {
        return cond_txt_n;
    }

    public void setCond_txt_n(String cond_txt_n) {
        this.cond_txt_n = cond_txt_n;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp_max() {
        return tmp_max;
    }

    public void setTmp_max(String tmp_max) {
        this.tmp_max = tmp_max;
    }

    public String getTmp_min() {
        return tmp_min;
    }

    public void setTmp_min(String tmp_min) {
        this.tmp_min = tmp_min;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
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

    @Override
    public String toString() {
        return "DailyForecastModel{" +
                "cond_code_d='" + cond_code_d + '\'' +
                ", cond_code_n='" + cond_code_n + '\'' +
                ", cond_txt_d='" + cond_txt_d + '\'' +
                ", cond_txt_n='" + cond_txt_n + '\'' +
                ", date='" + date + '\'' +
                ", hum='" + hum + '\'' +
                ", pcpn='" + pcpn + '\'' +
                ", pop='" + pop + '\'' +
                ", pres='" + pres + '\'' +
                ", tmp_max='" + tmp_max + '\'' +
                ", tmp_min='" + tmp_min + '\'' +
                ", uv_index='" + uv_index + '\'' +
                ", vis='" + vis + '\'' +
                ", wind_deg='" + wind_deg + '\'' +
                ", wind_dir='" + wind_dir + '\'' +
                ", wind_sc='" + wind_sc + '\'' +
                ", wind_spd='" + wind_spd + '\'' +
                '}';
    }
}
