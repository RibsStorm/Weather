package com.kusofan.seeweather.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.module.model.CityModel;

import java.util.List;

/**
 * Created by heming on 2018/1/29.
 */

public class CityAdapter extends BaseQuickAdapter<CityModel, BaseViewHolder> {


    public CityAdapter(@Nullable List<CityModel> data) {
        super(R.layout.item_city,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityModel item) {

    }
}
