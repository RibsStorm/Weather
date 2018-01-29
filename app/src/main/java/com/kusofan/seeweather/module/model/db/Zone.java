package com.kusofan.seeweather.module.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by heming on 2018/1/29.
 */
@DatabaseTable(tableName = "T_Zone")
public class Zone {
    public static final String ZONE_NAME = "ZoneName";
    public static final String CITY_ID = "CityID";
    public static final String ZONE_ID = "ZoneID";
    //县城/区 名称
    @DatabaseField(columnDefinition = ZONE_NAME)
    private String zoneName;

    //关联 City表的那个 CitySort
    @DatabaseField(columnDefinition = CITY_ID)
    private String cityID;

    //排序
    @DatabaseField(generatedId = true,columnDefinition = ZONE_ID)
    private String zoneID;

    public Zone() {
    }

    public Zone(String zoneName, String cityID, String zoneID) {
        this.zoneName = zoneName;
        this.cityID = cityID;
        this.zoneID = zoneID;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getZoneID() {
        return zoneID;
    }

    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }
}
