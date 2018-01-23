package com.kusofan.seeweather.module.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseFragment;
import com.kusofan.seeweather.common.util.RxUtil;
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
    private View view;
    private Weather mWeather;

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
        }
    }


    private void load() {
        getWeather("上海")
                .doOnSubscribe(Along -> mRefresh.setRefreshing(true))
                .doOnError(throwable -> {
                    //TODO...相关错误处理，比如界面隐藏，显示错误界面等
                })
                .doOnNext(weather -> {
                    mWeather = weather;
                })
                .doOnComplete(() -> {
                    //TODO...完成请求的逻辑处理
                    mRefresh.setRefreshing(false);
                })
                .subscribe();
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
