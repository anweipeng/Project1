package com.chinaztt.fda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.test.RecyclerViewRefresh.RecyclerFootActivity;
import com.chinaztt.fda.test.RecyclerViewRefresh.RecyclerRefreshActivity;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;


/**
 * 当前类注释:RecyclerView 完全解析相关实例入口
 * 主要有1.RecyclerView基础以及高级使用;
 * 2.Recycler实战实例打造Gallery;
 * 3.Recycler结合AA(AndroidAnnotations)注入框架实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/19 16:57
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class RecyclerDemoActivity  extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_demo_layout);
        TextView top_bar_title=findViewById(R.id.top_bar_title);
        top_bar_title.setText("RecyclerView测试入口");
    }

    public void clickButton(View view){
        switch (view.getId()){
            case R.id.btn_four:
                Intent refreshIntent=new Intent(this,RecyclerRefreshActivity.class);
                this.startActivity(refreshIntent);
                break;
            case R.id.btn_five:
                Intent fiveIntent=new Intent(this,RecyclerFootActivity.class);
                this.startActivity(fiveIntent);
                break;
        }
    }




}
