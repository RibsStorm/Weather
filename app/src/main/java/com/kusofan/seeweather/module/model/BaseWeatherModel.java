package com.kusofan.seeweather.module.model;

/**
 * Created by heming on 2018/1/19.
 */


public class BaseWeatherModel {
    /**
     * "cid":"CN101010100",
     * "location":"北京",
     * "parent_city":"北京",
     * "admin_area":"北京",
     * "cnty":"中国",
     * "lat":"39.90498734",
     * "lon":"116.40528870",
     * "tz":"8.0"
     */
    //地区／城市ID
    private String cid;
    //地区／城市名称
    private String location;
    //该地区／城市的上级城市
    private String parent_city;
    //该地区／城市所属行政区域
    private String admin_area;
    //该地区／城市所属国家名称
    private String cnty;
    //地区／城市经度
    private String lat;
    //地区／城市纬度
    private String lon;
    //该地区／城市所在时区
    private String tz;

    public BaseWeatherModel() {
    }

    /**
     * @param cid         地区／城市ID
     * @param location    地区／城市名称
     * @param parent_city 该地区／城市的上级城市
     * @param admin_area  该地区／城市所属行政区域
     * @param cnty        该地区／城市所属国家名称
     * @param lat         地区／城市经度
     * @param lon         地区／城市纬度
     * @param tz          该地区／城市所在时区
     */
    public BaseWeatherModel(String cid, String location, String parent_city, String admin_area, String cnty, String lat, String lon, String tz) {
        this.cid = cid;
        this.location = location;
        this.parent_city = parent_city;
        this.admin_area = admin_area;
        this.cnty = cnty;
        this.lat = lat;
        this.lon = lon;
        this.tz = tz;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParent_city() {
        return parent_city;
    }

    public void setParent_city(String parent_city) {
        this.parent_city = parent_city;
    }

    public String getAdmin_area() {
        return admin_area;
    }

    public void setAdmin_area(String admin_area) {
        this.admin_area = admin_area;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }
}
