package com.oldnum7.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oldnum7.R;
import com.oldnum7.base.BaseAppFragment;

/**
 * <pre>
 *       author : denglin
 *       time   : 2018/02/07/16:30
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class Fragment3 extends BaseAppFragment {

    public static Fragment3 newInstance() {
        Bundle args = new Bundle();
        Fragment3 fragment = new Fragment3();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        return view;
    }
}
