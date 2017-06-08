package com.oldnum7.www.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private TabLayout mTl;
    private ViewPager mVp;
    private List<Fragment> mList = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();
    private String[] titles = {"页面1", "页面2", "页面3", "页面4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mTl = (TabLayout) findViewById(R.id.tl);
        mVp = (ViewPager) findViewById(R.id.vp);

        mList.add(new Tab1Fragment());
        mList.add(new Tab2Fragment());
        mList.add(new Tab3Fragment());
        mList.add(new Tab4Fragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);
        mTl.setupWithViewPager(mVp);
        mVp.setOffscreenPageLimit(3);
    }


    private class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
