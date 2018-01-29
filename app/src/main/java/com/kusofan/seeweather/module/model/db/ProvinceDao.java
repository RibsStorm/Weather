package com.kusofan.seeweather.module.model.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heming on 2018/1/29.
 */

public class ProvinceDao {

    private Context context;
    private Dao<Province, Integer> mProvinceDao;
    private DatabaseHelper helper;

    public ProvinceDao(Context contex) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(contex);
            mProvinceDao = helper.getDao(Province.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查
     *
     * @return
     */
    public List<Province> queryAllUser() {
        ArrayList<Province> users = null;
        try {
            users = (ArrayList<Province>) mProvinceDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 获取Province
     *
     * @param id user编号
     * @return
     */
//    public Province getUser(Integer id) {
//        try {
//            return mProvinceDao.queryForId(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
