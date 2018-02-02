package com.kusofan.seeweather.module.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by heming on 2018/1/29.
 */
@DatabaseTable(tableName = "T_Zone")
public class Zone{
    public static final String ZONE_NAME = "ZoneName";
    public static final String CITY_ID = "CityID";
    public static final String ZONE_ID = "ZoneID";
    /**
     * 属性名得写的和表里面一样,首字母大写.否则会抛异常,说找不到这个属性
     */
    //县城/区 名称
    @DatabaseField(columnDefinition = ZONE_NAME)
    private String ZoneName;

    //关联 City表的那个 CitySort
    @DatabaseField(columnDefinition = CITY_ID)
    private String CityID;

    //排序
    @DatabaseField(columnDefinition = ZONE_ID)
    private String ZoneID;

    public Zone() {
    }

    public Zone(String zoneName, String cityID, String zoneID) {
        ZoneName = zoneName;
        CityID = cityID;
        ZoneID = zoneID;
    }

    public String getZoneName() {
        return ZoneName;
    }

    public void setZoneName(String zoneName) {
        ZoneName = zoneName;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getZoneID() {
        return ZoneID;
    }

    public void setZoneID(String zoneID) {
        ZoneID = zoneID;
    }
}
