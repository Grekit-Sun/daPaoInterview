package com.yifan.dapaointerview.module.home.viewmodel.javaviewmodel;

import com.yifan.dapaointerview.base.viewmodel.BaseViewModel;
import com.yifan.dapaointerview.bean.KnowledgeBean;
import com.yifan.dapaointerview.util.NetworkUtils;

import java.util.List;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-08
 */
public class IvJavaViewModel extends BaseViewModel {

    /**
     * load java data
     *
     * @return
     */
    public void loadJavaData() {
        if (NetworkUtils.isConnected() && NetworkUtils.getWifiEnabled()) {
            loadJavaDataByNet();
        } else {
            loadJavaDataByDb();
        }
    }

    private void loadJavaDataByDb() {
    }

    private void loadJavaDataByNet() {
    }

}