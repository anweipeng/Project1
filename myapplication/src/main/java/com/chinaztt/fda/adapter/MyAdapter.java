package com.chinaztt.fda.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinaztt.fda.bean.ItemBean;
import com.chinaztt.fda.ui.R;

import java.util.List;

/**
 * Created by hx on 2017/11/7.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<ItemBean> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View myView;
        ImageView imageView;
        TextView title;
        TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    public MyAdapter(List<ItemBean> list){
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //将数据绑定到控件上
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemBean bean = mList.get(position);
        holder.imageView.setBackgroundResource(bean.itemImage);
        holder.title.setText(bean.itemTitle);
        holder.content.setText(bean.itemContent);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ItemBean> addMessageList) {
        //增加数据
        int position = mList.size();
        mList.addAll(position, addMessageList);
        notifyItemInserted(position);
    }

    public void refresh(List<ItemBean> newList) {
        //刷新数据
        mList.removeAll(mList);
        mList.addAll(newList);
        notifyDataSetChanged();
    }
}
