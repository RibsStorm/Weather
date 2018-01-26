package com.kusofan.seeweather.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.common.util.LogUtil;
import com.kusofan.seeweather.common.util.SharedPreferenceUtil;
import com.kusofan.seeweather.common.util.TimeUitl;
import com.kusofan.seeweather.module.model.DailyForecastModel;

import java.util.List;

/**
 * Created by heming on 2018/1/23.
 */

public class DailyAdapter extends BaseQuickAdapter<DailyForecastModel, BaseViewHolder> {

    public DailyAdapter(@Nullable List<DailyForecastModel> data) {
        super(R.layout.item_daily, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyForecastModel item) {

        int dayIcon = SharedPreferenceUtil.getInstance().getInt(item.getCond_txt_d(), R.drawable.none);
        int nightIcon = SharedPreferenceUtil.getInstance().getInt(item.getCond_txt_n(), R.drawable.none);

        helper.setBackgroundRes(R.id.iv_daily_icon_d, dayIcon)
                .setBackgroundRes(R.id.iv_daily_icon_n, nightIcon)
                .setText(R.id.tv_daily_temp, mContext.getResources().getString(R.string.main_daily_temp, item.getTmp_min(), item.getTmp_max()))
                .setText(R.id.tv_daily_detail, item.getCond_txt_d() + "." + item.getWind_dir() + "," + "相对湿度:" + item.getHum());

        int pos = helper.getAdapterPosition();
        if (pos == 0) {
            helper.setText(R.id.tv_daily_day, "今日");
        } else if (pos == 1) {
            helper.setText(R.id.tv_daily_day, "明日");
        } else if (pos > 1) {
            try {
                helper.setText(R.id.tv_daily_day, TimeUitl.dayForWeek(item.getDate()));
            } catch (Exception e) {
                LogUtil.e(e.toString());
            }
        }
    }


}
