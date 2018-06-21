package fanggu.org.hospitaldevicemonitor.itemDecoration;

import android.graphics.*;
import android.support.v7.widget.*;
import android.view.View;

public class MyItemDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = 1;
        outRect.bottom = 1;
        //由于每行都只有4个，所以第一个都是4的倍数，把左边距设为0
        int position=parent.getChildLayoutPosition(view);
        if (position%4==0) {
            outRect.left = 0;
        }

        int itemCount=parent.getAdapter().getItemCount();
        int rowCount=itemCount%4==0?itemCount/4:itemCount/4+1;     //一共几行
        int currRowCount=position%4==0?position/4+1:position/4+1;  //当前第几行

        //当前是最后一行的话底部没有间距
        if(currRowCount==rowCount){
            outRect.bottom = 0;
        }
        System.out.println();
    }

}
