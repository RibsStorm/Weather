package com.kusofan.seeweather.common;

import com.kusofan.seeweather.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heming on 2018/1/26.
 */

public class WeatherConstant {
    public Map<String, String> lifeStyles;
    public Map<String, Integer> lifeIcons;
    public Map<String, Integer> weaIcons;

    private static WeatherConstant sInstance;

    public static WeatherConstant getInstance() {
        if (sInstance == null) {
            return sInstance = new WeatherConstant();
        }
        return sInstance;
    }

    private WeatherConstant() {
        initData();
    }

    private void initData() {
        if (weaIcons == null) {
            weaIcons = new HashMap<>();
            weaIcons.put("未知", R.drawable.none);
            weaIcons.put("晴", R.drawable.type_one_sunny);
            weaIcons.put("阴", R.drawable.type_one_cloudy);
            weaIcons.put("多云", R.drawable.type_one_cloudy);
            weaIcons.put("少云", R.drawable.type_one_cloudy);
            weaIcons.put("晴间多云", R.drawable.type_one_cloudytosunny);
            weaIcons.put("小雨", R.drawable.type_one_light_rain);
            weaIcons.put("中雨", R.drawable.type_one_light_rain);
            weaIcons.put("大雨", R.drawable.type_one_heavy_rain);
            weaIcons.put("阵雨", R.drawable.type_one_thunderstorm);
            weaIcons.put("雷阵雨", R.drawable.type_one_thunder_rain);
            weaIcons.put("霾", R.drawable.type_one_fog);
            weaIcons.put("雾", R.drawable.type_one_fog);
        }

        if (lifeStyles == null) {
            lifeStyles = new HashMap<>();
            lifeStyles.put("comf", "舒适度指数");
            lifeStyles.put("cw", "洗车指数");
            lifeStyles.put("drsg", "穿衣指数");
            lifeStyles.put("flu", "感冒指数");
            lifeStyles.put("sport", "运动指数");
            lifeStyles.put("trav", "旅游指数");
            lifeStyles.put("uv", "紫外线指数");
            lifeStyles.put("air", "空气污染指数");
        }

        if (lifeIcons == null) {
            lifeIcons = new HashMap<>();
            lifeIcons.put("comf", R.drawable.icon_comf);
            lifeIcons.put("cw", R.drawable.icon_cw);
            lifeIcons.put("drsg", R.drawable.icon_drsg);
            lifeIcons.put("flu", R.drawable.icon_flu);
            lifeIcons.put("sport", R.drawable.icon_sport);
            lifeIcons.put("trav", R.drawable.icon_travel);
            lifeIcons.put("uv", R.drawable.icon_uv);
            lifeIcons.put("air", R.drawable.icon_air);
        }
    }
}
