package fanggu.org.lotteryapplication.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fanggu.org.lotteryapplication.activity.PurchaseActivity;
import fanggu.org.lotteryapplication.R;
import fanggu.org.lotteryapplication.adapter.LotteryHistoryDataAdapter;
import fanggu.org.lotteryapplication.adapter.LotteryHistoryTitleAdapter;
import fanggu.org.lotteryapplication.customview.MyGridView;

/**
 * Created by 77386 on 2018/5/26.
 */
public class BusiUtil {
    private static Toast toast;
    /**
     * 新建textView
     * @param context
     * @param layoutParams
     * @param TypedValue
     * @param textSize
     * @param textColor
     * @param text
     * @return
     */
    public static TextView newTextView(Context context,ViewGroup.LayoutParams layoutParams,int TypedValue, int textSize, String textColor, String text){
        TextView textView=new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(TypedValue,textSize);
        textView.setTextColor(Color.parseColor(textColor));
        textView.setText(text);
        return textView;
    }

    /**
     * 新建WRAP_CONTENT的textView
     * @param context
     * @param textSize
     * @param textColor
     * @param text
     * @return
     */
    public static TextView newWRAP_CONTENTTextView(Context context,int textSize, String textColor, String text){
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView textView=new TextView(context);
        textView.setLayoutParams(vlp);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        textView.setTextColor(Color.parseColor(textColor));
        textView.setText(text);
        return textView;
    }

    /**
     * 调整 purchaseScrollViewParent的位置,重绘会导致自身回到原点
     * @param activity
     */
    public static  void  adjustPurchaseScrollViewParentPosition(PurchaseActivity activity){
        View history_layout=activity.findViewById(R.id.history_layout);
        View opened_time_layout=activity.findViewById(R.id.opened_time_layout);
        LinearLayout purchaseScrollViewParent=(LinearLayout)activity.findViewById(R.id.purchaseScrollViewParent);
        int maxYMoveDistance=history_layout.getHeight()-opened_time_layout.getHeight();
        int historyScrollY=history_layout.getScrollY();          // 初始位置为0,上滑为正,下滑为负.初始historyScrollY为0,最大值为maxYMoveDistance
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)purchaseScrollViewParent.getLayoutParams();
        if(historyScrollY==0){ //走势图已经滑下来了
            if(layoutParams.topMargin==0){ //玩法视图回到上方原位

                //玩法视图下移
                layoutParams.topMargin=layoutParams.topMargin+maxYMoveDistance;
                purchaseScrollViewParent.setLayoutParams(layoutParams);
            }
        }else{  //走势图已经滑上去了
            if(layoutParams.topMargin>0){  //玩法视图已经下移
                //玩法视图上移
                layoutParams.topMargin = layoutParams.topMargin - maxYMoveDistance;
                purchaseScrollViewParent.setLayoutParams(layoutParams);
            }
        }
    }

    /**
     * 显示普通玩法界面,适用于:任选一、任选二....
     * @param activity
     */
    public static void showCommonPlayView(PurchaseActivity activity){
        activity.findViewById(R.id.shakeLayout).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.renXuanInclude).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.bonusTipLayout).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.wanQianBaiWeiInclude).setVisibility(View.GONE);
        activity.findViewById(R.id.danTuoInclude).setVisibility(View.GONE);

        adjustPurchaseScrollViewParentPosition(activity);
        System.out.println();
    }


    /**
     * 显示万位、千位、百位界面
     * @param activity
     */
    public static void showWanQianBaiWeiPlayView(PurchaseActivity activity){
        activity.findViewById(R.id.shakeLayout).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.wanQianBaiWeiInclude).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.bonusTipLayout).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.renXuanInclude).setVisibility(View.GONE);
        activity.findViewById(R.id.danTuoInclude).setVisibility(View.GONE);

        activity.findViewById(R.id.baiWeiParent).setVisibility(View.VISIBLE);


        ((TextView)activity.findViewById(R.id.firstTextView)).setText("万位");
        ((TextView)activity.findViewById(R.id.secondTextView)).setText("千位");
        ((TextView)activity.findViewById(R.id.thirdTextView)).setText("百位");


        adjustPurchaseScrollViewParentPosition(activity);

    }

    /**
     * 显示万位、千位界面
     * @param activity
     */
    public static void showWanQianWeiPlayView(PurchaseActivity activity){
        activity.findViewById(R.id.shakeLayout).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.wanQianBaiWeiInclude).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.bonusTipLayout).setVisibility(View.VISIBLE);

        activity.findViewById(R.id.renXuanInclude).setVisibility(View.GONE);
        activity.findViewById(R.id.danTuoInclude).setVisibility(View.GONE);

        activity.findViewById(R.id.baiWeiParent).setVisibility(View.GONE);


        ((TextView)activity.findViewById(R.id.firstTextView)).setText("万位");
        ((TextView)activity.findViewById(R.id.secondTextView)).setText("千位");
        ((TextView)activity.findViewById(R.id.thirdTextView)).setText("百位");


        adjustPurchaseScrollViewParentPosition(activity);

    }

    /**
     * 显示三码通玩法界面
     * @param activity
     */
    public static void showSanMaTongPlayView(PurchaseActivity activity){
        activity.findViewById(R.id.shakeLayout).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.bonusTipLayout).setVisibility(View.VISIBLE);

        activity.findViewById(R.id.wanQianBaiWeiInclude).setVisibility(View.VISIBLE);

        activity.findViewById(R.id.renXuanInclude).setVisibility(View.GONE);
        activity.findViewById(R.id.danTuoInclude).setVisibility(View.GONE);

        ((TextView)activity.findViewById(R.id.firstTextView)).setText("选号");
        ((TextView)activity.findViewById(R.id.secondTextView)).setText("选号");
        ((TextView)activity.findViewById(R.id.thirdTextView)).setText("选号");

        adjustPurchaseScrollViewParentPosition(activity);
    }


    /**
     * 显示胆拖玩法界面
     * @param activity
     */
    public static void showDanTuoPlayView(PurchaseActivity activity){
        activity.findViewById(R.id.danTuoInclude).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.bonusTipLayout).setVisibility(View.GONE);
        activity.findViewById(R.id.wanQianBaiWeiInclude).setVisibility(View.GONE);
        activity.findViewById(R.id.renXuanInclude).setVisibility(View.GONE);
        activity.findViewById(R.id.shakeLayout).setVisibility(View.GONE);

        adjustPurchaseScrollViewParentPosition(activity);
    }



    /**
     * 根据选择的玩法获取玩法名称
     * @param activity
     * @return
     */
    public static String getPlayNameByPlayType(PurchaseActivity activity){
        TextView playTypeNameTextView=activity.findViewById(R.id.playTypeName);
        String playName="";
        if(playTypeNameTextView.getText().equals("任选一")){
            playName=PlayTypeNameStr.OptOne;
        }else if(playTypeNameTextView.getText().equals("任选二")) {
            playName=PlayTypeNameStr.OptTwo;
        }else if(playTypeNameTextView.getText().equals("任选三")) {
            playName=PlayTypeNameStr.OptThree;
        }else if(playTypeNameTextView.getText().equals("任选四")) {
            playName=PlayTypeNameStr.OptFour;
        }else if(playTypeNameTextView.getText().equals("任选五")) {
            playName=PlayTypeNameStr.OptFive;
        }else if(playTypeNameTextView.getText().equals("任选六")) {
            playName=PlayTypeNameStr.OptSix;
        }else if(playTypeNameTextView.getText().equals("任选七")) {
            playName=PlayTypeNameStr.OptSenve;
        }else if(playTypeNameTextView.getText().equals("任选八")) {
            playName=PlayTypeNameStr.OptEight;
        }else if(playTypeNameTextView.getText().equals("前二直选")) {
            playName=PlayTypeNameStr.FrontTwo;
        }else if(playTypeNameTextView.getText().equals("前二组选")) {
            playName=PlayTypeNameStr.FrontTwoGroup;
        }else if(playTypeNameTextView.getText().equals("前三直选")) {
            playName=PlayTypeNameStr.FrontThree;
        }else if(playTypeNameTextView.getText().equals("前三组选")) {
            playName=PlayTypeNameStr.FrontThreeGroup;
        }else if(playTypeNameTextView.getText().equals("三码通")) {
            playName=PlayTypeNameStr.ThreeNOFree;
        }else if(playTypeNameTextView.getText().equals("乐选四")) {
            playName=PlayTypeNameStr.HappyFour;
        }else if(playTypeNameTextView.getText().equals("乐选五")) {
            playName=PlayTypeNameStr.HappyFive;
        }else if(playTypeNameTextView.getText().equals("乐选六")) {
            playName=PlayTypeNameStr.HappySix;
        } else if (playTypeNameTextView.getText().equals("任选二胆拖")) {
            playName=PlayTypeNameStr.OptTwo;
        } else if (playTypeNameTextView.getText().equals("任选三胆拖")) {
            playName=PlayTypeNameStr.OptThree;
        } else if (playTypeNameTextView.getText().equals("任选四胆拖")) {
            playName=PlayTypeNameStr.OptFour;
        } else if (playTypeNameTextView.getText().equals("任选五胆拖")) {
            playName=PlayTypeNameStr.OptFive;
        } else if (playTypeNameTextView.getText().equals("任选六胆拖")) {
            playName=PlayTypeNameStr.OptSix;
        } else if (playTypeNameTextView.getText().equals("任选七胆拖")) {
            playName=PlayTypeNameStr.OptSenve;
        } else if (playTypeNameTextView.getText().equals("前二组选胆拖")) {
            playName=PlayTypeNameStr.FrontTwoGroup;
        } else if (playTypeNameTextView.getText().equals("前三组选胆拖")) {
            playName=PlayTypeNameStr.FrontThreeGroup;
        }
        return playName;
    }

    /**
     * 根据玩法获取“最少选择球的数量”
     * @param activity
     * @return
     */
    public static int getBallAtLeastSelectNumByPlayType(PurchaseActivity activity){
        TextView playTypeNameTextView=activity.findViewById(R.id.playTypeName);
        int ballAtLeastSelectNum=0;
        if(playTypeNameTextView.getText().equals("任选一")){
            ballAtLeastSelectNum=1;
        }else if(playTypeNameTextView.getText().equals("任选二")) {
            ballAtLeastSelectNum = 2;
        }else if(playTypeNameTextView.getText().equals("任选三")) {
            ballAtLeastSelectNum = 3;
        }else if(playTypeNameTextView.getText().equals("任选四")) {
            ballAtLeastSelectNum = 4;
        }else if(playTypeNameTextView.getText().equals("任选五")) {
            ballAtLeastSelectNum = 5;
        }else if(playTypeNameTextView.getText().equals("任选六")) {
            ballAtLeastSelectNum = 6;
        }else if(playTypeNameTextView.getText().equals("任选七")) {
            ballAtLeastSelectNum = 7;
        }else if(playTypeNameTextView.getText().equals("任选八")) {
            ballAtLeastSelectNum = 8;
        }else if(playTypeNameTextView.getText().equals("前二直选")) {
            ballAtLeastSelectNum = 1;
        }else if(playTypeNameTextView.getText().equals("前二组选")) {
            ballAtLeastSelectNum = 2;
        }else if(playTypeNameTextView.getText().equals("前三直选")) {
            ballAtLeastSelectNum = 1;
        }else if(playTypeNameTextView.getText().equals("前三组选")) {
            ballAtLeastSelectNum = 3;
        }else if(playTypeNameTextView.getText().equals("三码通")) {
            ballAtLeastSelectNum = 3;
        }else if(playTypeNameTextView.getText().equals("乐选四")) {
            ballAtLeastSelectNum = 4;
        }else if(playTypeNameTextView.getText().equals("乐选五")) {
            ballAtLeastSelectNum = 5;
        }else if(playTypeNameTextView.getText().equals("乐选六")) {
            ballAtLeastSelectNum = 6;
        }
        return ballAtLeastSelectNum;
    }


    /**
     * 设置所有任选小球为未选中状态
     * @param activity
     */
    public static void unSelectAllRenXuanBalls(PurchaseActivity activity){
        LinearLayout commonBallsParent=activity.findViewById(R.id.commonBallsParent);

        int childCount = commonBallsParent.getChildCount();
        for(int i=0;i<childCount;i++){

            View childView = commonBallsParent.getChildAt(i);

            Class<? extends View> aClass = childView.getClass();
            String simpleName = aClass.getSimpleName();

            if(!simpleName.equals("LinearLayout")){
                continue;
            }

            LinearLayout childLinearLayout=(LinearLayout)childView;

            int childCountOfLinearLayout = childLinearLayout.getChildCount();

            TextView ballTextView1= (TextView) childLinearLayout.getChildAt(0);
            Boolean isSelected = (Boolean) ballTextView1.getTag();
            if(isSelected!=null && isSelected==true){ //选中,设置为未选中
                ballTextView1.setTag(false);
                ballTextView1.setBackgroundDrawable(ballTextView1.getResources().getDrawable(R.drawable.common_ball_unselected));
                ((TextView)ballTextView1).setTextColor(Color.parseColor("#e64d3b"));  //文字变为红色
            }

            if(childCountOfLinearLayout==4){ //有4个子元素,即有上下两个球
                TextView ballTextView2= (TextView) childLinearLayout.getChildAt(2);
                Boolean isSelected2 = (Boolean) ballTextView2.getTag();
                if(isSelected2!=null && isSelected2==true){ //选中,设置为未选中
                    ballTextView2.setTag(false);
                    ballTextView2.setBackgroundDrawable(ballTextView2.getResources().getDrawable(R.drawable.common_ball_unselected));
                    ((TextView)ballTextView2).setTextColor(Color.parseColor("#e64d3b"));  //文字变为红色
                }
            }
        }
    }

    /**
     * 设置万位、千位、百位所有的小球为未选中状态
     * @param activity
     */
    public static void unSelectAllWanQianBaiBalls(PurchaseActivity activity) {
        MyGridView wanWeiGridView=activity.findViewById(R.id.wanWeiGridView);
        int countWan=wanWeiGridView.getCount();
        for(int i=0;i<countWan;i++){
            LinearLayout convertView = (LinearLayout)wanWeiGridView.getChildAt(i);
            if (convertView != null) {
                TextView ballTextView = (TextView) convertView.getChildAt(0);
                Boolean isSelected= (Boolean) ballTextView.getTag();
                if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                    ballTextView.setTag(false);
                    ballTextView.setBackgroundDrawable(ballTextView.getResources().getDrawable(R.drawable.common_ball_unselected));
                    ballTextView.setTextColor(Color.parseColor("#e64d3b"));  //文字变为红色
                }
            }
        }


        MyGridView qianWeiGridView=activity.findViewById(R.id.qianWeiGridView);
        int countQian=qianWeiGridView.getCount();
        for(int i=0;i<countQian;i++){
            LinearLayout convertView = (LinearLayout)qianWeiGridView.getChildAt(i);
            if (convertView != null) {
                TextView ballTextView = (TextView) convertView.getChildAt(0);
                Boolean isSelected= (Boolean) ballTextView.getTag();
                if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                    ballTextView.setTag(false);
                    ballTextView.setBackgroundDrawable(ballTextView.getResources().getDrawable(R.drawable.common_ball_unselected));
                    ballTextView.setTextColor(Color.parseColor("#e64d3b"));  //文字变为红色
                }
            }
        }

        MyGridView baiWeiGridView=activity.findViewById(R.id.baiWeiGridView);
        int countBai=baiWeiGridView.getCount();
        for(int i=0;i<countBai;i++){
            LinearLayout convertView = (LinearLayout)baiWeiGridView.getChildAt(i);
            if (convertView != null) {
                TextView ballTextView = (TextView) convertView.getChildAt(0);
                Boolean isSelected= (Boolean) ballTextView.getTag();
                if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                    unselectBall(ballTextView);
                }
            }
        }
    }

    public static void unSelectAllDanTuoBalls(PurchaseActivity activity){
        MyGridView danMaMyGridView=activity.findViewById(R.id.danMaMyGridView);
        int countWan=danMaMyGridView.getCount();
        for(int i=0;i<countWan;i++){
            LinearLayout convertView = (LinearLayout)danMaMyGridView.getChildAt(i);
            if (convertView != null) {
                TextView ballTextView = (TextView) convertView.getChildAt(0);
                Boolean isSelected= (Boolean) ballTextView.getTag();
                if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                    unselectBall(ballTextView);
                }
            }
        }
    }

    public static void unSelectAllBallsAndClearBetInfo(PurchaseActivity activity){
        unSelectAllRenXuanBalls(activity);    //设置所有任选玩法小球为未选中状态
        unSelectAllWanQianBaiBalls(activity); //设置所有万千百小球为未选中状态
        unSelectAllDanTuoBalls(activity);     //设置所有胆拖小球为未选中状态

        clearBetInfo(activity);              //清空注数和金额
    }

    /**
     * 清空注数和金额
     * @param activity
     */
    public static void clearBetInfo(PurchaseActivity activity){
        TextView betCountTextView=activity.findViewById(R.id.betCount);
        betCountTextView.setText("0");

        TextView betMoneyAmountTextView=activity.findViewById(R.id.betMoneyAmount);
        betMoneyAmountTextView.setText("0");
    }


    /**
     * 显示与选择的玩法相关的界面
     */
    public static void showRelevantViewOfPlayType(PurchaseActivity activity){
        unSelectAllBallsAndClearBetInfo(activity);

        TextView playTypeNameTextView=activity.findViewById(R.id.playTypeName);
        TextView ballNeedSelectNum=activity.findViewById(R.id.ballAtLeastSelectNumTextView);
        LinearLayout bonusTipLayout=activity.findViewById(R.id.bonusTipLayout);
        LinearLayout danMaTipLayout=activity.findViewById(R.id.danMaTipLayout);
        LinearLayout tuoMaTipLayout=activity.findViewById(R.id.tuoMaTipLayout);
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        String blackColor="#323232";
        String redColor="#b61e1d";
        String greyColor="#666666";
        String PlayName="";
        int ballAtLeastSelectNum=0;
        if(playTypeNameTextView.getText().equals("任选一")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);
            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对第1个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor, ConstantUtil.REXUAN_YI_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);

        }else if(playTypeNameTextView.getText().equals("任选二")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对任意"+ballAtLeastSelectNum+"个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.rexuan_ER_bonus+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);

        }else if(playTypeNameTextView.getText().equals("任选三")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对任意"+ballAtLeastSelectNum+"个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.rexuan_SAN_bonus+"元");


            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);

        }else if(playTypeNameTextView.getText().equals("任选四")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对任意"+ballAtLeastSelectNum+"个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.rexuan_SI_bonus+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
        }else if(playTypeNameTextView.getText().equals("任选五")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对全部"+ballAtLeastSelectNum+"个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.REXUAN_WU_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
        }else if(playTypeNameTextView.getText().equals("任选六")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"选号包含5个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.REXUAN_LIU_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
        }else if(playTypeNameTextView.getText().equals("任选七")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"选号包含5个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.REXUAN_QI_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
        }else if(playTypeNameTextView.getText().equals("任选八")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"选号包含5个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.REXUAN_BA_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
        }else if(playTypeNameTextView.getText().equals("前二直选")){
            BusiUtil.showWanQianWeiPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"按位猜对前2个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.REXUAN_QIANER_ZHIXUAN_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);

        }else if(playTypeNameTextView.getText().equals("前二组选")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对前2个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.REXUAN_QIANER_ZUXUAN_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
        }else if(playTypeNameTextView.getText().equals("前三直选")){
            BusiUtil.showWanQianBaiWeiPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"按位猜对前3个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.REXUAN_QIANSAN_ZHIXUAN_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);

        }else if(playTypeNameTextView.getText().equals("前三组选")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对前3个开奖号即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.REXUAN_QIANSAN_ZUXUAN_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
        }else if(playTypeNameTextView.getText().equals("三码通")){
            BusiUtil.showSanMaTongPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对开奖号前3码(按位或不按位)、任意3码即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.LEXUAN_SAN_ANY_SAN_BONUS+"~"+ConstantUtil.LEXUAN_SAN_QIANSAN_WITHORDER_BONUS+"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);

        }else if(playTypeNameTextView.getText().equals("乐选四")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对开奖号任意4码即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.LEXUAN_SI_WHOLE_SI_BONUS +"元");
            TextView tipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,",任意3码即中");  //new TextView(this);
            TextView tipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.LEXUAN_SI_ANY_SAN_BONUS +"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
            bonusTipLayout.addView(tipText3);
            bonusTipLayout.addView(tipText4);
        }else if(playTypeNameTextView.getText().equals("乐选五")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对开奖号5码即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.LEXUAN_WU_WHOLE_WU_BONUS +"元");
            TextView tipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,",任意4码即中");  //new TextView(this);
            TextView tipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.LEXUAN_WU_ANY_SI_BONUS +"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
            bonusTipLayout.addView(tipText3);
            bonusTipLayout.addView(tipText4);
        }else if(playTypeNameTextView.getText().equals("乐选六")){
            BusiUtil.showCommonPlayView(activity);

            ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);

            ballNeedSelectNum.setText(ballAtLeastSelectNum+"");

            TextView tipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,"猜对开奖号5码即中");  //new TextView(this);
            TextView tipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.LEXUAN_LIU_WHOLE_WU_BONUS +"元");
            TextView tipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,blackColor,",任意4码即中");  //new TextView(this);
            TextView tipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,ConstantUtil.LEXUAN_LIU_ANY_SI_BONUS +"元");

            bonusTipLayout.removeAllViews();
            bonusTipLayout.addView(tipText1);
            bonusTipLayout.addView(tipText2);
            bonusTipLayout.addView(tipText3);
            bonusTipLayout.addView(tipText4);
        }else if(playTypeNameTextView.getText().equals("任选二胆拖")){
            BusiUtil.showDanTuoPlayView(activity);

            TextView danMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"请选择");
            TextView danMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView danMaTipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            TextView tuoMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"至少选择");
            TextView tuoMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView tuoMaTipText3=BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,最多选择");
            TextView tuoMaTipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"10个");
            TextView tuoMaTipText5= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            danMaTipLayout.removeAllViews();
            danMaTipLayout.addView(danMaTipText1);
            danMaTipLayout.addView(danMaTipText2);
            danMaTipLayout.addView(danMaTipText3);

            tuoMaTipLayout.removeAllViews();
            tuoMaTipLayout.addView(tuoMaTipText1);
            tuoMaTipLayout.addView(tuoMaTipText2);
            tuoMaTipLayout.addView(tuoMaTipText3);
            tuoMaTipLayout.addView(tuoMaTipText4);
            tuoMaTipLayout.addView(tuoMaTipText5);

        }else if(playTypeNameTextView.getText().equals("任选三胆拖")) {
            BusiUtil.showDanTuoPlayView(activity);

            TextView danMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"请选择");
            TextView danMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView danMaTipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");
            TextView danMaTipText4= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"最多选择");
            TextView danMaTipText5=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"2个");
            TextView danMaTipText6= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");


            TextView tuoMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"至少选择");
            TextView tuoMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView tuoMaTipText3=BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,最多选择");
            TextView tuoMaTipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"10个");
            TextView tuoMaTipText5= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            danMaTipLayout.removeAllViews();
            danMaTipLayout.addView(danMaTipText1);
            danMaTipLayout.addView(danMaTipText2);
            danMaTipLayout.addView(danMaTipText3);
            danMaTipLayout.addView(danMaTipText4);
            danMaTipLayout.addView(danMaTipText5);
            danMaTipLayout.addView(danMaTipText6);

            tuoMaTipLayout.removeAllViews();
            tuoMaTipLayout.addView(tuoMaTipText1);
            tuoMaTipLayout.addView(tuoMaTipText2);
            tuoMaTipLayout.addView(tuoMaTipText3);
            tuoMaTipLayout.addView(tuoMaTipText4);
            tuoMaTipLayout.addView(tuoMaTipText5);
        }else if(playTypeNameTextView.getText().equals("任选四胆拖")) {
            BusiUtil.showDanTuoPlayView(activity);

            TextView danMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"请选择");
            TextView danMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView danMaTipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");
            TextView danMaTipText4= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"最多选择");
            TextView danMaTipText5=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"3个");
            TextView danMaTipText6= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");


            TextView tuoMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"至少选择");
            TextView tuoMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView tuoMaTipText3=BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,最多选择");
            TextView tuoMaTipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"10个");
            TextView tuoMaTipText5= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            danMaTipLayout.removeAllViews();
            danMaTipLayout.addView(danMaTipText1);
            danMaTipLayout.addView(danMaTipText2);
            danMaTipLayout.addView(danMaTipText3);
            danMaTipLayout.addView(danMaTipText4);
            danMaTipLayout.addView(danMaTipText5);
            danMaTipLayout.addView(danMaTipText6);

            tuoMaTipLayout.removeAllViews();
            tuoMaTipLayout.addView(tuoMaTipText1);
            tuoMaTipLayout.addView(tuoMaTipText2);
            tuoMaTipLayout.addView(tuoMaTipText3);
            tuoMaTipLayout.addView(tuoMaTipText4);
            tuoMaTipLayout.addView(tuoMaTipText5);
        }else if(playTypeNameTextView.getText().equals("任选五胆拖")) {
            BusiUtil.showDanTuoPlayView(activity);

            TextView danMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"请选择");
            TextView danMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView danMaTipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");
            TextView danMaTipText4= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"最多选择");
            TextView danMaTipText5=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"4个");
            TextView danMaTipText6= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");


            TextView tuoMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"至少选择");
            TextView tuoMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView tuoMaTipText3=BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,最多选择");
            TextView tuoMaTipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"10个");
            TextView tuoMaTipText5= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            danMaTipLayout.removeAllViews();
            danMaTipLayout.addView(danMaTipText1);
            danMaTipLayout.addView(danMaTipText2);
            danMaTipLayout.addView(danMaTipText3);
            danMaTipLayout.addView(danMaTipText4);
            danMaTipLayout.addView(danMaTipText5);
            danMaTipLayout.addView(danMaTipText6);

            tuoMaTipLayout.removeAllViews();
            tuoMaTipLayout.addView(tuoMaTipText1);
            tuoMaTipLayout.addView(tuoMaTipText2);
            tuoMaTipLayout.addView(tuoMaTipText3);
            tuoMaTipLayout.addView(tuoMaTipText4);
            tuoMaTipLayout.addView(tuoMaTipText5);
        }else if(playTypeNameTextView.getText().equals("任选六胆拖")) {
            BusiUtil.showDanTuoPlayView(activity);

            TextView danMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"请选择");
            TextView danMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView danMaTipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");
            TextView danMaTipText4= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"最多选择");
            TextView danMaTipText5=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"5个");
            TextView danMaTipText6= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");


            TextView tuoMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"至少选择");
            TextView tuoMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView tuoMaTipText3=BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,最多选择");
            TextView tuoMaTipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"10个");
            TextView tuoMaTipText5= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            danMaTipLayout.removeAllViews();
            danMaTipLayout.addView(danMaTipText1);
            danMaTipLayout.addView(danMaTipText2);
            danMaTipLayout.addView(danMaTipText3);
            danMaTipLayout.addView(danMaTipText4);
            danMaTipLayout.addView(danMaTipText5);
            danMaTipLayout.addView(danMaTipText6);

            tuoMaTipLayout.removeAllViews();
            tuoMaTipLayout.addView(tuoMaTipText1);
            tuoMaTipLayout.addView(tuoMaTipText2);
            tuoMaTipLayout.addView(tuoMaTipText3);
            tuoMaTipLayout.addView(tuoMaTipText4);
            tuoMaTipLayout.addView(tuoMaTipText5);
        }else if(playTypeNameTextView.getText().equals("任选七胆拖")) {
            BusiUtil.showDanTuoPlayView(activity);

            TextView danMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"请选择");
            TextView danMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView danMaTipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");
            TextView danMaTipText4= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"最多选择");
            TextView danMaTipText5=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"6个");
            TextView danMaTipText6= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");


            TextView tuoMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"至少选择");
            TextView tuoMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView tuoMaTipText3=BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,最多选择");
            TextView tuoMaTipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"10个");
            TextView tuoMaTipText5= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            danMaTipLayout.removeAllViews();
            danMaTipLayout.addView(danMaTipText1);
            danMaTipLayout.addView(danMaTipText2);
            danMaTipLayout.addView(danMaTipText3);
            danMaTipLayout.addView(danMaTipText4);
            danMaTipLayout.addView(danMaTipText5);
            danMaTipLayout.addView(danMaTipText6);

            tuoMaTipLayout.removeAllViews();
            tuoMaTipLayout.addView(tuoMaTipText1);
            tuoMaTipLayout.addView(tuoMaTipText2);
            tuoMaTipLayout.addView(tuoMaTipText3);
            tuoMaTipLayout.addView(tuoMaTipText4);
            tuoMaTipLayout.addView(tuoMaTipText5);
        }else if(playTypeNameTextView.getText().equals("前二组选胆拖")){
            BusiUtil.showDanTuoPlayView(activity);

            TextView danMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"请选择");
            TextView danMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView danMaTipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            TextView tuoMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"至少选择");
            TextView tuoMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView tuoMaTipText3=BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,最多选择");
            TextView tuoMaTipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"10个");
            TextView tuoMaTipText5= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            danMaTipLayout.removeAllViews();
            danMaTipLayout.addView(danMaTipText1);
            danMaTipLayout.addView(danMaTipText2);
            danMaTipLayout.addView(danMaTipText3);

            tuoMaTipLayout.removeAllViews();
            tuoMaTipLayout.addView(tuoMaTipText1);
            tuoMaTipLayout.addView(tuoMaTipText2);
            tuoMaTipLayout.addView(tuoMaTipText3);
            tuoMaTipLayout.addView(tuoMaTipText4);
            tuoMaTipLayout.addView(tuoMaTipText5);
        }else if(playTypeNameTextView.getText().equals("前三组选胆拖")) {
            BusiUtil.showDanTuoPlayView(activity);

            TextView danMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"请选择");
            TextView danMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView danMaTipText3= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");
            TextView danMaTipText4= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"最多选择");
            TextView danMaTipText5=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"2个");
            TextView danMaTipText6= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,");


            TextView tuoMaTipText1= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"至少选择");
            TextView tuoMaTipText2=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"1个");
            TextView tuoMaTipText3=BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码,最多选择");
            TextView tuoMaTipText4=BusiUtil.newWRAP_CONTENTTextView(activity,12,redColor,"10个");
            TextView tuoMaTipText5= BusiUtil.newWRAP_CONTENTTextView(activity,12,greyColor,"号码");

            danMaTipLayout.removeAllViews();
            danMaTipLayout.addView(danMaTipText1);
            danMaTipLayout.addView(danMaTipText2);
            danMaTipLayout.addView(danMaTipText3);
            danMaTipLayout.addView(danMaTipText4);
            danMaTipLayout.addView(danMaTipText5);
            danMaTipLayout.addView(danMaTipText6);

            tuoMaTipLayout.removeAllViews();
            tuoMaTipLayout.addView(tuoMaTipText1);
            tuoMaTipLayout.addView(tuoMaTipText2);
            tuoMaTipLayout.addView(tuoMaTipText3);
            tuoMaTipLayout.addView(tuoMaTipText4);
            tuoMaTipLayout.addView(tuoMaTipText5);
        }


    }

    /**
     * 初始化走势图数据
     * @param activity
     */
    public static void initPurchaseHistoryViewData(PurchaseActivity activity){
        initHistoryTitle(activity);
        initHistoryData(activity);
        scrollHistoryToTop(activity); //将走势图滚动到顶部
    }

    /**
     * 将走势图滚动到顶部
     */
    public static void scrollHistoryToTop(PurchaseActivity activity){
        View purchaseScrollViewParent;
        final View history_layout;
        final View opened_time_layout;
        purchaseScrollViewParent =activity.findViewById(R.id.purchaseScrollViewParent);
        history_layout=activity.findViewById(R.id.history_layout);
        opened_time_layout=activity.findViewById(R.id.opened_time_layout);

        View topView=activity.findViewById(R.id.topView);
        topView.post(new Runnable() {
            public void run() {
                int maxYMoveDistance=history_layout.getHeight()-opened_time_layout.getHeight();

                history_layout.scrollTo(0,maxYMoveDistance);

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) history_layout.getLayoutParams();
                layoutParams.bottomMargin=layoutParams.bottomMargin-maxYMoveDistance;
                history_layout.setLayoutParams(layoutParams);

            }
        });


    }

    public static void initHistoryData(PurchaseActivity activity){
        String[][] dataArr=new String[][]{
                {"50期","01","03","05","08","11"},
                {"51期","01","02","06","09","10"},
                {"52期","01","03","04","06","08"},
                {"53期","02","03","05","07","08"},
                {"54期","04","03","07","08","10"},
                {"55期","01","03","07","09","11"},
                {"56期","02","03","05","06","08"},
                {"57期","01","03","05","07","11"},
                {"58期","01","02","04","09","11"},
                {"59期","04","06","08","10","11"},
        };

        //初始化RecyclerView
        RecyclerView recyslerview = (RecyclerView) activity.findViewById(R.id.lottery_history_data);

        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思</span>
        LinearLayoutManager layoutmanager = new LinearLayoutManager(activity);

        //设置RecyclerView 布局管理器
        recyslerview.setLayoutManager(layoutmanager);

        //使用的是系统默认的分割线
        recyslerview.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

        //设置设置RecyclerView的适配器Adapter
        LotteryHistoryDataAdapter adapter = new LotteryHistoryDataAdapter(dataArr);
        recyslerview.setAdapter(adapter);
    }
    /**
     * 展开往期开奖数据
     */
    public static void initHistoryTitle(PurchaseActivity activity){

        String[] titleArr={"期号","01","02","03","04","05","06","07","08","09","10","11"};

        //初始化RecyclerView
        RecyclerView recyslerview = (RecyclerView) activity.findViewById(R.id.lottery_history_title);

        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思</span>
        LinearLayoutManager layoutmanager = new LinearLayoutManager(activity);

        //设置RecyclerView 布局管理器
        recyslerview.setLayoutManager(layoutmanager);

        //设置设置RecyclerView的适配器Adapter
        LotteryHistoryTitleAdapter adapter = new LotteryHistoryTitleAdapter(titleArr);
        recyslerview.setAdapter(adapter);

    }

    /**
     * 获取任选玩法已选中球的数量
     * @param activity
     * @return
     */
    public static int getSelectedRenXuanBallCount(PurchaseActivity activity){
        int selectedBallCount=0;
        LinearLayout commonBallsParent=activity.findViewById(R.id.commonBallsParent);

        int childCount = commonBallsParent.getChildCount();
        for(int i=0;i<childCount;i++){

            View childView = commonBallsParent.getChildAt(i);

            Class<? extends View> aClass = childView.getClass();
            String simpleName = aClass.getSimpleName();

            if(!simpleName.equals("LinearLayout")){
                continue;
            }

            LinearLayout childLinearLayout=(LinearLayout)childView;

            int childCountOfLinearLayout = childLinearLayout.getChildCount();

            TextView ballTextView1= (TextView) childLinearLayout.getChildAt(0);
            Boolean isSelected = (Boolean) ballTextView1.getTag();
            if(isSelected!=null && isSelected==true){
                selectedBallCount+=1;
            }

            if(childCountOfLinearLayout==4){ //有4个子元素,即有上下两个球
                TextView ballTextView2= (TextView) childLinearLayout.getChildAt(2);
                Boolean isSelected2 = (Boolean) ballTextView2.getTag();
                if(isSelected2!=null && isSelected2==true){
                    selectedBallCount+=1;
                }
            }
        }
        return selectedBallCount;
    }

    /**
     * 获取任选玩法被选择的小球的号码集合
     * @param activity
     * @return
     */
    public static List getSelectedRenXuanBallList(PurchaseActivity activity){
        int selectedBallCount=0;
        List selectedBallNumList=new ArrayList<>();
        LinearLayout commonBallsParent=activity.findViewById(R.id.commonBallsParent);

        int childCount = commonBallsParent.getChildCount();
        for(int i=0;i<childCount;i++){
            View childView = commonBallsParent.getChildAt(i);
            Class<? extends View> aClass = childView.getClass();
            String simpleName = aClass.getSimpleName();
            if(!simpleName.equals("LinearLayout")){
                continue;
            }

            LinearLayout childLinearLayout=(LinearLayout)childView;
            int childCountOfLinearLayout = childLinearLayout.getChildCount();
            TextView ballTextView1= (TextView) childLinearLayout.getChildAt(0);  //头一行的小球
            Boolean isSelected = (Boolean) ballTextView1.getTag();
            if(isSelected!=null && isSelected==true){
                String ballNum1=ballTextView1.getText().toString();
                selectedBallNumList.add(Integer.parseInt(ballNum1));
                selectedBallCount+=1;
            }

            if(childCountOfLinearLayout==4){ //有4个子元素,即有上下两个球
                TextView ballTextView2= (TextView) childLinearLayout.getChildAt(2);  //第二行的小球
                Boolean isSelected2 = (Boolean) ballTextView2.getTag();
                if(isSelected2!=null && isSelected2==true){
                    String ballNum2=ballTextView2.getText().toString();
                    selectedBallNumList.add(Integer.parseInt(ballNum2));
                    selectedBallCount+=1;
                }
            }
        }
        return selectedBallNumList;
    }

    /**
     * 获取任选玩法被选中的小球组成的字符串,用叹号分隔的
     * @param activity
     * @return
     */
    public static String getSelectedRenXuanBallStrByExclamationMark(PurchaseActivity activity){
        List selectedBallNumList=getSelectedRenXuanBallList(activity);
        String ballNumStr="";
        for(int i=0;i<selectedBallNumList.size();i++){
            int ballNum= (int) selectedBallNumList.get(i);
            ballNumStr+=ballNum;
            if(i<selectedBallNumList.size()-1){
                ballNumStr+="!";
            }
        }
        return ballNumStr;
    }


    /**
     * 任选玩法,点击小球事件
     * @param view
     */
    public static void renXuanBallClick(View view){
        PurchaseActivity activity= (PurchaseActivity) view.getContext();
        TextView playTypeNameTextView=activity.findViewById(R.id.playTypeName);

        //获取已选择小球的数量
        int selectedBallCount= getSelectedRenXuanBallCount(activity);

        int ballAtLeastSelectNum=getBallAtLeastSelectNumByPlayType(activity);  //最少选择球数
        TextView betCountTextView=activity.findViewById(R.id.betCount);
        TextView betMoneyAmountTextView=activity.findViewById(R.id.betMoneyAmount);

        int betCount=0;         //投注注数
        int betMoneyAmount=0;  //投注总金额
        int pricePerBet=2;     //每注金额
        if(playTypeNameTextView.getText().equals("任选一")){
            Boolean isSelected = (Boolean) view.getTag();
            if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                unselectBall((TextView)view);
                selectedBallCount-=1;
            }else{ //未选中,变为选中
                selectBall((TextView)view);
                selectedBallCount+=1;
            }
            betCount=selectedBallCount;

        }else if(playTypeNameTextView.getText().equals("任选二")){
            Boolean isSelected = (Boolean) view.getTag();
            if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                unselectBall((TextView)view);
                selectedBallCount-=1;
            }else{ //未选中,变为选中
                selectBall((TextView)view);
                selectedBallCount+=1;
            }
            if(selectedBallCount<ballAtLeastSelectNum){
                betCount=0;
            }else{
                betCount=(int)FactorialUtil.combination(selectedBallCount,ballAtLeastSelectNum);
            }
        }else if(playTypeNameTextView.getText().equals("任选三")){
            Boolean isSelected = (Boolean) view.getTag();
            if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                unselectBall((TextView)view);
                selectedBallCount-=1;
            }else{ //未选中,变为选中
                selectBall((TextView)view);
                selectedBallCount+=1;
            }
            if(selectedBallCount<ballAtLeastSelectNum){
                betCount=0;
            }else{
                betCount=(int)FactorialUtil.combination(selectedBallCount,ballAtLeastSelectNum);
            }
        }else if(playTypeNameTextView.getText().equals("任选四")){
            Boolean isSelected = (Boolean) view.getTag();
            if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                unselectBall((TextView)view);
                selectedBallCount-=1;
            }else{ //未选中,变为选中
                selectBall((TextView)view);
                selectedBallCount+=1;
            }
            if(selectedBallCount<ballAtLeastSelectNum){
                betCount=0;
            }else{
                betCount=(int)FactorialUtil.combination(selectedBallCount,ballAtLeastSelectNum);
            }
        }else if(playTypeNameTextView.getText().equals("任选五")){
            Boolean isSelected = (Boolean) view.getTag();
            if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                unselectBall((TextView)view);
                selectedBallCount-=1;
            }else{ //未选中,变为选中
                selectBall((TextView)view);
                selectedBallCount+=1;
            }
            if(selectedBallCount<ballAtLeastSelectNum){
                betCount=0;
            }else{
                betCount=(int)FactorialUtil.combination(selectedBallCount,ballAtLeastSelectNum);
            }
        }else if(playTypeNameTextView.getText().equals("任选六")){
            Boolean isSelected = (Boolean) view.getTag();
            if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                unselectBall((TextView)view);
                selectedBallCount-=1;
            }else{ //未选中,变为选中
                selectBall((TextView)view);
                selectedBallCount+=1;
            }
            if(selectedBallCount<ballAtLeastSelectNum){
                betCount=0;
            }else{
                betCount=(int)FactorialUtil.combination(selectedBallCount,ballAtLeastSelectNum);
            }
        }else if(playTypeNameTextView.getText().equals("任选七")){
            Boolean isSelected = (Boolean) view.getTag();
            if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                unselectBall((TextView)view);
                selectedBallCount-=1;
            }else{ //未选中,变为选中
                selectBall((TextView)view);
                selectedBallCount+=1;
            }
            if(selectedBallCount<ballAtLeastSelectNum){
                betCount=0;
            }else{
                betCount=(int)FactorialUtil.combination(selectedBallCount,ballAtLeastSelectNum);
            }
        }else if(playTypeNameTextView.getText().equals("任选八")){
            Boolean isSelected = (Boolean) view.getTag();
            if(isSelected!=null && isSelected==true){ //已选中,变为未选中
                unselectBall((TextView)view);
                selectedBallCount-=1;
            }else{ //未选中,变为选中
                selectBall((TextView)view);
                selectedBallCount+=1;
            }
            if(selectedBallCount<ballAtLeastSelectNum){
                betCount=0;
            }else{
                betCount=(int)FactorialUtil.combination(selectedBallCount,ballAtLeastSelectNum);
            }
        }

        betMoneyAmount=betCount*pricePerBet;
        betCountTextView.setText(betCount+"");
        betMoneyAmountTextView.setText(betMoneyAmount+"");

    }

    /**
     * 选中小球
     * @param ballTextView
     */
    public static void selectBall(TextView ballTextView){
        ballTextView.setTag(true);
        ballTextView.setBackgroundDrawable(ballTextView.getResources().getDrawable(R.drawable.common_ball_selected));
        ballTextView.setTextColor(Color.parseColor("#ffffff"));  //文字变为白色
    }

    /**
     * 取消选中小球
     * @param ballTextView
     */
    public static void unselectBall(TextView ballTextView){
        ballTextView.setTag(false);
        ballTextView.setBackgroundDrawable(ballTextView.getResources().getDrawable(R.drawable.common_ball_unselected));
        ballTextView.setTextColor(Color.parseColor("#e64d3b"));  //文字变为红色
    }



    /**
     * 选中普通玩法按钮
     * @param playTypeTextView
     */
    public static void selectCommonPlayTextView(View playTypeTextView){
        playTypeTextView.setBackgroundDrawable(playTypeTextView.getResources().getDrawable(R.drawable.common_play_selected));
        playTypeTextView.setTag(true);
    }

    /**
     * 取消选中普通玩法按钮
     * @param playTypeTextView
     */
    public static void unSelectCommonPlayTextView(View playTypeTextView){
        playTypeTextView.setBackgroundDrawable(playTypeTextView.getResources().getDrawable(R.drawable.common_play_unselected));
        playTypeTextView.setTag(false);
    }


    /**
     * 设置胆拖玩法按钮为选中
     * @param playTypeTextView
     */
    public static void selectDanTuoPlayTextView(View playTypeTextView){
        playTypeTextView.setBackgroundDrawable(playTypeTextView.getResources().getDrawable(R.drawable.dantuo_play_selected));
        playTypeTextView.setTag(true);
    }


    /**
     * 取消胆拖玩法按钮选中
     * @param playTypeTextView
     */
    public static void unSelectDanTuoPlayTextView(View playTypeTextView){
        playTypeTextView.setBackgroundDrawable(playTypeTextView.getResources().getDrawable(R.drawable.dantuo_play_unselected));
        playTypeTextView.setTag(false);
    }



    /**
     * 获取投注的总金额
     * @param activity
     * @return
     */
    public static int getbetMoneyAmount(PurchaseActivity activity){
        TextView betMoneyAmountTextView=activity.findViewById(R.id.betMoneyAmount);
        String text=betMoneyAmountTextView.getText().toString();
        return Integer.parseInt(text);
    }


    /**
     * 获取当前投注的注数
     * @param activity
     * @return
     */
    public static int getCurBetCount(PurchaseActivity activity){
        TextView betCountTextView=activity.findViewById(R.id.betCount);
        int betCount=Integer.parseInt(betCountTextView.getText().toString());
        return betCount;
    }


    /**
     * 显示对话框
     * @param activity
     * @param title
     * @param message
     */
    public static void showDialog(Activity activity,String title,String message){
        //AlertDialog的创建用到AlertDialog.Builder，AlertDialog.Builder构造函数中的Context必须传Activity的实例(先记着)
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //设置对话框标题，该标题会显示在标题区域中
        builder.setTitle(title)
                //设置对话框图标，该标题会显示在标题区域中
                .setIcon(null)
                //setMessage方法中的内容会显示在内容区域中
                .setMessage(message)
                //真正实例化AlertDialog对象
                .create()
                //显示对话框
                .show();
    }

    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            toast.setText(message);
        }

        toast.show();
    }


    public static String getSelectedRenXuanBalls(PurchaseActivity activity){

        return  "";
    }


}
