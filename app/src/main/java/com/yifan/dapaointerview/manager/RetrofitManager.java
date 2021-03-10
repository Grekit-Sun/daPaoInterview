package com.yifan.dapaointerview.manager;

import com.yifan.dapaointerview.http.httptools.CookiesInterceptor;
import com.yifan.dapaointerview.http.httptools.HttpInterceptor;
import com.yifan.dapaointerview.http.httptools.ResponseConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-10
 */
public class RetrofitManager {

    private static volatile RetrofitManager mRetrofitManager;
    private static Retrofit retrofit = null;
    public static String HTTP_HOST_URL = "";

    private RetrofitManager() {
    }

    public static RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    public static <T> T getSpecialUrlInstance(String url, Class<T> service) {
        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(HTTP_CLIENT)
                .build()
                .create(service);
    }

    public static <T> T getDefaultInstance(Class<T> service) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(HTTP_HOST_URL)
                    .addConverterFactory(ResponseConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(HTTP_CLIENT)
                    .build();
        }
        return retrofit.create(service);
    }

    /**
     * 设置HttpClient
     */
    private static OkHttpClient HTTP_CLIENT =
            new OkHttpClient.Builder()
                    //添加自定义拦截器
                    .addInterceptor(new HttpInterceptor())
                    .addInterceptor(new CookiesInterceptor())
                    //设置超时时间
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();

}
