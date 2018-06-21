package com.example.weixin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * 2016年7月26日17:20:13
 * @author 小瓶盖 blog    http://blog.csdn.net/qq_25193681/article/details/52005375
 *
 */
public class SecondSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

	/**
	 * 是否处于绘制状态
	 */
	private boolean mIsDrawing;
	/**
	 * 帮助类
	 */
	private SurfaceHolder mHolder;
	/**
	 * 画布
	 */
	private Canvas mCanvas;
	/**
	 * 路径
	 */
	private Path mPath;
	/**
	 * 画笔
	 */
	private Paint mPaint;

	public SecondSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public SecondSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SecondSurfaceView(Context context) {
		super(context);
		initView();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int x=(int) event.getX();
		int y=(int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPath.moveTo(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			mPath.lineTo(x, y);
			break;
		case MotionEvent.ACTION_UP:

			break;
		default:
			break;
		}

		return true;
	}

	private void initView() {
		mHolder=getHolder();
		mHolder.addCallback(this);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setKeepScreenOn(true);

		mPath=new Path();

		mPaint=new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(15);
	}

	@Override
	public void run() {
		long start =System.currentTimeMillis();
		while(mIsDrawing){
			draw();
		}
		long end =System.currentTimeMillis();
		if (end-start<100) {
			try {
				Thread.sleep(100-(end-start));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {


	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		mIsDrawing=true;
		new Thread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		mIsDrawing=false;

	}
	private void draw(){
		try {
			mCanvas=mHolder.lockCanvas();
			mCanvas.drawColor(Color.WHITE);
			mCanvas.drawPath(mPath, mPaint);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (mCanvas!=null) {
				mHolder.unlockCanvasAndPost(mCanvas);
			}
		}
	}
	/**
	 * 清除内容
	 */
	public void clean(){
		initView();
	}
}

