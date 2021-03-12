package com.yifan.dapaointerview.module.main.activity;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.base.BaseActivity;
import com.yifan.dapaointerview.databinding.ActivityMainBinding;
import com.yifan.dapaointerview.module.main.viewmodel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private AppBarConfiguration mAppBarConfiguration;

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
        initView();
    }

    private void initView() {
        mDrawer = mDataBinding.drawerLayout;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_fs_space, R.id.nav_collect, R.id.nav_me)
                .setDrawerLayout(mDrawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        BottomNavigationView navView = findViewById(R.id.nav_view_bottom);
        NavigationUI.setupWithNavController(navView, navController);
    }
}