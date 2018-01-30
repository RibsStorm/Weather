package com.kusofan.seeweather.module.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by heming on 2018/1/29.
 */
@DatabaseTable(tableName = "T_Province")
public class Province {
    public static final String PROVINCE_NAME = "ProName";
    public static final String PROVINCE_SORT = "ProSort";
    public static final String PROVINCE_REMARK = "ProRemark";
    /**
     * 属性名得写的和表里面一样,首字母大写.否则会抛异常,说找不到这个属性
     */
    //省/直辖市 名称
    @DatabaseField(columnDefinition = PROVINCE_NAME)
    private String ProName;

    //对应id,关联到city表
    @DatabaseField(columnDefinition = PROVINCE_SORT)
    private String ProSort;

    //对应城市类型==>省/直辖市
    @DatabaseField(columnDefinition = PROVINCE_REMARK)
    private String ProRemark;

    public Province() {
    }

    public Province(String proName, String proSort, String proRemark) {
        ProName = proName;
        ProSort = proSort;
        ProRemark = proRemark;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }

    public String getProSort() {
        return ProSort;
    }

    public void setProSort(String proSort) {
        ProSort = proSort;
    }

    public String getProRemark() {
        return ProRemark;
    }

    public void setProRemark(String proRemark) {
        ProRemark = proRemark;
    }
}
