package com.kusofan.seeweather.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.module.model.DailyForecastModel;

import java.util.List;

/**
 * Created by heming on 2018/1/23.
 */

public class DailyAdapter extends BaseQuickAdapter<DailyForecastModel, BaseViewHolder> {

    public DailyAdapter(@Nullable List<DailyForecastModel> data) {
        super(R.layout.item_weather_daily, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyForecastModel item) {

    }
}
