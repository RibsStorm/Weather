package com.kusofan.seeweather;

import android.os.Bundle;
import android.widget.TextView;

import com.kusofan.seeweather.base.BaseActivity;
import com.kusofan.seeweather.common.util.RxUtil;
import com.kusofan.seeweather.module.model.Weather;
import com.kusofan.seeweather.module.model.WeatherAPI;
import com.kusofan.seeweather.module.net.WeatherRequest;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * TODO...理一下网络请求流程
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick(R.id.btn_test)
    void testWeather() {
        getWeather("上海")
                //订阅上游
                .doOnError(throwable -> {
                    //TODO...相关错误处理，比如界面隐藏，显示错误界面等
                })
                .doOnNext(weather -> {
                    if (weather.getHeWeather6() != null && weather.getHeWeather6().size() > 0) {
                        Weather totayWea = weather.getHeWeather6().get(0);
                        if (totayWea.getDaily_forecast() != null && totayWea.getDaily_forecast().size() > 0)
                            tvText.setText(totayWea.getDaily_forecast().get(0).toString());
                    }
                })
                .doOnComplete(() -> {
                    //TODO...完成请求的逻辑处理
                })
                .subscribe();
    }

    private Observable<WeatherAPI> getWeather(String city) {
        city = "上海";
        return WeatherRequest.getInstance().getWeather(city)
                .compose(RxUtil.activityLifecycle(this));
    }
}
