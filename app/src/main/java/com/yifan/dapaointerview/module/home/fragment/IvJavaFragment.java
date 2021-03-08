package com.yifan.dapaointerview.module.home.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.BaseFragment;
import com.yifan.dapaointerview.databinding.FragmentJavaBinding;
import com.yifan.dapaointerview.databinding.FragmentListBinding;
import com.yifan.dapaointerview.module.home.viewmodel.IvJavaViewModel;
import com.yifan.dapaointerview.module.home.viewmodel.IvViewModel;
import com.yifan.dapaointerview.module.main.viewmodel.MainViewModel;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-08
 */
public class IvJavaFragment extends BaseFragment<FragmentJavaBinding, IvJavaViewModel> {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_java;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(IvJavaViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setIvJavaModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
