package com.kusofan.seeweather.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by heming on 2018/1/18.
 */

public class BaseApplication extends Application {

    public static Context sAppContext;
    public static String sAppCache;

    @Override
    public void onCreate() {
        super.onCreate();
        //启用工作线程进行初始化
        InitializeService.start(this);
        /*
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            sAppCache = getApplicationContext().getExternalCacheDir().toString();
        } else {
            sAppCache = getApplicationContext().getCacheDir().toString();
        }
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static Context getAppContext() {
        return sAppContext;
    }

    public static String getAppCache() {
        return sAppCache;
    }
}
