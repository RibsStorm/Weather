package com.kusofan.seeweather.module.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.j256.ormlite.dao.Dao;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseActivity;
import com.kusofan.seeweather.common.util.ToastUtil;
import com.kusofan.seeweather.module.model.db.City;
import com.kusofan.seeweather.module.model.db.DatabaseHelper;
import com.kusofan.seeweather.module.model.db.Province;
import com.kusofan.seeweather.module.model.db.Zone;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by heming on 2018/1/25.
 */

public class CityActivity extends BaseActivity {
    List<Province> provinces = new ArrayList<>();
    List<City> citys = new ArrayList<>();
    List<Zone> zones = new ArrayList<>();

    @BindView(R.id.city_recycler)
    RecyclerView mCityRecycler;
    @BindView(R.id.city_toolbar)
    Toolbar mCityToolbar;

    @Override
    public int setLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCityToolbar.setTitle("城市选择");
        setSupportActionBar(mCityToolbar);
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_search) {
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
            //TODO...
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseHelper.getHelper(this).close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    private void initData() {
        Dao<Province, ?> provinceDao = DatabaseHelper.getHelper(this).getCityDao(Province.class);
        try {
            provinces = provinceDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ToastUtil.shortToast(provinces.toString());
    }
}
