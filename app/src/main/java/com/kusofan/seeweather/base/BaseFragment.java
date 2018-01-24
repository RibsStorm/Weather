package com.kusofan.seeweather.base;

import android.content.Context;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by heming on 2018/1/18.
 * 继承RxFragment,拥有RxLifeCycle对生命周期及事件管理
 */

public class BaseFragment extends RxFragment {
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
}
