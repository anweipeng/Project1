package com.example.weixin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class BallActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new BallSurfaceView(this));
    }

    class BallSurfaceView extends SurfaceView implements Callback,Runnable{
        private int screenW;		//屏幕宽度
        private int screenH;		//屏幕高度
        private Paint paint; 		//定义画笔
        private float cx = 50;		//圆点默认X坐标
        private float cy = 50;		//圆点默认Y坐标
        private int radius = 20;
        //定义颜色数组
        private int colorArray[] = {Color.BLACK,Color.BLACK,Color.GREEN,Color.YELLOW, Color.RED};
        private int paintColor = colorArray[0]; //定义画笔默认颜色
        private Canvas canvas = null; //定义画布
        private Thread th = null;     //定义线程
        private SurfaceHolder sfh = null;

        public BallSurfaceView(Context context){
            super(context);
            /*备注1：在此处获取屏幕高、宽值为0，以为此时view还未被创建，
             * 在接口Callback的surfaceCreated方法中view才被创建
             */
    		/*screenW = getWidth();
    		screenH = getHeight();*/

            //初始化画笔
            initPaint();
            sfh = getHolder();
            sfh.addCallback(this);
            th = new Thread(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            //获取屏幕宽度
            screenW = getWidth();
            //获取屏幕高度
            screenH = getHeight();
            //启动绘图线程
            th.start();
        }

        private void initPaint(){
            paint = new Paint();
            //设置消除锯齿
            paint.setAntiAlias(true);
            //设置画笔颜色
            paint.setColor(paintColor);
        }

        @Override
        public void run() {
            while(true){
                try{
                    myDraw();
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        /*备注2：切记，在自定SurfaceView中定义的myDraw方法，自定义View（继承自View的子类）中的onDraw方法
         * 完全是两码事：
         * 1）自定义View（继承自View的子类）中的onDraw方法是重写父类的onDraw方法，在调用postInvalidate后会自动回调该onDraw()方法。
         * 2）此处的myDraw方法需要手动调用，所以此处故意将方法命名为myDraw，突出为该方法是自己写的，非重写父类的方法 。
         *
         */
        //重写onDraw方法实现绘图操作
        protected void myDraw() {
            //获取canvas实例
            canvas = sfh.lockCanvas();
            //将屏幕设置为白色
            canvas.drawColor(Color.WHITE);
            //修正圆点坐标
            revise();
            //随机设置画笔颜色
            setPaintRandomColor();
            //绘制小圆作为小球
            canvas.drawCircle(cx, cy, radius, paint);
            //将画好的画布提交
            sfh.unlockCanvasAndPost(canvas);
        }

        //为画笔设置随机颜色
        private void setPaintRandomColor(){
            Random rand = new Random();
            int randomIndex = rand.nextInt(colorArray.length);
            paint.setColor(colorArray[randomIndex]);
        }

        //修正圆点坐标
        private void revise(){
            if(cx <= radius){
                cx = radius;
            }else if(cx >= (screenW-radius)){
                cx = screenW-radius;
            }
            if(cy <= radius){
                cy = radius;
            }else if(cy >= (screenH-radius)){
                cy = screenH-radius;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 按下
                    cx = (int) event.getX();
                    cy = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    // 移动
                    cx = (int) event.getX();
                    cy = (int) event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    // 抬起
                    cx = (int) event.getX();
                    cy = (int) event.getY();
                    break;
            }

            /*
             * 备注1：次处一定要将return super.onTouchEvent(event)修改为return true，原因是：
             * 1）父类的onTouchEvent(event)方法可能没有做任何处理，但是返回了false。
             * 2)一旦返回false，在该方法中再也不会收到MotionEvent.ACTION_MOVE及MotionEvent.ACTION_UP事件。
             */
            //return super.onTouchEvent(event);
            return true;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }
}
