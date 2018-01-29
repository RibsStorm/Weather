package com.kusofan.seeweather.module.model.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by heming on 2018/1/29.
 */

public class ZoneDao {
    private Context context;
    private Dao<Zone, Integer> mZoneDao;
    private DatabaseHelper helper;

    public ZoneDao(Context contex) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(contex);
            mZoneDao = helper.getDao(Zone.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Zone> queryZoneById(String cityID) throws SQLException {
        QueryBuilder<Zone, Integer> queryBuilder = mZoneDao.queryBuilder();
        queryBuilder.where().eq(Zone.CITY_ID, cityID);

        return queryBuilder.query();
    }
}
