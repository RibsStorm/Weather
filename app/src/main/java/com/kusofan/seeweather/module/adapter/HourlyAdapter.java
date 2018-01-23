package com.kusofan.seeweather.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.module.model.HourlyModel;

import java.util.List;


/**
 * Created by heming on 2018/1/23.
 */
public class HourlyAdapter extends BaseQuickAdapter<HourlyModel, BaseViewHolder> {


    public HourlyAdapter(@Nullable List<HourlyModel> data) {
        super(R.layout.item_weather_hourly,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HourlyModel item) {

    }
}
