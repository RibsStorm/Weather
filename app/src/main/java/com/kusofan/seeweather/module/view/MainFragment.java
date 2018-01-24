package com.kusofan.seeweather.module.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseFragment;
import com.kusofan.seeweather.common.util.LogUtil;
import com.kusofan.seeweather.common.util.RxUtil;
import com.kusofan.seeweather.common.util.SharedPreferenceUtil;
import com.kusofan.seeweather.module.adapter.DailyAdapter;
import com.kusofan.seeweather.module.adapter.HourlyAdapter;
import com.kusofan.seeweather.module.adapter.LifeAdapter;
import com.kusofan.seeweather.module.model.Weather;
import com.kusofan.seeweather.module.net.WeatherRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by heming on 2018/1/22.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    Unbinder unbinder;
    @BindView(R.id.recycler_life)
    RecyclerView mRecyclerLife;
    @BindView(R.id.card_life)
    CardView mCardLife;
    @BindView(R.id.recycler_hourly)
    RecyclerView mRecyclerHourly;
    @BindView(R.id.card_hourly)
    CardView mCardHourly;
    @BindView(R.id.recycler_daily)
    RecyclerView mRecyclerDaily;
    @BindView(R.id.card_daily)
    CardView mCardDaily;
    @BindView(R.id.tv_now_low)
    TextView mTvNowLow;
    @BindView(R.id.tv_now_high)
    TextView mTvNowHigh;
    @BindView(R.id.tv_now_temp)
    TextView mTvNowTemp;
    @BindView(R.id.iv_now_icon)
    ImageView mIvNowIcon;
    private View view;
    private Weather mWeather = new Weather();
    private LifeAdapter mLifeAdapter;
    private HourlyAdapter mHourlyAdapter;
    private DailyAdapter mDailyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, view);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        if (mRefresh != null) {
            mRefresh.setColorSchemeColors(
                    Color.RED, Color.GREEN, Color.BLUE
            );
            mRefresh.setSize(SwipeRefreshLayout.DEFAULT);
            mRefresh.setDistanceToTriggerSync(240);
            mRefresh.setOnRefreshListener(() -> {
                mRefresh.postDelayed(this::load, 1000);
            });

            mRecyclerLife.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerHourly.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerDaily.setLayoutManager(new LinearLayoutManager(mContext));

            mLifeAdapter = new LifeAdapter(mWeather.getLifestyle());
            mHourlyAdapter = new HourlyAdapter(mWeather.getHourly());
            mDailyAdapter = new DailyAdapter(mWeather.getDaily_forecast());

            mRecyclerLife.setAdapter(mLifeAdapter);
            mRecyclerHourly.setAdapter(mHourlyAdapter);
            mRecyclerDaily.setAdapter(mDailyAdapter);
        }
    }


    private void load() {
        getWeather("上海")
                .doOnSubscribe(Along -> mRefresh.setRefreshing(true))
                .doOnError(throwable -> {
                    //TODO...相关错误处理，比如界面隐藏，显示错误界面等
                })
                .doOnNext(weather -> {
                    LogUtil.d(mWeather.toString());
                    mWeather = weather;

                    initNowWeather();
                    if (mWeather.getLifestyle() != null && mWeather.getLifestyle().size() > 0) {
                        mCardLife.setVisibility(View.VISIBLE);
                        mLifeAdapter.notifyDataSetChanged();
                    } else {
                        mCardLife.setVisibility(View.GONE);
                    }

                    if (mWeather.getHourly() != null && mWeather.getHourly().size() > 0) {
                        mCardHourly.setVisibility(View.VISIBLE);
                        mHourlyAdapter.notifyDataSetChanged();
                    } else {
                        mCardHourly.setVisibility(View.GONE);
                    }

                    if (mWeather.getDaily_forecast() != null && mWeather.getDaily_forecast().size() > 0) {
                        mCardDaily.setVisibility(View.VISIBLE);
                        mDailyAdapter.notifyDataSetChanged();
                    } else {
                        mCardDaily.setVisibility(View.GONE);
                    }

                })
                .doOnComplete(() -> {
                    //TODO...完成请求的逻辑处理
                    mRefresh.setRefreshing(false);
                })
                .subscribe();
    }

    private void initNowWeather() {
        int weatherIcon = SharedPreferenceUtil.getInstance().getInt(mWeather.getNow().getCond_code(), R.drawable.none);
        mIvNowIcon.setBackground(getResources().getDrawable(weatherIcon));
        mTvNowTemp.setText(getResources().getText(R.string.main_now_temp, mWeather.getNow().getTmp()));
        mTvNowHigh.setText(getResources().getText(R.string.main_now_high, mWeather.getDaily_forecast().get(0).getTmp_max()));
        mTvNowLow.setText(getResources().getText(R.string.main_now_low, mWeather.getDaily_forecast().get(0).getTmp_min()));
    }


    private Observable<Weather> getWeather(String city) {
        return WeatherRequest.getInstance().getWeather(city)
                .compose(RxUtil.fragmentLifecycle(this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
