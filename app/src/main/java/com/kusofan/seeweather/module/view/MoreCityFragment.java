package com.kusofan.seeweather.module.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by heming on 2018/1/22.
 */

public class MoreCityFragment extends BaseFragment {


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view ==null){
            view = inflater.inflate(R.layout.fragment_city_more,container,false);
            ButterKnife.bind(this,view);
        }
        return view;
    }
}
