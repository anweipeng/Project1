package fanggu.org.hospitaldevicemonitor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import fanggu.org.hospitaldevicemonitor.R;
import fanggu.org.hospitaldevicemonitor.adapter.MenuGridDataAdapter;
import fanggu.org.hospitaldevicemonitor.itemDecoration.MyItemDecoration;

public class HomePageFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[][] dataArr=new String[][]{
                {"设备巡检",""+R.drawable.xunjian},
                {"设备维修",""+R.drawable.weixiu},
                {"设备保养",""+R.drawable.baoyang},
                {"交接班",""+R.drawable.jiaojieban},
                {"故障报修",""+R.drawable.baoxiu},
                {"报警信息",""+R.drawable.baojing},
                {"临时报修",""+R.drawable.linshibaoxiu},
                {"设备档案",""+R.drawable.shebeidangan},
                {"数据下载",""+R.drawable.xiazai},
                {"数据上传",""+R.drawable.shangchuan},
                {"合同提醒",""+R.drawable.hetong},
                {"意见建议",""+R.drawable.yijian},
                {"满意度调查",""+R.drawable.manyidu}
        };
        View root = inflater.inflate(R.layout.homepage_fragment, container, false);
        //初始化RecyclerView
        RecyclerView menuRecyclerView = (RecyclerView) root.findViewById(R.id.menuRecyclerView);

        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思</span>
        GridLayoutManager layoutmanager = new GridLayoutManager(root.getContext(),4);
        layoutmanager.setOrientation(GridLayoutManager.VERTICAL);
        //设置RecyclerView 布局管理器
        menuRecyclerView.setLayoutManager(layoutmanager);

        //把我们自定义的ItemDecoration设置给RecyclerView
        menuRecyclerView.addItemDecoration(new MyItemDecoration());

        //设置设置RecyclerView的适配器Adapter
        MenuGridDataAdapter adapter = new MenuGridDataAdapter(dataArr);
        menuRecyclerView.setAdapter(adapter);

        return root;
    }

}