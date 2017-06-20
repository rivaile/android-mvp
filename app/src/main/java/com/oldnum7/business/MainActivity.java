package com.oldnum7.business;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.oldnum7.R;
import com.oldnum7.mvp.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private String TAG = getClass().getSimpleName();

    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

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

        //在MODE_FIXED时的显示有问题...
        BadgeItem badgeItem = new BadgeItem()
                .setBorderWidth(2)
                .setBackgroundColorResource(R.color.red)
                .setText("99+");

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home").setActiveColor(R.color.orange).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black_24dp, "Dashboard"))
                .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black_24dp, "Account"))
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(mOnTabSelectedListener);
    }

    private BottomNavigationBar.OnTabSelectedListener mOnTabSelectedListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            Log.d(TAG, "onTabSelected: " + position);
        }

        @Override
        public void onTabUnselected(int position) {
            Log.d(TAG, "onTabUnselected: " + position);
        }

        @Override
        public void onTabReselected(int position) {
            Log.d(TAG, "onTabReselected: " + position);
        }
    };
}
