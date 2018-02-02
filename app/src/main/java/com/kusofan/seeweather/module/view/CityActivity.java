package com.kusofan.seeweather.module.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseActivity;
import com.kusofan.seeweather.common.util.RxUtil;
import com.kusofan.seeweather.module.adapter.CityAdapter;
import com.kusofan.seeweather.module.model.db.City;
import com.kusofan.seeweather.module.model.db.CityDao;
import com.kusofan.seeweather.module.model.db.DatabaseHelper;
import com.kusofan.seeweather.module.model.db.Province;
import com.kusofan.seeweather.module.model.db.ProvinceDao;
import com.kusofan.seeweather.module.model.db.Zone;
import com.kusofan.seeweather.module.model.db.ZoneDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by heming on 2018/1/25.
 */

public class CityActivity extends BaseActivity {
    @BindView(R.id.city_recycler)
    RecyclerView mCityRecycler;
    @BindView(R.id.city_toolbar)
    Toolbar mCityToolbar;


    private CityAdapter mCityAdapter;
    //当前显示的城市列表
    private List<String> nowCitys = new ArrayList<>();
    //当前选中的省及市
    private City mCity;
    private Province mProvince;
    //当前请求到的省市
    private List<Province> mProvinces = new ArrayList<>();
    private List<City> mCities = new ArrayList<>();
    private List<Zone> mZones = new ArrayList<>();

    private int currentLever = -1;
    public static final int LEVER_PROVINCE = -1;
    public static final int LEVER_CITY = -3;
    public static final int LEVER_ZONE = -4;


    @Override
    public int setLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCityToolbar.setTitle("城市选择");
        setSupportActionBar(mCityToolbar);
        initView();
        queryProvince();
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
        if (currentLever == LEVER_PROVINCE){
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }else if (currentLever ==LEVER_CITY){
            queryProvince();
        }else if (currentLever == LEVER_ZONE){
            queryCity();
        }
    }


    private void initView() {
        if (mCityAdapter == null) {
            mCityRecycler.setLayoutManager(new LinearLayoutManager(this));
            mCityAdapter = new CityAdapter(nowCitys);
            mCityRecycler.setAdapter(mCityAdapter);
        }

        mCityAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (currentLever == LEVER_PROVINCE) {
                mProvince = mProvinces.get(position);
                queryCity();
            } else if (currentLever == LEVER_CITY) {
                mCity = mCities.get(position);
                queryZone();
            } else if (currentLever == LEVER_ZONE) {
                //TODO...弹窗,将选中的城市添加到  城市列表内
            }
        });
    }

    /**
     * 从数据库查询 一级城市
     */
    private void queryProvince() {
        mCityToolbar.setTitle("选择省份/直辖市");
        ProvinceDao provinceDao = new ProvinceDao(this);
        Flowable.create((FlowableOnSubscribe<String>) emitter -> {
            if (mProvinces.size() == 0) {
                mProvinces = provinceDao.queryAllUser();
            }
            for (Province province : mProvinces) {
                emitter.onNext(province.getProName());
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER)
                .compose(RxUtil.ioF())
                .compose(RxUtil.activityLifecycleF(this))
                .doOnNext(proName -> nowCitys.add(proName))
                .doOnComplete(() -> {
                    mCityAdapter.notifyDataSetChanged();
                    //默认为一级
                    currentLever = LEVER_PROVINCE;
                })
                .subscribe();
    }

    /**
     * 从数据库查询 二级城市
     */
    private void queryCity() {
        mCityToolbar.setTitle("选择城市");
        CityDao cityDao = new CityDao(this);

        Flowable.create((FlowableOnSubscribe<String>) emitter -> {

            mCities = cityDao.queryCityById(mProvince.getProSort());
            for (City city : mCities) {
                emitter.onNext(city.getCityName());
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER)
                .compose(RxUtil.ioF())
                .compose(RxUtil.activityLifecycleF(this))
                .doOnNext(cityName -> nowCitys.add(cityName))
                .doOnComplete(() -> {
                    mCityAdapter.notifyDataSetChanged();
                    //默认为一级
                    currentLever = LEVER_CITY;
                })
                .subscribe();
    }

    /**
     * 从数据库查询 三级城市
     */
    private void queryZone() {
        mCityToolbar.setTitle("选择县城");
        ZoneDao zoneDao = new ZoneDao(this);
        Flowable.create((FlowableOnSubscribe<String>) emitter -> {
            mZones = zoneDao.queryZoneById(mCity.getCitySort());

            for (Zone zone : mZones) {
                emitter.onNext(zone.getZoneName());
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER)
                .compose(RxUtil.ioF())
                .compose(RxUtil.activityLifecycleF(this))
                .doOnNext(zoneName -> nowCitys.add(zoneName))
                .doOnComplete(() -> {
                    mCityAdapter.notifyDataSetChanged();
                    //默认为一级
                    currentLever = LEVER_ZONE;
                })
                .subscribe();
    }

}
