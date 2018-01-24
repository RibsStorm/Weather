package com.kusofan.seeweather.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.module.model.LifeStyleModel;

import java.util.List;

/**
 * Created by heming on 2018/1/23.
 */

public class LifeAdapter extends BaseQuickAdapter<LifeStyleModel, BaseViewHolder> {

    public LifeAdapter(@Nullable List<LifeStyleModel> data) {
        super(R.layout.item_weather_life, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LifeStyleModel item) {

    }
}
