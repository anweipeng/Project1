package fanggu.org.lotteryapplication.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;

import fanggu.org.lotteryapplication.R;

//自定义适配器（通过继承BaseAdapter）
public class MyAdapter extends BaseAdapter {
    Context context;//声明适配器中引用的上下文
    //将需要引用的图片和文字分别封装成数组

    String[] names = {"100", "200", "300", "400", "500", "600"};
    //通过构造方法初始化上下文
    public MyAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return names.length;//images也可以
    }
    @Override
    public Object getItem(int position) {
        return names[position];//images也可以
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //通过布局填充器LayoutInflater填充网格单元格内的布局
        View v = LayoutInflater.from(context).inflate(R.layout.lottery_ball_gridview_item, null);
        //使用findViewById分别找到单元格内布局的图片以及文字
        TextView tv = (TextView) v.findViewById(R.id.ballTextView);
        //引用数组内元素设置布局内图片以及文字的内容

        tv.setText(names[position]);
        //返回值一定为单元格整体布局v
        return v;
    }

    /**
     * 刷新单个item
     *
     * @param gridView
     * @param position
     */
    public void refreshListView(GridView gridView, int position) {
        int start = gridView.getFirstVisiblePosition();
        int last = gridView.getLastVisiblePosition();
        for (int i = start, j = last; i <= j; i++) {
            if (position == i) {
                LinearLayout convertView = (LinearLayout)gridView.getChildAt(position - start);
                if (convertView != null) {
//                    getView(position, convertView, gridView);
                    TextView child = (TextView)convertView.getChildAt(0);
                    child.setText(Integer.parseInt(child.getText().toString())+1+"");
                    break;
                }
            }
        }
    }
}
