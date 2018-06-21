package fanggu.org.lotteryapplication.customview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import fanggu.org.lotteryapplication.R;
import fanggu.org.lotteryapplication.activity.PurchaseActivity;
import fanggu.org.lotteryapplication.util.BusiUtil;

/**
 * Created by 77386 on 2018/5/21.
 */
public class CustomScrollerLayout05281130 extends LinearLayout {



    //1,定义GestureDetector类
    private GestureDetector m_gestureDetector;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;

    /**
     * 手机按下时的屏幕坐标
     */
    private float mXDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;

    public CustomScrollerLayout05281130(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewConfiguration configuration=ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop=configuration.getScaledPagingTouchSlop(); //getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件

        //设置为可点击
        setClickable(true);
        //2,初始化手势类，同时设置手势监听
        m_gestureDetector=new GestureDetector(context, onGestureListener);

    }
    private static final int FLING_MIN_DISTANCE = 20;//mListView  滑动最小距离
    private static final int FLING_MIN_VELOCITY = 200;//mListView 滑动最大速度
    private int m_max_scrollX;
    //初始化手势监听对象，使用GestureDetector.OnGestureListener的实现抽象类，因为实际开发中好多方法用不上
    private final GestureDetector.OnGestureListener onGestureListener=new GestureDetector.SimpleOnGestureListener() {
        /*@Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("GestureDemoView", "onScroll() distanceY=" + distanceY + " getScrollY=" + getScrollY() + " max_scrollX=" + m_max_scrollX);
            history_layout.scrollBy(0,(int) distanceY);
            return true;
        }*/
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            isFling=true;
            if(e2.getY()-e1.getY()>0) {  //快速向下滑动,滑动下面
                Log.d("GestureDemoView2快速向下滑动", "onFling();e1.getY():"+e1.getY()+";e2.getY():"+e2.getY()+";velocityY="+velocityY);

                distance=0-history_layout.getScrollY();
                duration=Math.abs(fullDuration/ maxYMoveDistance *distance);
                history_layout.getThisScroller().startScroll(0, (int) history_layout.getScrollY(),0,distance, duration);

                layoutScrollView(-1,duration);

            }else { //快速向上滑动,滑到上面
                Log.d("GestureDemoView2快速向上滑动", "onFling();e1.getY():"+e1.getY()+";e2.getY():"+e2.getY()+"velocityY="+velocityY);

                distance= maxYMoveDistance -history_layout.getScrollY();
                duration=Math.abs(fullDuration/ maxYMoveDistance *distance);
                history_layout.getThisScroller().startScroll(0, (int) history_layout.getScrollY(),0,distance, duration);

                layoutScrollView(1,duration);
            }

            yLastMove=e2.getY();
            return true;
        }
    };

    float eventDownRawY=0;  //手指按下时的y轴坐标
    /**Log.d("GestureDemoView快速向下滑动", "onFling();e1.getY():"+e1.getY()+";e2.getY():"+e2.getY()+"velocityY="+velocityY);

     * 结束拖动时手指的y轴坐标
     */
    float  eventYEnd;  //结束拖动时手指的y轴坐标
    float yLastMove=0;  //上次触发ACTION_MOVE事件时的屏幕y坐标
    int historyScrollY=0;  // history_layout 相对自己初始位置滑动的距离。初始historyScrollY为0,上滑为正,下滑为负,

    int fingerScrolledY=0;  // 上滑为正,下滑为负.
    private View purchaseScrollViewParent;
    private HistoryLayout history_layout;
    private LinearLayout opened_time_layout;
    int maxYMoveDistance;  //可滑动的最大y轴距离

    int distance=0; //需要继续滑动的距离
    int fullDuration=1000;
    int duration=0; //滑动继续滑动的时间
    boolean isFling=false;

    int minTopOfScrollView =0;  //最小高度
    int maxTopOfScrollView=0;   //最大高度

    /**
     *
     * @param orientation 负数为向下滑动,正数为向上滑动
     */
    public void layoutScrollView(final int orientation, int duration){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LayoutParams layoutParams = (LayoutParams)purchaseScrollViewParent.getLayoutParams();

                PurchaseActivity activity=(PurchaseActivity)getContext();
                BusiUtil.adjustPurchaseScrollViewParentPosition(activity);
/*
                if(orientation<0){  //向下滑动
                    if(purchaseScrollViewParent.getTop()<maxTopOfScrollView){
                        // scrollView向下移动
                        purchaseScrollViewParent.layout(purchaseScrollViewParent.getLeft(), purchaseScrollViewParent.getTop()+maxYMoveDistance, purchaseScrollViewParent.getRight(), purchaseScrollViewParent.getBottom()+maxYMoveDistance);
                    }
                }else if(orientation>0){  //向上滑动
                    if(purchaseScrollViewParent.getTop()>minTopOfScrollView){
                        // scrollView向上移动
                        purchaseScrollViewParent.layout(purchaseScrollViewParent.getLeft(), purchaseScrollViewParent.getTop()-maxYMoveDistance, purchaseScrollViewParent.getRight(), purchaseScrollViewParent.getBottom()-maxYMoveDistance);
                    }
                }
                */



            }
        }, duration);//3秒后执行Runnable中的run方法
    }

    @Override
     public boolean onTouchEvent(MotionEvent event) {

        if(purchaseScrollViewParent ==null){
            purchaseScrollViewParent = ((View) getParent().getParent()).findViewById(R.id.purchaseScrollViewParent);
            minTopOfScrollView = purchaseScrollViewParent.getTop();
        }
        if(history_layout==null){
            history_layout= (HistoryLayout)getParent();
        }
        if(opened_time_layout==null){
            opened_time_layout=findViewById(R.id.opened_time_layout);
        }

        //滑动的最大距离
        maxYMoveDistance =history_layout.getHeight()-opened_time_layout.getHeight();


        //3,将touch事件交给gesture处理
        m_gestureDetector.onTouchEvent(event);


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isFling=false;
                eventDownRawY=event.getRawY();
                yLastMove=eventDownRawY;
                historyScrollY=history_layout.getScrollY();          // 初始位置为0,上滑为正,下滑为负.初始historyScrollY为0,最大值为maxYMoveDistance


                if(historyScrollY==maxYMoveDistance){  //走势图在最上面

                    LayoutParams layoutParams = (LayoutParams)purchaseScrollViewParent.getLayoutParams();

                    // scrollView立刻滑动到下面
                    layoutParams.topMargin=layoutParams.topMargin+maxYMoveDistance;
                    purchaseScrollViewParent.setLayoutParams(layoutParams);

//                  purchaseScrollViewParent.layout(purchaseScrollViewParent.getLeft(), purchaseScrollViewParent.getTop()+maxYMoveDistance, purchaseScrollViewParent.getRight(), purchaseScrollViewParent.getBottom()+maxYMoveDistance);




                    if(maxTopOfScrollView==0){
                        maxTopOfScrollView= purchaseScrollViewParent.getTop();
                    }
                }


                break;

            case MotionEvent.ACTION_MOVE:
                isFling=false;
                historyScrollY=history_layout.getScrollY();          // 初始位置为0,上滑为正,下滑为负.初始historyScrollY为0,最大值为maxYMoveDistance

                eventYEnd=event.getRawY();  //结束拖动时手指的y轴坐标
                fingerScrolledY=(int) (yLastMove - eventYEnd); // 上滑为正,下滑为负.

                if(historyScrollY+fingerScrolledY<=0){ // 超过往下滑的极限
                    history_layout.scrollTo(0,0);
                    return true;
                }else if (historyScrollY +  fingerScrolledY > maxYMoveDistance) {  //超过往上滑的极限
                    history_layout.scrollTo(0, maxYMoveDistance);
                    return true;
                }else{
                    //滑动到指定位置
                    history_layout.scrollBy(0, fingerScrolledY);
                    System.err.println("#####################_(historyScrollY+fingerScrolledY):"+(historyScrollY +  fingerScrolledY)+";history_layout.getScrollY():"+historyScrollY+";eventDownRawY:"+eventDownRawY+";eventYEnd:"+eventYEnd+";fingerScrolledY:"+fingerScrolledY);
                    yLastMove=eventYEnd;
                }
                break;
           case MotionEvent.ACTION_UP:   // 当手指抬起时，继续滚动，根据当前的滚动值来判定应该滚动到哪个子控件的界面
               eventYEnd=event.getRawY();  //结束拖动时手指的y轴坐标
               fingerScrolledY=(int) (yLastMove - eventYEnd); // 上滑为正,下滑为负.
               System.err.println("############手指抬起,isFling为"+isFling+";historyScrollY +  fingerScrolledY:"+(historyScrollY +  fingerScrolledY)+";history_layout.getScrollY():"+historyScrollY+";eventDownRawY:"+eventDownRawY+";eventYEnd:"+eventYEnd+";fingerScrolledY:"+fingerScrolledY);

               historyScrollY=history_layout.getScrollY();  // 初始位置为0,上滑为正,下滑为负.初始historyScrollY为0,最大值为maxYMoveDistance
               if(isFling){  //若已处理为fling则不进行处理
                   break;
               }

               if(Math.abs(fingerScrolledY)<10) {  //判断为点击:移动距离小于10,判断为点击,不是滑动
                   System.err.println("*******判断为点击,fingerScrolledY为"+fingerScrolledY+";getScrollY:"+history_layout.getScrollY());
                   if(historyScrollY>= maxYMoveDistance /2){  // 点击,   滚动到1/2界限上方,滑动到下面
                       System.err.println("在1/2界限上方,滑动到下面");
                       distance=0-history_layout.getScrollY();
                       duration=Math.abs(fullDuration/ maxYMoveDistance *distance);
                       history_layout.getThisScroller().startScroll(0, (int) historyScrollY,0,distance, duration);


                       layoutScrollView(-1,duration);


                   }else{ //点击,   滚动到1/2界限下方,滑动到上面
                       System.err.println("在1/2界限下方,滑动到上面");
                       distance= maxYMoveDistance -history_layout.getScrollY();
                       duration=Math.abs(fullDuration/ maxYMoveDistance *distance);
                       history_layout.getThisScroller().startScroll(0, (int) historyScrollY,0,distance, duration);
                       layoutScrollView(1,duration);
                   }

               }else{ //判断为滑动
                   if(historyScrollY>= maxYMoveDistance /2){  //向上滑动到大于1/2界限处,滑动到上边
                       System.err.println("*******向上滑动fingerScrolledY为"+fingerScrolledY);
                       distance= maxYMoveDistance -history_layout.getScrollY();
                       duration=Math.abs(fullDuration/ maxYMoveDistance *distance);
                       history_layout.getThisScroller().startScroll(0, (int) historyScrollY,0,distance, duration);


                       layoutScrollView(1,duration);

                   }else{  //滑动到下边
                       System.err.println("*******向下滑动fingerScrolledY"+fingerScrolledY);
                       distance=0-history_layout.getScrollY();
                       duration=Math.abs(fullDuration/ maxYMoveDistance *distance);
                       history_layout.getThisScroller().startScroll(0, (int) historyScrollY,0,distance, duration);


                       layoutScrollView(-1,duration);
                   }
               }
                break;
        }

        history_layout.invalidate(); //必须要写
        return true;
    }

}
