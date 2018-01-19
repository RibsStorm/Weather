package com.kusofan.seeweather.common;

import com.kusofan.seeweather.base.BaseApplication;

import java.io.File;

/**
 * Created by heming on 2018/1/18.
 */

public class Const {
    public static final String BASE_URL = "https://free-api.heweather.com";

    public static final String APP_ID_BUGLY = "f922c5b646";

    public static final String KEY_WEATHER = "e1984444a03d4a52a1f6cc545cce9245";

    public static final String NET_CACHE = BaseApplication.getAppCache() + File.separator + "NetCache";

}
