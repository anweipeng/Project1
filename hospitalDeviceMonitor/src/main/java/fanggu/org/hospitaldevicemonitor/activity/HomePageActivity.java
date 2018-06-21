package fanggu.org.hospitaldevicemonitor.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanggu.org.hospitaldevicemonitor.R;
import fanggu.org.hospitaldevicemonitor.fragment.HomePageFragment;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.homePageTextView)
    TextView homePageTextView;
    @BindView(R.id.settingTextView)
    TextView settingTextView;
    @BindView(R.id.meTextView)
    TextView meTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        ButterKnife.bind(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        homePageTextView.setSelected(true);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        HomePageFragment homePageFragment = new HomePageFragment();
        transaction.replace(R.id.fragment_container, homePageFragment);
        transaction.commit();
    }

    public void switchSelector(View view) {
        homePageTextView.setSelected(false);
        settingTextView.setSelected(false);
        meTextView.setSelected(false);
        view.setSelected(true);



    }
}
