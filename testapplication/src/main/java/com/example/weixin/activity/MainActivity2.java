package com.example.weixin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.example.weixin.R;
import com.example.weixin.view.SecondSurfaceView;

/**
 * 2016年7月26日17:20:13
 * @author 小瓶盖 blog    http://blog.csdn.net/qq_25193681/article/details/52005375
 *
 */
public class MainActivity2 extends Activity{
	SecondSurfaceView surfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		surfaceView=(SecondSurfaceView)findViewById(R.id.surfaceview);
	}

	public void onClick(View v){
		surfaceView.clean();
	}

}
