package com.kusofan.seeweather.module.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseFragment;
import com.kusofan.seeweather.component.RxBus;
import com.kusofan.seeweather.module.adapter.DailyAdapter;
import com.kusofan.seeweather.module.adapter.LifeAdapter;
import com.kusofan.seeweather.module.model.Weather;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by heming on 2018/1/22.
 */

public class MainFragment extends BaseFragment {


    @BindView(R.id.recycler_life)
    RecyclerView mRecyclerLife;
    @BindView(R.id.recycler_daily)
    RecyclerView mRecyclerDaily;
    //    @BindView(R.id.recycler)
//    RecyclerView mRecycler;
    private View view;
    private Weather mWeather = new Weather();
    private static MainFragment mMainFragment;
    private Unbinder unbinder;
    //    private WeatherAdapter mAdapter;
    private LifeAdapter mLifeAdapter;
    private DailyAdapter mDailyAdapter;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        if (mMainFragment == null) {
            mMainFragment = new MainFragment();
        }
        return mMainFragment;
    }

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
        RxBus.getDefault()
                .toObservable(Weather.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Weather>() {
                    @Override
                    public void accept(Weather weather) throws Exception {
                        mWeather = weather;
                        initView();
                    }
                });
    }

    private void initView() {
        if (mWeather != null) {
            if (mLifeAdapter == null) {
                mRecyclerLife.setLayoutManager(new LinearLayoutManager(mContext));
                mLifeAdapter = new LifeAdapter(mWeather.getLifestyle());
                mRecyclerLife.setAdapter(mLifeAdapter);
            } else {
                mLifeAdapter.notifyDataSetChanged();
            }

            if (mDailyAdapter == null) {
                mRecyclerDaily.setLayoutManager(new LinearLayoutManager(mContext));
                mDailyAdapter = new DailyAdapter(mWeather.getDaily_forecast());
                mRecyclerDaily.setAdapter(mDailyAdapter);
            } else {
                mDailyAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
