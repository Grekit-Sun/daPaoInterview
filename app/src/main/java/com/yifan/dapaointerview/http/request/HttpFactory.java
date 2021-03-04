package com.yifan.dapaointerview.http.request;


import com.yifan.dapaointerview.http.data.HttpResponseInterface;
import com.yifan.dapaointerview.http.httptools.CookiesInterceptor;
import com.yifan.dapaointerview.http.httptools.HttpInterceptor;
import com.yifan.dapaointerview.http.httptools.ResponseConverterFactory;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @Description: 网络请求
 * @Author: ZhengXiang Sun
 * @Data: 2021-01-13
 */
public class HttpFactory {

    public static String HTTP_HOST_URL = "";
    public static HttpResponseInterface httpResponseInterface = null;

    private HttpFactory() {

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

    private static Retrofit retrofit = null;

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

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> Flowableschedulers() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> ObservableTschedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
