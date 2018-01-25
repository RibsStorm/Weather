package com.kusofan.seeweather.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.module.model.Weather;

import butterknife.BindView;

/**
 * Created by heming on 2018/1/25.
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private Weather mWeather;
    public static final int TYPE_LIFE = 0;
    public static final int TYPE_HORULY = 1;
    public static final int TYPE_DAILY = 2;

    public WeatherAdapter(Context context, Weather weather) {
        mContext = context;
        mWeather = weather;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_LIFE) {
            return WeatherAdapter.TYPE_LIFE;
        }
        if (position == TYPE_HORULY) {
            return WeatherAdapter.TYPE_HORULY;
        }
        if (position == TYPE_DAILY) {
            return WeatherAdapter.TYPE_DAILY;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_LIFE:
                return new LifeViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_weather_life, parent, false));
            case TYPE_HORULY:
                return new HourlyViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_weather_hourly, parent, false));
            case TYPE_DAILY:
                return new DailyViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_weather_daily, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_LIFE:
                ((LifeViewHolder) holder).bind(mWeather);
                break;

            case TYPE_HORULY:
                ((HourlyViewHolder) holder).bind(mWeather);
                break;

            case TYPE_DAILY:
                ((DailyViewHolder) holder).bind(mWeather);
                break;
        }
    }

    @Override
    public int getItemCount() {
        int temp = 0;
        if (mWeather.getLifestyle() != null && mWeather.getLifestyle().size() > 0) {
            temp++;
        }
        if (mWeather.getHourly() != null && mWeather.getHourly().size() > 0) {
            temp++;
        }
        if (mWeather.getDaily_forecast() != null && mWeather.getDaily_forecast().size() > 0) {
            temp++;
        }
        return temp;
    }

    class LifeViewHolder extends BaseViewHolder<Weather> {
        @BindView(R.id.ll_life_container)
        LinearLayout mLlLifeContainer;

        @BindView(R.id.iv_life_icon)
        ImageView mIvLifeIcon;
        @BindView(R.id.tv_life_type)
        TextView mTvLifeType;
        @BindView(R.id.tv_life_tips)
        TextView mTvLifeTips;

        public LifeViewHolder(View itemView) {
            super(itemView);
//            LinearLayout llLife = itemView.findViewById(R.id.ll_life_container);
            for (int i = 0; i < mWeather.getLifestyle().size(); i++) {
                View view = View.inflate(mContext, R.layout.item_life, null);

                mLlLifeContainer.addView(view);
            }
        }

        @Override
        protected void bind(Weather weather) {

        }
    }

    class HourlyViewHolder extends BaseViewHolder<Weather> {

        public HourlyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(Weather weather) {

        }
    }

    class DailyViewHolder extends BaseViewHolder<Weather> {

        public DailyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(Weather weather) {

        }
    }
}
