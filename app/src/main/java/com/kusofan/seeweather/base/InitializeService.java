package com.kusofan.seeweather.base;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.kusofan.seeweather.common.Const;
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
}
