package com.oldnum7.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.FragmentUtils;
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
public class Fragment2 extends BaseAppFragment {

    public static Fragment2 newInstance() {
        Bundle args = new Bundle();
        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.addFragment(getActivity().getSupportFragmentManager(), Fragment3.newInstance(),R.id.fl_login,false,true);
            }
        });
        return view;
    }
}
