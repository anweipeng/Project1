package fanggu.org.lotteryapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fanggu.org.lotteryapplication.R;

/**
 * Created by 77386 on 2018/5/17.
 */

public class LotteryHistoryTitleAdapter extends RecyclerView.Adapter<LotteryHistoryTitleAdapter.ViewHolder> {
    private String[] titleArr;

    public LotteryHistoryTitleAdapter(String[] titleArr) {
        this.titleArr=titleArr;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
//        System.err.println("############################onCreateViewHolder");
        Context context = recyclerView.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.lottery_history_title_item,recyclerView,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View itemView = holder.itemView;
        TextView titleView = itemView.findViewById(R.id.title_qihao);
        titleView.setText(titleArr[position]);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

}
