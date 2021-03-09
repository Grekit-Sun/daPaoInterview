package com.yifan.dapaointerview.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.yifan.dapaointerview.R;
import com.yifan.dapaointerview.bean.KnowledgeBean;

import java.util.List;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-09
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHolder> {

    /**
     * 数据源
     */
    private List<KnowledgeBean> mList;

    HomeAdapter(List<KnowledgeBean> list) {
        mList = list;
    }

    /**
     * 创建ViewHolder并返回，后续item布局里控件都是从ViewHolder中取出
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public HomeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), viewType, viewGroup, false);
        return new MyHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
//        return getItemLayout();
        return 0;
    }

    /**
     * 自定义的ViewHolder
     */
    class MyHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        public MyHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

//    public int getItemLayout(){
////        return R.layout.item_knowledge;
//    }
}
