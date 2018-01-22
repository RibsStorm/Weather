package com.kusofan.seeweather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kusofan.seeweather.base.BaseActivity;
import com.kusofan.seeweather.base.BaseFragment;
import com.kusofan.seeweather.module.adapter.MinePageAdapter;
import com.kusofan.seeweather.module.view.MainFragment;
import com.kusofan.seeweather.module.view.MoreCityFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * TODO...理一下网络请求流程
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private List<BaseFragment> fragments = new ArrayList();
    private List<String> titles = new ArrayList();

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

    }

    private void initView() {
        setSupportActionBar(mToolbar);

        MainFragment mainFragment = new MainFragment();
        MoreCityFragment moreCityFragment = new MoreCityFragment();
        fragments.add(mainFragment);
        fragments.add(moreCityFragment);
        titles.add(getResources().getString(R.string.main_weather));
        titles.add(getResources().getString(R.string.main_more_city));
        MinePageAdapter adapter = new MinePageAdapter(getSupportFragmentManager(), fragments, titles);
        mViewpager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewpager);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mFab.setOnClickListener(view -> {
            //TODO...
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
