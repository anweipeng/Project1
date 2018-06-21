package fanggu.org.lotteryapplication.activity;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import java.io.IOException;
import java.util.*;
import fanggu.org.lotteryapplication.R;
import fanggu.org.lotteryapplication.http.buyNOOrderRequest.*;
import fanggu.org.lotteryapplication.http.common.CryptoHelper;
import fanggu.org.lotteryapplication.http.common.HttpConstants;
import fanggu.org.lotteryapplication.http.common.MessagePost;
import fanggu.org.lotteryapplication.util.OkHttp3Utils;
import fanggu.org.lotteryapplication.http.login.UserLoginRequest;
import fanggu.org.lotteryapplication.http.login.UserLoginResponse;
import fanggu.org.lotteryapplication.http.timeno.*;
import fanggu.org.lotteryapplication.util.*;
import okhttp3.*;


/**
 * 购买/投注页面
 */
public class PurchaseActivity extends AppCompatActivity {

    private String TimeNO;       //期号
    private PopupWindow playTypesPopWindow; //所有玩法弹窗

    // 任选玩法
    private String[] commonPlayTypeTexts = new String[] { "任选一", "任选二", "任选三", "任选四", "任选五", "任选六", "任选七", "任选八","前二直选",
            "前二组选","前三直选","前三组选"};

    //任选玩法奖金
    private String[] rewardTexts = new String[] { "奖金13元", "奖金6元", "奖金19元", "奖金78元", "奖金540元", "奖金90元", "奖金26元", "奖金9元","奖金130元",
            "奖金1170元","奖金65元","奖金195元"};

    //乐选新玩法
    private String[] newPlayTypeTexts = new String[] { "三码通","乐选四","乐选五","乐选六"};

    //胆拖玩法
    private String[] dantuoPlayTypeTexts = new String[] { "任选二胆拖", "任选三胆拖", "任选四胆拖", "任选五胆拖", "任选六胆拖", "任选七胆拖", "前二组选胆拖", "前三组选胆拖"};

    //十一个球的编号
    private String[] elevenBallNumArr = new String[] { "01","02","03","04","05","06","07","08","09","10","11"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        init();
    }

    public void init(){
        getTimeNOInit();              //获取期号
        initWanQianBaiDanTuoView();   //万位、千位、百位、胆码、拖码 初始化
        BusiUtil.initPurchaseHistoryViewData(this); //初始化下拉走势数据
    }

    /**
     * 万位、千位、百位、胆码、拖码 初始化
     */
    public void initWanQianBaiDanTuoView(){
        //万位
        GridView wanWeiGridView = findViewById(R.id.wanWeiGridView);
        wanWeiGridView.setAdapter(getWanQianBaiDanTuoAdapter());
//        wanWeiGridView.setAdapter(new MyAdapter(this)); //自定义适配器

        //千位
        GridView qianWeiGridView = findViewById(R.id.qianWeiGridView);
        qianWeiGridView.setAdapter(getWanQianBaiDanTuoAdapter());

        //百位
        GridView baiWeiGridView = findViewById(R.id.baiWeiGridView);
        baiWeiGridView.setAdapter(getWanQianBaiDanTuoAdapter());


        //胆码
        GridView danMaMyGridView = findViewById(R.id.danMaMyGridView);
        danMaMyGridView.setAdapter(getWanQianBaiDanTuoAdapter());

        //拖码
        GridView tuoMaMyGridView = findViewById(R.id.tuoMaMyGridView);
        tuoMaMyGridView.setAdapter(getWanQianBaiDanTuoAdapter());

    }

    public void ballClick(View view){
        TextView textView= (TextView) view;
        Boolean tag = (Boolean) textView.getTag();
        int id=textView.getId();
        String number=textView.getText().toString();
        int index=Integer.parseInt(textView.getText().toString())-1;
        if(tag==null || tag==false){ //未选中,设置为选中状态
            BusiUtil.selectBall(textView);
        }else{
            BusiUtil.unselectBall(textView);
        }
        System.out.println();
    }

    /**
     * 任选玩法,点击小球事件
     * @param view
     */
    public void renXuanBallClick(View view){
         BusiUtil.renXuanBallClick(view);
    }



    /**
     * 右上角菜单点击事件,弹出菜单弹窗
     * @param view
     */
    public void showMenuPopupWindow(View view) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwin_layout, null);
        PopupWindow mPopWindow = new PopupWindow(contentView,DensityUtil.dip2px(this,140), DensityUtil.dip2px(this,178), true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new ColorDrawable());
        mPopWindow.showAsDropDown(view, -(mPopWindow.getWidth()- view.getWidth()+DensityUtil.dip2px(getApplicationContext(),11)),0);
    }

    private View selectedPlayTypeView;  //已选中的玩法

    /**
     * 取消已选中的玩法的选中状态
     * @param selectedView  被点击的GridView子项目内的最外层元素
     */
    public void unSelectSelectedPlayTypeView(View selectedView){
        if(selectedPlayTypeView!=null){
            selectedPlayTypeView.setBackgroundDrawable(getResources().getDrawable(R.drawable.common_play_unselected));
            selectedPlayTypeView.setTag(false);
        }
        selectedPlayTypeView=selectedView;
        playTypesPopWindow.dismiss();

    }


    /**
     * 弹出所有投注玩法下拉框
     * @param view
     */
    public void showAllPlayTypesPopupWin(View view){
        if(playTypesPopWindow!=null){ //已实例化过,直接弹出
            playTypesPopWindow.showAsDropDown(findViewById(R.id.topBar),0,0);
            return ;
        }

        //所有玩法布局文件
        View contentView = LayoutInflater.from(this).inflate(R.layout.allplayypepopwin_layout, null);

        // 任选GridView
        final GridView renXuanGridView = (GridView) contentView.findViewById(R.id.renXuanGridView);
        // 设置数据匹配器
        renXuanGridView.setAdapter(getRenXuanAdapter());

        renXuanGridView.post(new Runnable() {
            public void run() {
                //设置第一个玩法为选中状态
                View child0 = renXuanGridView.getChildAt(0);
                BusiUtil.selectCommonPlayTextView(child0);
                selectedPlayTypeView=child0;
            }
        });
        // 添加单击item时的监听事件
        renXuanGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * @param arg0 当前AdapterView的实体,即此renXuanGridView
             * @param arg1 被点击的子项目内的最外层元素
             * @param index 被点击子项目的索引
             * @param arg3
             */
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
                unSelectSelectedPlayTypeView(arg1);

                //设置玩法text
                TextView renXuanItemText=arg1.findViewById(R.id.renXuanItemText);
                String text=renXuanItemText.getText().toString();
                ((TextView)findViewById(R.id.playTypeName)).setText(text);


                Boolean selectedTag= (Boolean) arg1.getTag();
                if(selectedTag==null || selectedTag==false){ //设置为选中状态
                    BusiUtil.selectCommonPlayTextView(arg1);
                }else{  //设置为未选中状态
                    BusiUtil.unSelectCommonPlayTextView(arg1);
                }

                //显示与选择的玩法相关的界面
                BusiUtil.showRelevantViewOfPlayType(PurchaseActivity.this);

                System.out.println();
            }
        });

        //乐选GridView
        GridView leXuanGridView = (GridView) contentView.findViewById(R.id.leXuanGridView);

        // 设置数据匹配器
        leXuanGridView.setAdapter(getLeXuanAdapter());
        leXuanGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
                unSelectSelectedPlayTypeView(arg1);

                //设置玩法text
                TextView leXuanItemText=arg1.findViewById(R.id.leXuanItemText);
                String text=leXuanItemText.getText().toString();
                ((TextView)findViewById(R.id.playTypeName)).setText(text);


                Boolean selectedTag= (Boolean) arg1.getTag();
                if(selectedTag==null || selectedTag==false){  //未选中,设置为选中
                    BusiUtil.selectCommonPlayTextView(arg1);
                }else{     //选中,设置为未选中
                    BusiUtil.unSelectCommonPlayTextView(arg1);
                }

                //显示与选择的玩法相关的界面
                BusiUtil.showRelevantViewOfPlayType(PurchaseActivity.this);

                System.out.println();
            }
        });

        //胆拖GridView
        GridView danTuoGridView= (GridView) contentView.findViewById(R.id.danTuoGridView);
        // 设置数据匹配器
        danTuoGridView.setAdapter(getDanTuoAdapter());
        danTuoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
                unSelectSelectedPlayTypeView(arg1);

                //设置玩法text
                TextView danTuoItemText=arg1.findViewById(R.id.danTuoItemText);
                String text=danTuoItemText.getText().toString();
                ((TextView)findViewById(R.id.playTypeName)).setText(text);

                Boolean selectedTag= (Boolean) arg1.getTag();
                if(selectedTag==null || selectedTag==false){ //设置为选中
                    BusiUtil.selectDanTuoPlayTextView(arg1);
                }else{  //设置为未选中
                    BusiUtil.unSelectDanTuoPlayTextView(arg1);
                }

                //显示与选择的玩法相关的界面
                BusiUtil.showRelevantViewOfPlayType(PurchaseActivity.this);
                System.out.println();
            }
        });

        playTypesPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT , true);
        playTypesPopWindow.setContentView(contentView);
        playTypesPopWindow.setBackgroundDrawable(new ColorDrawable());
        playTypesPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener(){
            @Override
            public void onDismiss() {
            }
        });
        playTypesPopWindow.showAsDropDown(findViewById(R.id.topBar),0,0);
    }

   public void dismissMenuPopupWindow(View view){
       playTypesPopWindow.dismiss();
    }

    /**
     * 消息提示框
     * @param view
     */
    public void showCustomAlertDialog(View view) {
        //通过LayoutInflater获取登录窗口布局
        LayoutInflater lf = LayoutInflater.from(this);
        View customView = lf.inflate(R.layout.layout_alertdialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置自定义View并创建AlertDialog
        final AlertDialog alertDialog = builder.setView(customView).create();

        Button loginBtn = (Button) customView.findViewById(R.id.cancel);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    /**
     * 获取任选投注GridView的数据匹配器
     */
    private ListAdapter getRenXuanAdapter() {
        // 该list用来存放每一个item对应的文字和图片
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < commonPlayTypeTexts.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemText1", commonPlayTypeTexts[i]);
            map.put("itemText2", rewardTexts[i]);
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.renxuan_item, new String[] { "itemText1", "itemText2" },
                new int[] { R.id.renXuanItemText, R.id.renXuanItemBonusText});
        return simpleAdapter;
    }

    /**
     * 获取乐选投注GridView的数据匹配器
     */
    private ListAdapter getLeXuanAdapter() {
        // 该list用来存放每一个item对应的文字和图片
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < newPlayTypeTexts.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemText", newPlayTypeTexts[i]);
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.lexuan_item, new String[] { "itemText" },
                new int[] { R.id.leXuanItemText});
        return simpleAdapter;
    }

    /**
     * 获取胆拖投注GridView的数据匹配器
     */
    private ListAdapter getDanTuoAdapter() {
        // 该list用来存放每一个item对应的文字
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < dantuoPlayTypeTexts.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemText", dantuoPlayTypeTexts[i]);
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.dantuo_item, new String[] { "itemText" },
                new int[] { R.id.danTuoItemText});
        return simpleAdapter;
    }

    private ListAdapter getWanQianBaiDanTuoAdapter(){{
        // 该list用来存放每一个item对应的文字
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < elevenBallNumArr.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemText", elevenBallNumArr[i]);
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.lottery_ball_gridview_item, new String[] { "itemText" },
                new int[] { R.id.ballTextView});
        return simpleAdapter;
    }

    }

    /**
     * 设置popwindow背景半透明
     */
    private void lightoff() {
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.7f;
        getWindow().setAttributes(lp);
    }



    /**
     * 取消popwindow背景半透明
     */
    private void lighton() {
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=1f;
        getWindow().setAttributes(lp);
    }



    public void switchYiLouAndLengRe(View view){
        TextView aboveTextView = this.findViewById(R.id.aboveText);
        TextView belowTextView = this.findViewById(R.id.belowText);

        Boolean tag= (Boolean) view.getTag();
        if(tag==null || tag==false){   //
            aboveTextView.setText("冷热");
            belowTextView.setText("遗漏");
            view.setTag(true);
        }else{
            aboveTextView.setText("遗漏");
            belowTextView.setText("冷热");
            view.setTag(false);
        }
    }


    /**
     * 登录
     * @param view
     */
    public void loginReq(View view){
        UserLoginRequest userLoginRequest = new UserLoginRequest("abc", "cxzaq1");
        MessagePost message = new MessagePost(MessageType.userLogin, true, JSONHelper.toJSON(userLoginRequest));
        OkHttp3Utils.doPostJson(HttpConstants.REQUEST_URL,JSONHelper.toJSON(message),new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    try {
                        MessagePost message = JSONHelper.parseObject(responseStr, MessagePost.class);

                        String content = "";
                        if (message.getMessagehead().getIsencrypt().equals(true)) {
                            content = CryptoHelper.GetHandle().decode(message.getMessagebody().getContent());
                        } else {
                            content = message.getMessagebody().getContent();
                        }
                        UserLoginResponse user = JSONHelper.parseObject(content, UserLoginResponse.class);

                        System.out.println(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("onFailure！！！！！");
            }
        });
    }

    public void getTimeNO(View view){
        TextView timeNoTextView=findViewById(R.id.timeNoTextView);

        TimeNORequest timeNORequest = new TimeNORequest("");
        MessagePost message = new MessagePost(MessageType.getTimeNO, true, JSONHelper.toJSON(timeNORequest));
        OkHttp3Utils.doPostJson(HttpConstants.REQUEST_URL,JSONHelper.toJSON(message),new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    try {
                        MessagePost message = JSONHelper.parseObject(responseStr, MessagePost.class);

                        String content = "";
                        if (message.getMessagehead().getIsencrypt().equals(true)) {
                            content = CryptoHelper.GetHandle().decode(message.getMessagebody().getContent());
                        } else {
                            content = message.getMessagebody().getContent();
                        }
                        TimeNOResponse timeNOResponse =  JSONHelper.parseObject(content,TimeNOResponse.class);
                        System.err.println(message.getMessagehead().getCurrentdate()+": "+content);
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

    /**
     * 获取期号,递归调用自身
     */
    public void getTimeNOLoop(){
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //此处UI线程,更改显示的时间
                TimeNOResponse timeNOResponse= (TimeNOResponse) msg.obj;
                String timeNO=timeNOResponse.getTimeNO();
                TextView timeNoTextView=findViewById(R.id.timeNoTextView);
                timeNoTextView.setText(timeNO.substring(timeNO.length()-2,timeNO.length()));
                setTimeNO(timeNO);

                final TextView countDownTextView=findViewById(R.id.countDownTextView);
                int remainSec = timeNOResponse.getRemainSec();

                if(remainSec<=0){
                    getTimeNOLoop();
                    return;
                }

                /** 倒计时,一次1秒 */
                CountDownTimer timer = new CountDownTimer(timeNOResponse.getRemainSec()*1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {  //单位为毫秒
                        String remainMinuteStr,remainSecStr;
                        int remainMinute=(int)millisUntilFinished/1000/60;
                        if(remainMinute<10){
                            remainMinuteStr="0"+remainMinute;
                        }else{
                            remainMinuteStr=remainMinute+"";
                        }
                        int remainSecCur=(int)(millisUntilFinished-remainMinute*60*1000)/1000;
                        if(remainSecCur<10){
                            remainSecStr="0"+remainSecCur;
                        }else{
                            remainSecStr=remainSecCur+"";
                        }
                        countDownTextView.setText(remainMinuteStr+":"+remainSecStr);
                    }

                    @Override
                    public void onFinish() {
                        countDownTextView.setText("00:00");
                        //重新获取
                        getTimeNOLoop();
                    }
                }.start();
            }
        };

        TimeNORequest timeNORequest = new TimeNORequest("");
        MessagePost message = new MessagePost(MessageType.getTimeNO, true, JSONHelper.toJSON(timeNORequest));
        OkHttp3Utils.doPostJson(HttpConstants.REQUEST_URL,JSONHelper.toJSON(message),new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    try {
                        MessagePost messagePost = JSONHelper.parseObject(responseStr, MessagePost.class);

                        String content = "";
                        if (messagePost.getMessagehead().getIsencrypt().equals(true)) {
                            content = CryptoHelper.GetHandle().decode(messagePost.getMessagebody().getContent());
                        } else {
                            content = messagePost.getMessagebody().getContent();
                        }
                        TimeNOResponse timeNOResponse =  JSONHelper.parseObject(content,TimeNOResponse.class);

                        //切记不要在此处更改UI,此处不是UI主线程，要使用handler
                        Message messageHand = handler.obtainMessage(0,timeNOResponse);
                        handler.sendMessage(messageHand);

                        System.out.println(messagePost.getMessagehead().getCurrentdate()+"获取期号结果: "+content);
                    } catch (Exception e) {
                        System.out.println("Exception！！！！！");
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("getTimeNOLoop onFailure！！！！！");
            }
        });
    }
    /**
     * 获取11选5期号
     */
    public void getTimeNOInit(){
        final TextView timeNoTextView=findViewById(R.id.timeNoTextView);
        timeNoTextView.post(new Runnable() {
            public void run() {
                getTimeNOLoop();
            }
        });
    }



    /**
     * 【确定】按钮点击事件
     * @param view
     */
    public void confirmBuy(View view) {
        TextView playTypeNameTextView = findViewById(R.id.playTypeName);
        int selectedBallCount=BusiUtil.getSelectedRenXuanBallCount(this);
        int betCount=BusiUtil.getCurBetCount(this);

        if(betCount<=0){
            BusiUtil.showToast(this,"选择的号码尚未成为一注");
            return ;
        }
        boolean isencrypt=false;         //是否加密

        int UserId=2;                   //用户ID
        String ShopCode="QY";           //实体店编码
        String TuoNOs="";               //拖码
        String DanNOs="";               //胆码
        boolean isGroup=false;          //是否组选玩法
        String TimeNO=getTimeNO();      //期号
        int Mutiple=1;                  //购买倍数
        String PlayName=BusiUtil.getPlayNameByPlayType(this);             //玩法名称
        int Money=BusiUtil.getbetMoneyAmount(this);                    //金额
        String PayPassword="123456";    //支付密码

        if (playTypeNameTextView.getText().equals("任选一")) {
            TuoNOs=BusiUtil.getSelectedRenXuanBallStrByExclamationMark(this);
        }else if(playTypeNameTextView.getText().equals("任选二")){
            TuoNOs=BusiUtil.getSelectedRenXuanBallStrByExclamationMark(this);
        }else if(playTypeNameTextView.getText().equals("任选三")){
            TuoNOs=BusiUtil.getSelectedRenXuanBallStrByExclamationMark(this);
        }else if(playTypeNameTextView.getText().equals("任选四")){
            TuoNOs=BusiUtil.getSelectedRenXuanBallStrByExclamationMark(this);
        }else if(playTypeNameTextView.getText().equals("任选五")){
            TuoNOs=BusiUtil.getSelectedRenXuanBallStrByExclamationMark(this);
        }else if(playTypeNameTextView.getText().equals("任选六")){
            TuoNOs=BusiUtil.getSelectedRenXuanBallStrByExclamationMark(this);
        }else if(playTypeNameTextView.getText().equals("任选七")){
            TuoNOs=BusiUtil.getSelectedRenXuanBallStrByExclamationMark(this);
        }else if(playTypeNameTextView.getText().equals("任选八")){
            TuoNOs=BusiUtil.getSelectedRenXuanBallStrByExclamationMark(this);
        }

        BuyNOOrderRequest requestBean= new BuyNOOrderRequest(); //JSONHelper.parseObject(contentBuy,BuyNOOrderRequest.class);
        requestBean.setUserID(UserId);
        requestBean.setShopCode(ShopCode);
        requestBean.setTuoNOs(TuoNOs);
        requestBean.setDanNOs(DanNOs);
        requestBean.setIsGroup(isGroup);
        requestBean.setTimeNO(TimeNO);
        requestBean.setMutiple(Mutiple);
        requestBean.setPlayName(PlayName);
        requestBean.setMoney(Money);
        requestBean.setPayPassword(PayPassword);

        MessagePost messagePost_Buy =new MessagePost(MessageType.buyOrder,isencrypt,JSONHelper.toJSON(requestBean));
        String paramStr=JSONHelper.toJSON(messagePost_Buy);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                BuyNOOrderResponse responseBuy= (BuyNOOrderResponse) msg.obj;
                String resultinfo=responseBuy.getResultinfo();
                BusiUtil.showToast(PurchaseActivity.this,resultinfo);
            }
        };
        OkHttp3Utils.doPostJson(HttpConstants.REQUEST_URL,paramStr,new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    try {
                        MessagePost message = JSONHelper.parseObject(responseStr, MessagePost.class);

                        String content = "";
                        if (message.getMessagehead().getIsencrypt().equals(true)) {
                            content = CryptoHelper.GetHandle().decode(message.getMessagebody().getContent());
                        } else {
                            content = message.getMessagebody().getContent();
                        }
                        System.err.println(message.getMessagehead().getCurrentdate()+": "+content);

                        //切记不要在此处更改UI,此处不是UI主线程，要使用handler
                        BuyNOOrderResponse responseBuy=JSONHelper.parseObject(content,BuyNOOrderResponse.class);
                        Message messageHand = handler.obtainMessage(0,responseBuy);
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


        TimeNORequest timeNORequest = new TimeNORequest("");
        MessagePost message = new MessagePost(MessageType.getTimeNO, true, JSONHelper.toJSON(timeNORequest));

    }


    public String getTimeNO() {
        return TimeNO;
    }

    public void setTimeNO(String timeNO) {
        this.TimeNO = timeNO;
    }


    public void clearAllBalls(View view){
        BusiUtil.unSelectAllBallsAndClearBetInfo(this);
    }

}


