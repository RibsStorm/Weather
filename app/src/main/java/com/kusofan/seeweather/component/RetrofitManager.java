package com.kusofan.seeweather.component;

import com.kusofan.seeweather.common.Const;
import com.kusofan.seeweather.common.util.LogUtil;
import com.kusofan.seeweather.common.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heming on 2018/1/18.
 */

public class RetrofitManager {
    private static Retrofit sRetrofit = null;
    private static OkHttpClient sOkHttpClient = null;
    private static RetrofitManager sInstance;

    private RetrofitManager() {
        init();
    }


    public static RetrofitManager getInstance() {
        if (sInstance == null) {
            sInstance = new RetrofitManager();
        }

        return sInstance;
    }

    private void init() {
        initOKHttp();
        initRetrofit();
    }

    public <R> R getService(Class<R> c) {
        return sRetrofit.create(c);
    }

    private void initOKHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 缓存 http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(Const.NET_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if (!SystemUtil.isNetworkConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            //打印所有请求
            logRequest(request);
            Response response = chain.proceed(request);
            Response.Builder newBuilder = response.newBuilder();
            if (SystemUtil.isNetworkConnected()) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            }
            return newBuilder.build();
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);

        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();
    }

    private void initRetrofit() {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private void logRequest(Request request) {
        if (request == null) {
            LogUtil.w("RequestManager", "Request is null!");
        } else {
            LogUtil.d("Start Request: " + request.toString());
            if (request.headers() == null) {
                LogUtil.d("Start Request: headers: EMPTY");
            } else {
                LogUtil.d("Start Request: headers: " + request.headers().toString());
            }

            if (request.body() == null) {
                LogUtil.d("Start Request: body: EMPTY");
            } else {
                Buffer buffer = new Buffer();

                try {
                    request.body().writeTo(buffer);
                } catch (IOException var3) {
                    var3.printStackTrace();
                }

                LogUtil.d("Start Request: body: " + buffer.readString(Charset.forName("UTF-8")));
            }
        }

    }
}
