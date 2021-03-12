package com.yifan.dapaointerview.module.funspace;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.lifecycle.ViewModelProvider;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.BaseFragment;
import com.yifan.dapaointerview.databinding.FragmentFunspaceBinding;
import com.yifan.dapaointerview.module.funspace.maskIdentify.MaskIdentifyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-12
 */
public class FsFragment extends BaseFragment<FragmentFunspaceBinding, FsViewModel> {

    @BindView(R.id.btn_mask_identify)
    Button mBtnMaskIdentify;
    @BindView(R.id.container_masked)
    FrameLayout mMaskedLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_funspace;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(FsViewModel.class);
    }

    @Override
    protected void bindViewModel() {
    }

    @Override
    protected void init() {
        ButterKnife.bind(this, mDataBinding.getRoot());
        mBtnMaskIdentify.setOnClickListener((view) -> {
            startMaskIdentify();
        });
    }

    private void startMaskIdentify() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_masked,new MaskIdentifyFragment())
                .addToBackStack(null)
                .commit();
        mMaskedLayout.setVisibility(View.VISIBLE);
    }
}
