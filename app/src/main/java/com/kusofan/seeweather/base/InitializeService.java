package com.kusofan.seeweather.base;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.kusofan.seeweather.R;
import com.kusofan.seeweather.common.Const;
import com.kusofan.seeweather.common.util.SharedPreferenceUtil;
import com.kusofan.seeweather.common.util.SystemUtil;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by heming on 2018/1/18.
 */

public class InitializeService extends IntentService {
    public static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("initApplication");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();
        //bugly. 调试阶段使用true,release使用false
        initBugly();

        initIcon();
        initLifeStyle();
        initLifeIcon();
        //TODO...待添加检测内存泄漏等...
    }

    private void initBugly() {
        Context context = getApplicationContext();
        String packageName = context.getPackageName();
        String processName = SystemUtil.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, Const.APP_ID_BUGLY, true, strategy);
    }

    private void initIcon() {
        if (SharedPreferenceUtil.getInstance().getIconType() == 0) {
            SharedPreferenceUtil.getInstance().putInt("未知", R.drawable.none);
            SharedPreferenceUtil.getInstance().putInt("晴", R.drawable.type_one_sunny);
            SharedPreferenceUtil.getInstance().putInt("阴", R.drawable.type_one_cloudy);
            SharedPreferenceUtil.getInstance().putInt("多云", R.drawable.type_one_cloudy);
            SharedPreferenceUtil.getInstance().putInt("少云", R.drawable.type_one_cloudy);
            SharedPreferenceUtil.getInstance().putInt("晴间多云", R.drawable.type_one_cloudytosunny);
            SharedPreferenceUtil.getInstance().putInt("小雨", R.drawable.type_one_light_rain);
            SharedPreferenceUtil.getInstance().putInt("中雨", R.drawable.type_one_light_rain);
            SharedPreferenceUtil.getInstance().putInt("大雨", R.drawable.type_one_heavy_rain);
            SharedPreferenceUtil.getInstance().putInt("阵雨", R.drawable.type_one_thunderstorm);
            SharedPreferenceUtil.getInstance().putInt("雷阵雨", R.drawable.type_one_thunder_rain);
            SharedPreferenceUtil.getInstance().putInt("霾", R.drawable.type_one_fog);
            SharedPreferenceUtil.getInstance().putInt("雾", R.drawable.type_one_fog);
        } else {
            SharedPreferenceUtil.getInstance().putInt("未知", R.drawable.none);
            SharedPreferenceUtil.getInstance().putInt("晴", R.drawable.type_two_sunny);
            SharedPreferenceUtil.getInstance().putInt("阴", R.drawable.type_two_cloudy);
            SharedPreferenceUtil.getInstance().putInt("多云", R.drawable.type_two_cloudy);
            SharedPreferenceUtil.getInstance().putInt("少云", R.drawable.type_two_cloudy);
            SharedPreferenceUtil.getInstance().putInt("晴间多云", R.drawable.type_two_cloudytosunny);
            SharedPreferenceUtil.getInstance().putInt("小雨", R.drawable.type_two_light_rain);
            SharedPreferenceUtil.getInstance().putInt("中雨", R.drawable.type_two_rain);
            SharedPreferenceUtil.getInstance().putInt("大雨", R.drawable.type_two_rain);
            SharedPreferenceUtil.getInstance().putInt("阵雨", R.drawable.type_two_rain);
            SharedPreferenceUtil.getInstance().putInt("雷阵雨", R.drawable.type_two_thunderstorm);
            SharedPreferenceUtil.getInstance().putInt("霾", R.drawable.type_two_haze);
            SharedPreferenceUtil.getInstance().putInt("雾", R.drawable.type_two_fog);
            SharedPreferenceUtil.getInstance().putInt("雨夹雪", R.drawable.type_two_snowrain);
        }
    }

    private void initLifeStyle() {
        SharedPreferenceUtil.getInstance().putString("comf", "舒适度指数");
        SharedPreferenceUtil.getInstance().putString("cw", "洗车指数");
        SharedPreferenceUtil.getInstance().putString("drsg", "穿衣指数");
        SharedPreferenceUtil.getInstance().putString("flu", "感冒指数");
        SharedPreferenceUtil.getInstance().putString("sport", "运动指数");
        SharedPreferenceUtil.getInstance().putString("trav", "旅游指数");
        SharedPreferenceUtil.getInstance().putString("uv", "紫外线指数");
        SharedPreferenceUtil.getInstance().putString("air", "空气污染指数");
    }

    private void initLifeIcon() {
        SharedPreferenceUtil.getInstance().putInt("comf", R.drawable.icon_comf);
        SharedPreferenceUtil.getInstance().putInt("cw", R.drawable.icon_cw);
        SharedPreferenceUtil.getInstance().putInt("drsg", R.drawable.icon_drsg);
        SharedPreferenceUtil.getInstance().putInt("flu", R.drawable.icon_flu);
        SharedPreferenceUtil.getInstance().putInt("sport", R.drawable.icon_sport);
        SharedPreferenceUtil.getInstance().putInt("trav", R.drawable.icon_travel);
        SharedPreferenceUtil.getInstance().putInt("uv", R.drawable.icon_uv);
        SharedPreferenceUtil.getInstance().putInt("air", R.drawable.icon_air);
    }
}
