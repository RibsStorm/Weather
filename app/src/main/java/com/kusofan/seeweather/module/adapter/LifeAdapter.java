package com.kusofan.seeweather.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.common.util.SharedPreferenceUtil;
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
        int icon = SharedPreferenceUtil.getInstance().getInt(item.getType() + "Icon", R.drawable.github);
        String type = SharedPreferenceUtil.getInstance().getString(item.getType(), "暂无推荐");

        helper.setText(R.id.tv_life_type,type)
                .setText(R.id.tv_life_tips,item.getTxt())
                .setBackgroundRes(R.id.iv_life_icon,icon);
    }
}
