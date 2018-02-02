package com.kusofan.seeweather.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kusofan.seeweather.R;

import java.util.List;

/**
 * Created by heming on 2018/1/29.
 */

public class CityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public CityAdapter(@Nullable List data) {
        super(R.layout.item_city, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String name) {
        helper.setText(R.id.tv_city_name, name)
                .addOnClickListener(R.id.ll_city);
    }
}
