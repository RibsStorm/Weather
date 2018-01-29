package com.kusofan.seeweather.module.model.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by heming on 2018/1/29.
 */

public class CityDao {
    private Context context;
    private Dao<City, Integer> mCityDao;
    private DatabaseHelper helper;

    public CityDao(Context contex) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(contex);
            mCityDao = helper.getDao(City.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<City> queryCityById(String proID) throws SQLException {
        QueryBuilder<City, Integer> queryBuilder = mCityDao.queryBuilder();
        queryBuilder.where().eq(City.PRO_ID, proID);

        return queryBuilder.query();
    }
}
