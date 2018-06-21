package fanggu.org.hospitaldevicemonitor.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fanggu.org.hospitaldevicemonitor.R;


/**
 * 往期开奖数据适配器
 */
public class MenuGridDataAdapter extends RecyclerView.Adapter<MenuGridDataAdapter.ViewHolder> {
    private String[][] dataArr;

    public MenuGridDataAdapter(String[][] dataArr) {
        this.dataArr = dataArr;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        Context context = recyclerView.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.menu_grid_item,recyclerView,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        View itemView = holder.itemView;
        TextView item_tv = itemView.findViewById(R.id.item_tv);

        if(position>=dataArr.length){
            item_tv.setText("");
            item_tv.setCompoundDrawablesWithIntrinsicBounds(null, null , null, null);
        }else{
            item_tv.setText(dataArr[position][0]);
            Drawable top = itemView.getResources().getDrawable(Integer.parseInt(dataArr[position][1]));
            item_tv.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
        }

        System.out.println();

    }

    @Override
    public int getItemCount() {
        return dataArr.length+3;
    }

}
