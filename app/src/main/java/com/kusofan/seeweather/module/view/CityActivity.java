package com.kusofan.seeweather.module.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by heming on 2018/1/25.
 */

public class CityActivity extends BaseActivity {
    @BindView(R.id.city_toolbar)
    Toolbar mCityToolbar;
    @BindView(R.id.city_recycler)
    RecyclerView mCityRecycler;

    @Override
    public int setLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCityToolbar.setTitle("城市选择");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
