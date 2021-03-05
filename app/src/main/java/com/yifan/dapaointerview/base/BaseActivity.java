package com.yifan.dapaointerview.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.viewmodel.BaseViewModel;
import com.yifan.dapaointerview.config.LoadState;
import com.yifan.dapaointerview.databinding.ActivityBaseBinding;
import com.yifan.dapaointerview.databinding.ViewLoadErrorBinding;
import com.yifan.dapaointerview.databinding.ViewLoadingBinding;
import com.yifan.dapaointerview.databinding.ViewNoDataBinding;
import com.yifan.dapaointerview.databinding.ViewNoNetworkBinding;
import com.yifan.dapaointerview.helper.WeakHandlerHelper;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-01-13
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    public DB mDataBinding;

    protected VM mViewModel;

    private ActivityBaseBinding mActivityBaseBinding;

    private ViewLoadingBinding mViewLoadingBinding;

    private ViewLoadErrorBinding mViewLoadErrorBinding;

    private ViewNoNetworkBinding mViewNoNetworkBinding;

    private ViewNoDataBinding mViewNoDataBinding;

    protected WeakHandlerHelper mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        if (isNoActionBar()) {
            setNoActionBar();
        }

        mActivityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutResId(),
                mActivityBaseBinding.flContentContainer, true);

        initViewModel();
        bindViewModel();

        mDataBinding.setLifecycleOwner(this);

        initLoadState();
        init();

        // ViewModel订阅生命周期事件
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }

        setFitsSystemWindows(this,false);
    }

    private void initLoadState() {
        if (mViewModel != null && isSupportLoad()) {
            mViewModel.loadState.observe(this, this::switchLoadView);
        }
    }

    /**
     * 处理参数
     *
     * @param intent 参数容器
     */
    protected void handleIntent(Intent intent) {

    }

    /**
     * 是否为沉浸模式
     *
     * @return true表示支持，false表示不支持
     */
    protected boolean isNoActionBar() {
        return false;
    }

    /**
     * 设置沉浸式状态栏
     */
    private void setNoActionBar() {
        Window window = getWindow();
        View decorView = window.getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 是否支持页面加载。默认不支持
     *
     * @return true表示支持，false表示不支持
     */
    protected boolean isSupportLoad() {
        return false;
    }

    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化ViewModel
     */
    protected abstract void initViewModel();

    /**
     * 绑定ViewModel
     */
    protected abstract void bindViewModel();

    /**
     * 初始化
     */
    protected abstract void init();

    private void switchLoadView(LoadState loadState) {
        removeLoadView();
        switch (loadState){
            case LOADING:
                if (mViewLoadingBinding == null) {
                    mViewLoadingBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_loading,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadingBinding.getRoot());
                break;

            case NO_NETWORK:
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_network,
                            mActivityBaseBinding.flContentContainer, false);
                    mViewNoNetworkBinding.setViewModel(mViewModel);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoNetworkBinding.getRoot());
                break;

            case NO_DATA:
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_data,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoDataBinding.getRoot());
                break;

            case ERROR:
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_load_error,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadErrorBinding.getRoot());
                break;

            default:
                break;
        }
    }

    private void removeLoadView() {
        int childCount = mActivityBaseBinding.flContentContainer.getChildCount();
        if (childCount > 1) {
            mActivityBaseBinding.flContentContainer.removeViews(1, childCount - 1);
        }
    }

    /**
     * 设置页面最外层布局 FitsSystemWindows 属性
     * @param activity
     * @param value
     */
    public static void setFitsSystemWindows(Activity activity, boolean value) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(value);
        }
    }

    /**
     * 延时处理
     *
     * @param runnable
     * @param delayMillis
     */
    public void handleEventDelay(Runnable runnable, long delayMillis) {
        if (mHandler == null) {
            mHandler = new WeakHandlerHelper();
        }
        mHandler.postDelayed(runnable, delayMillis);
    }
}
