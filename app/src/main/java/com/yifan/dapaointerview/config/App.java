package com.yifan.dapaointerview.config;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.bumptech.glide.Glide;
import com.guoxiaoxing.phoenix.picker.Phoenix;
import com.orhanobut.hawk.Hawk;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.http.data.HttpBaseResponse;
import com.yifan.dapaointerview.http.request.HttpFactory;
import com.yifan.dapaointerview.http.request.ServerAddress;
import com.yifan.dapaointerview.http.httptools.HttpException;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-01-13
 */
public class App extends MultiDexApplication {

    private static Context context;
    public static boolean firstOpen;

    @Override
    public void onCreate() {
        super.onCreate();
        firstOpen = true;
        context = this;
        initActivityManager();
        init();
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 管理Activity
     */
    private void initActivityManager() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    /**
     * 一些第三方库和本地代码的初始化设置
     */
    private void init() {
        Hawk.init(context).build();
        setHttpConfig();

        Phoenix.config()
                .imageLoader((mContext, imageView, imagePath, type) -> Glide.with(mContext)
                        .load(imagePath)
                        .into(imageView));
    }

    /**
     * 请求配置
     */
    private static void setHttpConfig() {
        HttpFactory.HTTP_HOST_URL = ServerAddress.getApiDefaultHost();
        HttpFactory.httpResponseInterface = (gson, response) -> {
            if (firstOpen) {
                firstOpen = false;
                return response;
            }

            HttpBaseResponse httpResponse = gson.fromJson(response, HttpBaseResponse.class);
            if (httpResponse.errorCode != 0) {
                throw new HttpException(httpResponse.errorCode, httpResponse.errorMsg);
            }
            return gson.toJson(httpResponse.data);
        };
    }

    /**
     * 设置上拉加载和下拉刷新的样式
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });

        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            layout.setEnableFooterFollowWhenLoadFinished(true);
            layout.setEnableAutoLoadMore(false);
        });
    }

}
