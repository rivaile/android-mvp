package com.oldnum7.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.oldnum7.R;
import com.oldnum7.base.BaseAppActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class Main2Activity extends BaseAppActivity {

    private String TAG = getClass().getSimpleName();

//    @BindView(R.id.bottom_navigation_bar)
//    BottomNavigationBar mBottomNavigationBar;

    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        ButterKnife.bind(this);

    }



}
