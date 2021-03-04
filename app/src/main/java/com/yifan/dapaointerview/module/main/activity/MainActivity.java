package com.yifan.dapaointerview.module.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.BaseActivity;
import com.yifan.dapaointerview.databinding.ActivityMainBinding;
import com.yifan.dapaointerview.module.main.viewmodel.MainViewModel;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
    }
}