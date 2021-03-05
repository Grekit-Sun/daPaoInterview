package com.yifan.dapaointerview.module.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.yanzhenjie.permission.Action;
import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.BaseActivity;
import com.yifan.dapaointerview.databinding.ActivitySplashBinding;
import com.yifan.dapaointerview.helper.PermissionHelper;
import com.yifan.dapaointerview.module.main.activity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-04
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel> {

    public static final String FONTS = "fonts/yizhi.ttf";

    @BindView(R.id.appname_txt)
    TextView mAppNameTxt;
    @BindView(R.id.slogan_txt)
    TextView mSloganTxt;
    @BindView(R.id.bottom_appname_txt)
    TextView mBottomAppNameTxt;

    /**
     * 跳转主页的最大延迟
     */
    int FLAG_MAIN_DELAY = 2000;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setSplashViewModel(mViewModel);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initFont();
        startToMain();
    }

    private void initFont() {
        //设置自定义字体
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONTS);
        mAppNameTxt.setTypeface(typeface);
        mSloganTxt.setTypeface(typeface);
        mBottomAppNameTxt.setTypeface(typeface);
    }

    /**
     * 跳转至主页
     */
    public void startToMain() {
        initPremission();
    }

    /**
     * 初始化权限
     */
    private void initPremission() {
        PermissionHelper.requestMultiPermission(this, new Action() {
                    @Override
                    public void onAction(Object data) {
                        delyToMain();
                    }
                }, new Action() {
                    @Override
                    public void onAction(Object data) {
                        delyToMain();
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * 延时跳转
     */
    private void delyToMain() {
        handleEventDelay(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, FLAG_MAIN_DELAY);
    }
}
