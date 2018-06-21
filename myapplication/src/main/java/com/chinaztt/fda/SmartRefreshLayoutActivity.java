package com.chinaztt.fda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chinaztt.fda.adapter.MyAdapter;
import com.chinaztt.fda.bean.ItemBean;
import com.chinaztt.fda.ui.R;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SmartRefreshLayoutActivity extends AppCompatActivity {

    private List<ItemBean> list;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    RefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh_layout);
        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        initDate();
        setPullRefresher();
    }


    private void initDate(){
        list = new ArrayList<ItemBean>();
        for (int i=0;i<20;i++){
            list.add(new ItemBean(
                    R.mipmap.ic_launcher,
                    "initTitle"+i,
                    System.currentTimeMillis()+""
            ));
        }
        myAdapter = new MyAdapter(list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);//纵向线性布局

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }


    private void setPullRefresher(){
        //设置 Header 为 MaterialHeader
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //在这里执行上拉刷新时的具体操作(网络请求、更新UI等)

                //模拟网络请求到的数据
                ArrayList<ItemBean> newList = new ArrayList<ItemBean>();
                for (int i=0;i<20;i++){
                    newList.add(new ItemBean(
                            R.mipmap.ic_launcher,
                            "newTitle"+i,
                            System.currentTimeMillis()+""
                    ));
                }
                myAdapter.refresh(newList);
                refreshlayout.finishRefresh(100);
                //不传时间则立即停止刷新    传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                //模拟网络请求到的数据
                ArrayList<ItemBean> newList = new ArrayList<ItemBean>();
                for (int i=0;i<20;i++){
                    newList.add(new ItemBean(
                            R.mipmap.ic_launcher,
                            "addTitle"+i,
                            System.currentTimeMillis()+""
                    ));
                }
                myAdapter.add(newList);
                //在这里执行下拉加载时的具体操作(网络请求、更新UI等)
                refreshlayout.finishLoadmore(100/*,false*/);//不传时间则立即停止刷新    传入false表示加载失败
            }
        });
    }
}

