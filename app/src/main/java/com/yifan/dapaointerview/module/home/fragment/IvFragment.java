package com.yifan.dapaointerview.module.home.fragment;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.adpter.MyFragmentStateAdapter;
import com.yifan.dapaointerview.base.BaseFragment;
import com.yifan.dapaointerview.databinding.FragmentJavaBinding;
import com.yifan.dapaointerview.databinding.FragmentListBinding;
import com.yifan.dapaointerview.module.home.viewmodel.IvViewModel;
import com.yifan.dapaointerview.module.main.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-08
 */
public class IvFragment extends BaseFragment<FragmentListBinding, IvViewModel> {

    @BindView(R.id.iv_vp)
    ViewPager2 mViewPager;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    private List<Fragment> mFragmentList;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(IvViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setIvModel(mViewModel);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,mDataBinding.getRoot());
        initFragment();
        initView();
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        IvJavaFragment javaFragment = new IvJavaFragment();
        IvAndroidFragment androidFragment = new IvAndroidFragment();
        mFragmentList.add(javaFragment);
        mFragmentList.add(androidFragment);
    }

    private void initView() {

        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(getActivity().getSupportFragmentManager(), mFragmentList, getLifecycle());
        mViewPager.setAdapter(adapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.java)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.android)));
        // 添加页签选中监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        // 注册页面变化的回调接口
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.setScrollPosition(position, 0, false);
            }
        });
    }
}
