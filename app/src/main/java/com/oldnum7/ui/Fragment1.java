package com.oldnum7.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class Fragment1 extends BaseAppFragment {

    public static Fragment1 newInstance() {
        Bundle args = new Bundle();
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.addFragment(getActivity().getSupportFragmentManager(), Fragment2.newInstance(),R.id.fl_login,false,true);

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentUtils.setBackgroundColor(this,getResources().getColor(R.color.blue));
    }
}
