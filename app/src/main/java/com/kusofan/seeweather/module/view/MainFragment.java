package com.kusofan.seeweather.module.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseFragment;
import com.kusofan.seeweather.component.RxBus;
import com.kusofan.seeweather.module.model.Weather;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by heming on 2018/1/22.
 */

public class MainFragment extends BaseFragment {


    private View view;
    private Weather mWeather = new Weather();
    private static MainFragment mMainFragment;
    private Unbinder unbinder;

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
                .doOnNext(weather -> {
                    mWeather = weather;
                })
                .subscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
