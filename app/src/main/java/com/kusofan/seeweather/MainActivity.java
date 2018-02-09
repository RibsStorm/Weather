package com.kusofan.seeweather;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.kusofan.seeweather.base.BaseActivity;
import com.kusofan.seeweather.common.WeatherConstant;
import com.kusofan.seeweather.common.util.CircularAnimUtil;
import com.kusofan.seeweather.common.util.RxUtil;
import com.kusofan.seeweather.common.util.SharedPreferenceUtil;
import com.kusofan.seeweather.common.util.ToastUtil;
import com.kusofan.seeweather.component.RxBus;
import com.kusofan.seeweather.module.model.Weather;
import com.kusofan.seeweather.module.net.WeatherRequest;
import com.kusofan.seeweather.module.view.CityActivity;
import com.kusofan.seeweather.module.view.MainFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * TODO...理一下网络请求流程
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tv_wea_time)
    TextView mTvWeaTime;
    @BindView(R.id.tv_wea_temp)
    TextView mTvWeaTemp;
    @BindView(R.id.tv_wea_tv)
    TextView mTvWeaTv;
    @BindView(R.id.iv_wea_icon)
    ImageView mIvWeaIcon;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private String city;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;

    @Override
    public int setLayoutId() {
        //只有需要透明状态栏才设置这个,如果是纯色状态栏也设置的话,就会有一条黑线
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPermission();
        initView();
        RxBus.getDefault().toObservable(String.class)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(city -> {
                    this.city = city;
                    mCollapsingToolbar.setTitle(city);
                    initData(city);
                    SharedPreferenceUtil.getInstance().setCityName(city);
                }).subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlocationClient.onDestroy();
    }

    private void initPermission() {
        new RxPermissions(this)
                .requestEach(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.name.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        if (!permission.granted) {
                            ToastUtil.shortToast("获取定位权限失败,自动定位功能将失效,无法自动定位,选择为默认城市");
                            city = "上海";
                            initData(city);
                        } else {
                            initLocation();
                        }
                    } else if (permission.name.equals(Manifest.permission.READ_PHONE_STATE)) {
                        if (!permission.granted) {
                            ToastUtil.shortToast("请前往权限管理内打开读取手机信息权限,该权限用于获取手机基础信息");
                        }
                    } else if (permission.name.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        if (!permission.granted) {
                            ToastUtil.shortToast("请前往权限管理内打开读写文件权限,该权限用于存储错误信息以便反馈.");
                        }
                    }
                });
    }


    private void initView() {
        setSupportActionBar(mToolbar);
        //模板代码,设置侧边栏
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        //设置底部Fragment
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, mainFragment);
            transaction.commit();
        }
        //设置下拉刷新头部
        ClassicsHeader header = new ClassicsHeader(this);
        header.setPrimaryColors(this.getResources().getColor(R.color.colorPrimary), Color.WHITE);
        mRefreshLayout.setRefreshHeader(header);
        //下拉刷新时,请求initData()方法
        mRefreshLayout.setOnRefreshListener(
                refreshlayout -> initData(city)
        );
        //设置fab
        mFab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CityActivity.class);
            CircularAnimUtil.startActivity(this, intent, mFab, R.color.colorPrimary);
        });
    }

    private Observable<Weather> getWeather(String city) {
        return WeatherRequest.getInstance().getWeather(city)
                .compose(RxUtil.activityLifecycle(this));
    }

    private void initData(String city) {
        mCollapsingToolbar.setTitle(city);
        getWeather(city)
                .doOnNext(weather -> {
                    initAppbarForWeather(weather);
                    RxBus.getDefault().post(weather);
                })
                .doOnComplete(() -> {
                    mRefreshLayout.finishRefresh();
                })
                .subscribe();
    }

    //设置主页面上半部分数据
    private void initAppbarForWeather(Weather weather) {
        mTvWeaTemp.setText(getResources().getString(R.string.main_now_temp, weather.getNow().getTmp()));
        mTvWeaTime.setText(getResources().getString(R.string.main_now_time, weather.getUpdate().getLoc()));
        mTvWeaTv.setText(weather.getNow().getCond_txt());
        int weatherIcon = WeatherConstant.getInstance().weaIcons.get(weather.getNow().getCond_txt());
        mIvWeaIcon.setBackground(getResources().getDrawable(weatherIcon));
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_more_city) {
            ToastUtil.shortToast("到时候做完,添加多城市选择功能.");
        } else if (id == R.id.nav_setting) {
            ToastUtil.shortToast("没做完,以后再说.");
        } else if (id == R.id.nav_about) {
            Uri uri = Uri.parse("https://github.com/LaLaTiao/Weather");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 高德定位
     */
    private void initLocation() {
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //仅定位一次,切获取3秒内精度最高的一次.
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);

        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        //设置定位监听
        mlocationClient.setLocationListener(aMapLocation -> {
            if (aMapLocation != null) {
                String city = aMapLocation.getCity();
                if (TextUtils.isEmpty(city)) {
                    ToastUtil.shortToast("获取城市定位失败," + aMapLocation.getErrorInfo());
                    this.city = "上海市";
                } else {
                    this.city = city;
                    ToastUtil.shortToast("获得定位城市:" + city);
                }
                initData(this.city);
            } else {
                ToastUtil.shortToast("获取定位城市失败,自动设置为默认城市");
            }
        });
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }
}
