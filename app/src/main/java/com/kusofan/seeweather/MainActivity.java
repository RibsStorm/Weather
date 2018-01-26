package com.kusofan.seeweather;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kusofan.seeweather.base.BaseActivity;
import com.kusofan.seeweather.common.WeatherConstant;
import com.kusofan.seeweather.common.util.CircularAnimUtil;
import com.kusofan.seeweather.common.util.RxUtil;
import com.kusofan.seeweather.component.RxBus;
import com.kusofan.seeweather.module.model.Weather;
import com.kusofan.seeweather.module.net.WeatherRequest;
import com.kusofan.seeweather.module.view.CityActivity;
import com.kusofan.seeweather.module.view.MainFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import butterknife.BindView;
import io.reactivex.Observable;

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

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
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
                refreshlayout -> initData()
        );
        //设置fab
        mFab.setOnClickListener(view -> {
            Intent intent = new Intent(this,CityActivity.class);
            CircularAnimUtil.startActivity(this,intent,mFab,R.color.colorPrimary);
        });
        //TODO...到时候通过定位获取.
        mToolbar.setTitle("上海");
        mCollapsingToolbar.setTitle("上海");
    }

    private Observable<Weather> getWeather(String city) {
        return WeatherRequest.getInstance().getWeather(city)
                .compose(RxUtil.activityLifecycle(this));
    }

    private void initData() {
        getWeather("上海")
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

        if (id == R.id.nav_choose_city) {
            // Handle the camera action
        } else if (id == R.id.nav_more_city) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_about) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
