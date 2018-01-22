package com.kusofan.seeweather.module.model;

/**
 * Created by heming on 2018/1/22.
 */

public class LifeStyleModel {
    /**
     * brf	生活指数简介
     * txt	生活指数详细描述
     * type	生活指数类型 comf：舒适度指数、cw：洗车指数、drsg：穿衣指数、flu：感冒指数、sport：运动指数、trav：旅游指数、uv：紫外线指数、air：空气污染扩散条件指数
     */

    //生活指数简介
    private String brf;
    //生活指数详细描述
    private String txt;
    //type
    private String type;

    public LifeStyleModel() {
    }

    /**
     * @param brf  生活指数简介
     * @param txt  生活指数详细描述
     * @param type 生活指数类型
     */
    public LifeStyleModel(String brf, String txt, String type) {
        this.brf = brf;
        this.txt = txt;
        this.type = type;
    }

    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
