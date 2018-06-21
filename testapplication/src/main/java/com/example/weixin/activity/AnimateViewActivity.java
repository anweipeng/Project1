package com.example.weixin.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.weixin.R;

public class AnimateViewActivity extends Activity {
    int centerY,centerX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new AnimateView(this));//這邊傳入的this代表這個對象，因為Activity是繼承自Content類的，因此該對象也可向上轉型為Content類型作為AnimateView的構造方法的參數
    }

    class AnimateView extends View {

        float radius = 10;
        Paint paint;

        public AnimateView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(2);

            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            centerX=getWindowManager().getDefaultDisplay().getWidth()/2;
            centerY=getWindowManager().getDefaultDisplay().getHeight()/2;
            int widthScreen=getWindowManager().getDefaultDisplay().getWidth();
            int heightScreen=getWindowManager().getDefaultDisplay().getHeight();


            canvas.drawLine(0,centerY,widthScreen,centerY,paint);  //水平的线
            canvas.drawLine(centerX,0,centerX,heightScreen,paint); //竖直的线

//            canvas.translate(centerX, centerY);

            canvas.drawCircle(centerX, centerY, radius++, paint);

            if (radius > 500) {
                radius = 10;
            }

            invalidate();//通过调用这个方法让系统自动刷新视图
        }

    }

}

