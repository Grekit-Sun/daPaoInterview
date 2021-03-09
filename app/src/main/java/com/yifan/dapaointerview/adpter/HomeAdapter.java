package com.yifan.dapaointerview.adpter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yifan.dapaointerview.R;

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
    private List mList;

    HomeAdapter(List list) {
        mList = list;
    }

    /**
     * 创建ViewHolder并返回，后续item布局里控件都是从ViewHolder中取出
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public HomeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * 自定义的ViewHolder
     */
    class MyHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);
        }
    }

    public int getItemLayout(){

    }
}
