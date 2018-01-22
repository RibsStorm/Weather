package com.kusofan.seeweather.module.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseFragment;
import com.kusofan.seeweather.common.util.RxUtil;
import com.kusofan.seeweather.common.util.ToastUtil;
import com.kusofan.seeweather.module.model.Weather;
import com.kusofan.seeweather.module.model.WeatherAPI;
import com.kusofan.seeweather.module.net.WeatherRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by heming on 2018/1/22.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.tv_text)
    TextView mTvText;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @OnClick(R.id.btn_test)
    void testWeather() {
        getWeather("上海")
                .doOnError(throwable -> {
                    //TODO...相关错误处理，比如界面隐藏，显示错误界面等
                })
                .doOnNext(weather -> {
                    if (weather.getHeWeather6() != null && weather.getHeWeather6().size() > 0) {
                        Weather totayWea = weather.getHeWeather6().get(0);
                        if (totayWea.getDaily_forecast() != null && totayWea.getDaily_forecast().size() > 0)
                            mTvText.setText(totayWea.getDaily_forecast().get(0).toString());
                        ToastUtil.shortToast(totayWea.getDaily_forecast().get(0).toString());
                    }
                })
                .doOnComplete(() -> {
                    //TODO...完成请求的逻辑处理
                })
                .subscribe();
    }

    private Observable<WeatherAPI> getWeather(String city) {
        return WeatherRequest.getInstance().getWeather(city)
                .compose(RxUtil.fragmentLifecycle(this));
    }


}
