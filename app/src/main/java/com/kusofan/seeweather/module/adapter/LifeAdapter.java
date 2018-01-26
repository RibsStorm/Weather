package com.kusofan.seeweather.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.common.WeatherConstant;
import com.kusofan.seeweather.module.model.LifeStyleModel;

import java.util.List;

/**
 * Created by heming on 2018/1/23.
 */

public class LifeAdapter extends BaseQuickAdapter<LifeStyleModel, BaseViewHolder> {

    public LifeAdapter(@Nullable List<LifeStyleModel> data) {
        super(R.layout.item_life, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LifeStyleModel item) {
        int icon = WeatherConstant.getInstance().lifeIcons.get(item.getType());
        String type = WeatherConstant.getInstance().lifeStyles.get(item.getType());

        helper.setText(R.id.tv_life_type, type)
                .setText(R.id.tv_life_tips, item.getTxt())
                .setBackgroundRes(R.id.iv_life_icon, icon);
    }
}
