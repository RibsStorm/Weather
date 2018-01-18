package com.kusofan.seeweather;

import android.os.Bundle;

import com.kusofan.seeweather.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

}
