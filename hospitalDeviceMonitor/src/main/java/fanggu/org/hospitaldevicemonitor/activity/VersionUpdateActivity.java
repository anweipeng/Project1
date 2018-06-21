package fanggu.org.hospitaldevicemonitor.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import fanggu.org.hospitaldevicemonitor.R;
import fanggu.org.hospitaldevicemonitor.service.UpdateService;
import fanggu.org.hospitaldevicemonitor.util.OkHttp3Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 版本更新
 */
public class VersionUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_update);

        TextView versionCodeView = findViewById(R.id.versionCode);

        TextView versionNameView = findViewById(R.id.versionName);
        try {
            int versionCode=getVersionCode();
            String versionName=getVersionName();
            versionCodeView.setText(versionCode+"");
            versionNameView.setText(versionName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        showAlertDialog();

    }


    /**
     *
     * @param view
     */
    public void beginUpdate(View view) {
        udpate();
    }

    public void udpate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //启动服务
                Intent service = new Intent(VersionUpdateActivity.this,UpdateService.class);
                startService(service);
            }
        }).start();
    }


    /**
     * 获取versionId
     *
     * @return
     */
    private String getVersionName() throws Exception{
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }

    private int getVersionCode() throws Exception{
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionCode;
    }

    public void showAlertDialog(){

        String content = "\n" +
                "更新内容\n" +
                "\n" +
                "----------万能的分割线-----------\n" +
                "\n" +
                "1.下架商品误买了？恩。。。我搞了点小动作就不会出现了\n" +
                "2.侧边栏、弹框优化 —— 这个你自己去探索吧，总得留点悬念嘛-。-\n";//更新内容


        new AlertDialog.Builder(this)
                .setTitle("版本更新")
                .setMessage(content)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        udpate();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void getServerVersionInfo(){
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

            }
        };

        OkHttp3Utils.doSynPost("",null,new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    try {
                        Message messageHand = handler.obtainMessage(0,responseStr);
                        handler.sendMessage(messageHand);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("getTimeNO onFailure！！！！！");
            }
        });
    }

}
