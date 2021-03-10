package com.yifan.dapaointerview.module.home.fragment.java;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.adpter.HomeAdapter;
import com.yifan.dapaointerview.base.BaseFragment;
import com.yifan.dapaointerview.bean.KnowledgeBean;
import com.yifan.dapaointerview.databinding.FragmentJavaBinding;
import com.yifan.dapaointerview.module.home.viewmodel.javaviewmodel.IvJavaViewModel;

import java.util.ArrayList;

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
        mViewModel.loadJavaData();
        initRecycle();
    }

    private void initRecycle() {

        ArrayList<KnowledgeBean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            KnowledgeBean bean = new KnowledgeBean();
            bean.setAuthor("szx:"+1);
            bean.setCategory("handsomeboy:"+i);
            bean.setContent("I am content!");
            bean.setH1("hhhhhhhhhhhh1 : "+ i);
            bean.setH2("hhhhhhhhhhhh2 : " + i);
            bean.setNiceDate("2021-03-10 : " + i);
            list.add(bean);
        }
        HomeAdapter adapter = new HomeAdapter(list);
        mDataBinding.recycleJava.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.recycleJava.setAdapter(adapter);
    }
}
