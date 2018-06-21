package fanggu.org.hospitaldevicemonitor.activity;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.widget.EditText;


import java.util.Timer;
import java.util.TimerTask;

import fanggu.org.hospitaldevicemonitor.R;
import fanggu.org.hospitaldevicemonitor.receiver.ConnectionChangeReceiver;
import fanggu.org.hospitaldevicemonitor.util.ACache;

public class ACacheActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_acache);

        initView();//初始化界面，代 码不贴了

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
//        filter.addAction("android.net.wifi.STATE_CHANGE");

        ConnectionChangeReceiver connectionChangeReceiver=new ConnectionChangeReceiver();
        registerReceiver(connectionChangeReceiver,filter);
    }

    private void initView() {
        final ACache mCache = ACache.get(this);
        mCache.put("test_key1", "test value");
        mCache.put("test_key2", "test value", 5);//保存10秒，如果超过10秒去获取这个key，将为null
        mCache.put("test_key3", "test value", 2 * ACache.TIME_DAY);//保存两天，如果超过两天去获取这个key，将为null


        System.out.println();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                EditText userNameView = findViewById(R.id.userName);
                Editable text = userNameView.getText();
                String test_key2=mCache.getAsString("test_key1");


                userNameView.setText(text+";\n"+test_key2);
            }
        };

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = System.currentTimeMillis();
                handler.sendMessage(message);
            }
        };

        Timer timer = new Timer();
        // 参数：
        // 1000，延时1秒后执行。
        // 2000，每隔2秒执行1次task。
        timer.schedule(task, 1000, 2000);
    }


}