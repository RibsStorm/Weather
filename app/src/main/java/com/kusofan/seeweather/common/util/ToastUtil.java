package com.kusofan.seeweather.common.util;

import android.content.Context;
import android.widget.Toast;

import com.kusofan.seeweather.base.BaseApplication;

/**
 * Created by hemin on 2018/1/20.
 */

public class ToastUtil {

    public static void shortToast(String msg){
        Toast.makeText(BaseApplication.getAppContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String msg){
        Toast.makeText(BaseApplication.getAppContext(),msg,Toast.LENGTH_LONG).show();
    }
}
