package com.kusofan.seeweather.module.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by heming on 2018/1/29.
 */
@DatabaseTable(tableName = "T_City")
public class City {

    public static final String CITY_NAME = "CityName";
    public static final String PRO_ID = "ProID";
    public static final String CITY_SORT = "CitySort";
    /**
     * 属性名得写的和表里面一样,首字母大写.否则会抛异常,说找不到这个属性
     */
    //城市名称
    @DatabaseField(columnDefinition = CITY_NAME)
    private String CityName;

    //关联 省/直辖市表的 ProSort
    @DatabaseField(columnDefinition = PRO_ID)
    private String ProID;

    //对应id,关联到 Zone表
    @DatabaseField(columnDefinition = CITY_SORT)
    private String CitySort;

    public City() {
    }

    public City(String cityName, String proID, String citySort) {
        CityName = cityName;
        ProID = proID;
        CitySort = citySort;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getProID() {
        return ProID;
    }

    public void setProID(String proID) {
        ProID = proID;
    }

    public String getCitySort() {
        return CitySort;
    }

    public void setCitySort(String citySort) {
        CitySort = citySort;
    }
}
