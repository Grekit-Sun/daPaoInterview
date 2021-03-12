package com.yifan.dapaointerview.module.funspace.maskIdentify;

import androidx.lifecycle.ViewModelProvider;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.BaseFragment;
import com.yifan.dapaointerview.databinding.FragmentMaskidentifyBinding;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-12
 */
public class MaskIdentifyFragment extends BaseFragment<FragmentMaskidentifyBinding,MaskIdentifyViewModel> {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_maskidentify;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(MaskIdentifyViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {

    }
}
