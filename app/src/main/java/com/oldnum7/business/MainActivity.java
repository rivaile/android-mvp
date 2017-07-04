package com.oldnum7.business;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.oldnum7.R;
import com.oldnum7.business.account.Tab3Fragment;
import com.oldnum7.business.dashborard.Tab2Fragment;
import com.oldnum7.business.home.Tab1Fragment;
import com.oldnum7.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private String TAG = getClass().getSimpleName();

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private List<Fragment> mFragmentList = new ArrayList<>();

//    private Class[] clazz = new Class[]{Tab1Fragment.class, Tab2Fragment.class, Tab3Fragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initEvent() {
        mBottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED);

        mBottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        //在新版本MODE_FIXED时的显示有问题...
        BadgeItem badgeItem = new BadgeItem()
                .setBorderWidth(2)
                .setBackgroundColorResource(R.color.red)
                .setText("99+");

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Tab1").setActiveColor(R.color.orange).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black_24dp, "Tab2"))
                .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black_24dp, "Tab3"))
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(mOnTabSelectedListener);


        mFragmentList.clear();
        mFragmentList.add(0, new Tab1Fragment());
        mFragmentList.add(1, new Tab2Fragment());
        mFragmentList.add(2, new Tab3Fragment());

        mBottomNavigationBar.selectTab(0);
    }

    private BottomNavigationBar.OnTabSelectedListener mOnTabSelectedListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            Log.d(TAG, "onTabSelected: " + position);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!mFragmentList.get(position).isAdded()) {
                fragmentTransaction.add(R.id.content, mFragmentList.get(position), mFragmentList.get(position).getClass().getSimpleName());
            }

            fragmentTransaction.show(mFragmentList.get(position))
                    .commit();

        }

        @Override
        public void onTabUnselected(int position) {
            Log.d(TAG, "onTabUnselected: " + position);
            getSupportFragmentManager().beginTransaction()
                    .hide(mFragmentList.get(position))
                    .commit();
        }

        @Override
        public void onTabReselected(int position) {
            Log.d(TAG, "onTabReselected: " + position);
        }
    };

}
