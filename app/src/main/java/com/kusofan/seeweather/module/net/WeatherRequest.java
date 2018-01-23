package com.kusofan.seeweather.module.net;

import com.kusofan.seeweather.common.Const;
import com.kusofan.seeweather.common.util.RxUtil;
import com.kusofan.seeweather.common.util.ToastUtil;
import com.kusofan.seeweather.component.RetrofitManager;
import com.kusofan.seeweather.module.api.WeatherInterface;
import com.kusofan.seeweather.module.model.Weather;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by heming on 2018/1/19.
 */

public class WeatherRequest {
    private static WeatherRequest sInstance;

    public static WeatherRequest getInstance() {
        if (sInstance == null) {
            sInstance = new WeatherRequest();
        }
        return sInstance;
    }

    private WeatherRequest() {
    }

    public Observable<Weather> getWeather(String city) {
        return RetrofitManager.getInstance()
                .getService(WeatherInterface.class)
                .mWeatherAPI(city, Const.KEY_WEATHER)
                //flatMap操作符，将一个Observable变换为多个Observable
                //变换为2个，进行判断，用于 接口错误处理
                .flatMap(weather -> {
                    String status = weather.getHeWeather6().get(0).status;
                    if (!"ok".equals(status)) {
                        return Observable.error(new RuntimeException(ErrorMsg(status)));
                    } else {
                        return Observable.just(weather);
                    }
                })
                .map(weather-> weather.getHeWeather6().get(0))
                //接受Observable.error发送出来的error
                .doOnError(WeatherRequest::disposeFailureInfo)
                //使用转换器，代码统一处理。进行线程切换
                .compose(RxUtil.io());

    }

    private static Consumer<Throwable> disposeFailureInfo(Throwable t) {
        return throwable -> {
            if (t.toString().contains("no data for this location")) {
                ToastUtil.shortToast("万分抱歉，该城市/地区暂不支持查询天气情况");
            } else if (t.toString().contains("too fast") || t.toString().contains("no more requests")) {
                ToastUtil.shortToast("单日请求次数已超限，请明天再试");
            } else if (t.toString().contains("dead")) {
                ToastUtil.shortToast("请检查网络连接或服务器故障，请稍后再试");
            } else {
                ToastUtil.shortToast("服务器异常，请稍后再试");
            }

        };
    }

    private String ErrorMsg(String status) {

        Map<String, String> ErrorMsg = new HashMap<>();
        //城市问题
        ErrorMsg.put("no data for this location", "该城市/地区没有你所请求的数据");
        //请求次数超限
        ErrorMsg.put("too fast", "超过限定的QPM，请参考QPM说明");
        ErrorMsg.put("no more requests", "超过访问次数，需要等到当月最后一天24点后进行访问次数的重置或升级你的访问量");
        //服务器问题
        ErrorMsg.put("dead", "无响应或超时，接口服务异常请联系我们");
        //代码问题
        ErrorMsg.put("unknown location", "未知或错误城市/地区");
        ErrorMsg.put("invalid key", "错误的key，请检查你的key是否输入以及是否输入有误");
        ErrorMsg.put("param invalid", "参数错误，请检查你传递的参数是否正确");
        ErrorMsg.put("permission denied", "无访问权限，你没有购买你所访问的这部分服务");
        ErrorMsg.put("sign error", "签名错误，请参考签名算法");

        return ErrorMsg.get(status);
    }
}
