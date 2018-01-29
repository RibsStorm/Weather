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
    //省/直辖市 名称
    @DatabaseField(generatedId = true, columnDefinition = PROVINCE_NAME)
    private String proName;

    //对应id,关联到city表
    @DatabaseField(columnDefinition = PROVINCE_SORT)
    private String proSort;

    //对应城市类型==>省/直辖市
    @DatabaseField(columnDefinition = PROVINCE_REMARK)
    private String proRemark;

    public Province() {
    }

    public Province(String proName, String proSort, String proRemark) {
        this.proName = proName;
        this.proSort = proSort;
        this.proRemark = proRemark;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProSort() {
        return proSort;
    }

    public void setProSort(String proSort) {
        this.proSort = proSort;
    }

    public String getProRemark() {
        return proRemark;
    }

    public void setProRemark(String proRemark) {
        this.proRemark = proRemark;
    }
}
