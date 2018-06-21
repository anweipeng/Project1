package fanggu.org.lotteryapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import fanggu.org.lotteryapplication.R;

/**
 * 往期开奖数据适配器
 */
public class LotteryHistoryDataAdapter extends RecyclerView.Adapter<LotteryHistoryDataAdapter.ViewHolder> {
    private String[][] dataArr;

    public LotteryHistoryDataAdapter(String[][] dataArr) {
        this.dataArr = dataArr;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
//        System.err.println("############################onCreateViewHolder");
        Context context = recyclerView.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.lottery_history_data_item,recyclerView,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        System.err.println("##############position##############: "+position);
        View itemView = holder.itemView;
        TextView qihaoView = itemView.findViewById(R.id.data_qihao);
        qihaoView.setText(dataArr[position][0]);

        try {
            for(int i=1;i<=5;i++){
                String openedNumber=dataArr[position][i];

                int ball_id=R.id.class.getField("data_ball_"+Integer.parseInt(openedNumber)).getInt(null);
                TextView ball = itemView.findViewById(ball_id);
                ball.setTextColor(Color.parseColor("#ffffff"));

                //设置背景图片,判断是否连号
                if(position==0){ //第一行
                    ball.setBackgroundDrawable(itemView.getContext().getResources().getDrawable(R.drawable.history_red));
                }else{ //非第一行
                    //判断是否连号
                    String preRowOpenedNumber=dataArr[position-1][i];
                    if(preRowOpenedNumber.equals(openedNumber)){ //此号码和上一行同样位置号码是否相同
                        ball.setBackgroundDrawable(itemView.getContext().getResources().getDrawable(R.drawable.history_serial_yellow_ball));
                    }else{
                        ball.setBackgroundDrawable(itemView.getContext().getResources().getDrawable(R.drawable.history_red));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataArr.length;
    }

}
