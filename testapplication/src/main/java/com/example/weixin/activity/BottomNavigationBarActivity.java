package com.example.weixin.activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.weixin.R;
import com.example.weixin.fragment.IndexFragment;
import com.example.weixin.fragment.LoveFragment;
import com.example.weixin.fragment.MapFragment;
import com.example.weixin.fragment.PersonFragment;

public class BottomNavigationBarActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    BottomNavigationBar mBottomNavigationBar;
    private IndexFragment indexFragment;
    private MapFragment mapFragment;
    private LoveFragment loveFragment;
    private PersonFragment personFragment;
    private TextBadgeItem badgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);
        initBadge();
        setDefaultFragment();
        InitNavigationBar();
    }

    private void InitNavigationBar() {
        mBottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.zhichu,"首页"))  //.setActiveColorResource(R.color.red)
                .addItem(new BottomNavigationItem(R.drawable.youxiang,"地图")) //.setActiveColorResource(R.color.blue)
                .addItem(new BottomNavigationItem(R.drawable.yaoyiyao,"关注").setBadgeItem(badgeItem))  //.setActiveColorResource(android.R.color.holo_orange_light)
                .addItem(new BottomNavigationItem(R.drawable.yinhangka,"个人"))  //.setActiveColorResource(android.R.color.holo_green_light)
                .setFirstSelectedPosition(0)
                .initialise();
    }

    public void initBadge()
    {
        badgeItem = new TextBadgeItem()
                .setBorderWidth(2)
                .setBorderColor("#ff0000")
                .setBackgroundColor("#ff0000")
                .setGravity(Gravity.RIGHT| Gravity.TOP)
                .setText("2")
                .setTextColor("#ffffff")
                .setAnimationDuration(2000)
                .setHideOnSelect(true);
    }
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        indexFragment = new IndexFragment();
        transaction.replace(R.id.fragment_container, indexFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d("onTabSelected", "onTabSelected: " + position);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (indexFragment == null) {
                    indexFragment = new IndexFragment();
                }
                transaction.replace(R.id.fragment_container, indexFragment);
                break;
            case 1:
                if (mapFragment== null) {
                    mapFragment = new MapFragment();
                }
                transaction.replace(R.id.fragment_container, mapFragment);
                break;
            case 2:
                if (loveFragment == null) {
                    loveFragment = new LoveFragment();
                }
                transaction.replace(R.id.fragment_container,loveFragment);
                break;
            case 3:
                if (personFragment == null) {
                    personFragment = new PersonFragment();
                }
                transaction.replace(R.id.fragment_container,personFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d("onTabUnselected", "onTabUnselected: " + position);
    }

    @Override
    public void onTabReselected(int position) {
        Log.d("onTabReselected", "onTabReselected: " + position);
    }
}