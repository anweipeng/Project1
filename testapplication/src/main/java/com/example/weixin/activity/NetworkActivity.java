package com.example.weixin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weixin.R;
import com.example.weixin.util.NetWorkUtils;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        NetWorkUtils.isNetworkConnected(this);




    }
}
