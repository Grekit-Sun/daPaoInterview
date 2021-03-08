package com.yifan.dapaointerview.adpter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-08
 */
public class MyFragmentStateAdapter extends FragmentStateAdapter {

    private List<Fragment> mFragmets;

    public MyFragmentStateAdapter(@NonNull FragmentManager fragmentManager, List<Fragment> fragments, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.mFragmets = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmets.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmets.size();
    }
}
