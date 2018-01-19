package com.kusofan.seeweather.module.model;

/**
 * Created by heming on 2018/1/19.
 */

public class UpdateModel {
    /**
     * "loc":"2017-10-26 23:09",
     * "utc":"2017-10-26 15:09"
     */
    //当地时间，24小时制，格式yyyy-MM-dd HH:mm
    private String loc;
    //UTC时间，24小时制，格式yyyy-MM-dd HH:mm
    private String utc;

    public UpdateModel() {
    }

    /**
     * @param loc 当地时间，24小时制，格式yyyy-MM-dd HH:mm
     * @param utc UTC时间，24小时制，格式yyyy-MM-dd HH:mm
     */
    public UpdateModel(String loc, String utc) {
        this.loc = loc;
        this.utc = utc;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}
