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

    //城市名称
    @DatabaseField(columnDefinition = CITY_NAME)
    private String cityName;

    //关联 省/直辖市表的 ProSort
    @DatabaseField(columnDefinition = PRO_ID)
    private String proID;

    //对应id,关联到 Zone表
    @DatabaseField(generatedId = true, columnDefinition = CITY_SORT)
    private String citySort;

    public City() {
    }

    public City(String cityName, String proID, String citySort) {
        this.cityName = cityName;
        this.proID = proID;
        this.citySort = citySort;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getCitySort() {
        return citySort;
    }

    public void setCitySort(String citySort) {
        this.citySort = citySort;
    }
}
