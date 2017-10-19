package com.oldnum7.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oldnum7.R;
import com.oldnum7.base.BaseAppFragment;
import com.oldnum7.ui.login.LoginFragment;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/20/14:18
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class Tab1Fragment extends BaseAppFragment {

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

//        ActivityComponent component = getActivityComponent();
//        if (component != null) {
//            component.inject(this);
//            setUnBinder(ButterKnife.bind(this, view));
//        }
        return view;
    }

    @Override
    protected void initData() {
        Log.e(TAG, "initData: ");
    }
}
