package com.yifan.dapaointerview.module.home.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.BaseFragment;
import com.yifan.dapaointerview.databinding.FragmentAndroidBinding;
import com.yifan.dapaointerview.databinding.FragmentJavaBinding;
import com.yifan.dapaointerview.module.home.viewmodel.IvAndroidViewModel;
import com.yifan.dapaointerview.module.home.viewmodel.IvJavaViewModel;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-08
 */
public class IvAndroidFragment extends BaseFragment<FragmentAndroidBinding, IvAndroidViewModel> {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(IvAndroidViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setIvAndroidModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
