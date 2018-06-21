package com.example.weixin.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.weixin.R;
import com.example.weixin.adapter.MyListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * SwipeRefreshLayout
 * SwipeRefreshLayout是一个可以支持下拉刷新的布局控件，其中只能有一个子视图
 */
public class SwipeRefreshLayoutActivity  extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    private List<String> data;

    private MyListAdapter adapter;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srh);
        listView = (ListView) findViewById(R.id.lv);

        initView();
        initEvents();
    }



    private void initView() {

        // 初始化ListView中的数据
        data = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            data.add("List Item " + i);
        }


        // 为SwipeRefreshLayout设置一些参数
        // 设置刷新圆圈的颜色（最多只能有四种颜色）
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        // 为ListView设置适配器
        adapter = new MyListAdapter(this, data);
        listView.setAdapter(adapter);
        // 初始化Handler
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    // 在ListView中添加两条数据
                    data.add(0, "New Item..."+ System.currentTimeMillis()+"_001");
                    data.add(0, "New Item..."+ System.currentTimeMillis()+"_002");
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                    // 取消SwipeRefreshLayout的刷新状态
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        };
    }

    private void initEvents() {
        // 设置SwipeRefreshLayout的刷新事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            mHandler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


    }
}