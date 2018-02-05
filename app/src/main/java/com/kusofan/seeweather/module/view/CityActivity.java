package com.kusofan.seeweather.module.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseActivity;
import com.kusofan.seeweather.common.util.RxUtil;
import com.kusofan.seeweather.component.RxBus;
import com.kusofan.seeweather.module.adapter.CityAdapter;
import com.kusofan.seeweather.module.model.db.City;
import com.kusofan.seeweather.module.model.db.CityDao;
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
    public static final int LEVER_CITY = -2;
    public static final int LEVER_ZONE = -3;


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
    public void onBackPressed() {
        if (currentLever == LEVER_PROVINCE) {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (currentLever == LEVER_CITY) {

            currentLever = LEVER_PROVINCE;
            nowCitys.clear();
            for (Province province : mProvinces) {
                nowCitys.add(province.getProName());
            }
            mCityAdapter.notifyDataSetChanged();
            mCityRecycler.smoothScrollToPosition(0);
        } else if (currentLever == LEVER_ZONE) {

            currentLever = LEVER_CITY;
            nowCitys.clear();
            for (City city : mCities) {
                nowCitys.add(city.getCityName());
            }
            mCityAdapter.notifyDataSetChanged();
            mCityRecycler.smoothScrollToPosition(0);
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

                if (mCity.getCityName().contains("香港") || mCity.getCityName().contains("澳门")) {

                    new AlertDialog.Builder(this)
                            .setMessage("是否确定添加该城市?")
                            .setNegativeButton("取消", (dialog, i) -> {

                            }).setPositiveButton("确定", (dialog, i) -> {
                        RxBus.getDefault().post(mCity.getCityName());
                        CityActivity.this.finish();
                    }).create().show();

                }
                queryZone();
            } else if (currentLever == LEVER_ZONE) {

                new AlertDialog.Builder(this)
                        .setMessage("是否确定添加该城市?")
                        .setNegativeButton("取消", (dialog, i) -> {

                        }).setPositiveButton("确定", (dialog, i) -> {
                    RxBus.getDefault().post(nowCitys.get(position));
                    CityActivity.this.finish();
                }).create().show();
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
        }, BackpressureStrategy.LATEST)
                .compose(RxUtil.ioF())
                .compose(RxUtil.activityLifecycleF(this))
                .doOnNext(proName -> nowCitys.add(proName))
                .doOnComplete(() -> {
                    mCityAdapter.notifyDataSetChanged();
                    mCityRecycler.smoothScrollToPosition(0);
                    //默认为一级
                    currentLever = LEVER_PROVINCE;
                })
                .subscribe();
    }

    /**
     * 从数据库查询 二级城市
     */
    private void queryCity() {
        nowCitys.clear();
        mCityToolbar.setTitle("选择城市");
        CityDao cityDao = new CityDao(this);

        Flowable.create((FlowableOnSubscribe<String>) emitter -> {

            mCities = cityDao.queryCityById(mProvince.getProSort());
            for (City city : mCities) {
                emitter.onNext(city.getCityName());
            }
            emitter.onComplete();
        }, BackpressureStrategy.LATEST)
                .compose(RxUtil.ioF())
                .compose(RxUtil.activityLifecycleF(this))
                .doOnNext(cityName -> nowCitys.add(cityName))
                .doOnComplete(() -> {
                    mCityAdapter.notifyDataSetChanged();
                    mCityRecycler.smoothScrollToPosition(0);
                    currentLever = LEVER_CITY;
                })
                .subscribe();
    }

    /**
     * 从数据库查询 三级城市
     */
    private void queryZone() {
        nowCitys.clear();
        mCityToolbar.setTitle("选择区/县");
        ZoneDao zoneDao = new ZoneDao(this);
        Flowable.create((FlowableOnSubscribe<String>) emitter -> {
            mZones = zoneDao.queryZoneById(mCity.getCitySort());
            for (Zone zone : mZones) {
                emitter.onNext(zone.getZoneName());
            }
            emitter.onComplete();
        }, BackpressureStrategy.LATEST)
                .compose(RxUtil.ioF())
                .compose(RxUtil.activityLifecycleF(this))
                .doOnNext(zoneName -> nowCitys.add(zoneName))
                .doOnComplete(() -> {
                    //避免 香港/澳门 这种没有 三级选项的进来
                    if (nowCitys.size() != 0) {
                        mCityAdapter.notifyDataSetChanged();
                        mCityRecycler.smoothScrollToPosition(0);
                        currentLever = LEVER_ZONE;
                    }
                })
                .subscribe();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //这数据库太坑爹, 不好3表一起查, 不做动态搜索
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.menu_search) {
//            SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
