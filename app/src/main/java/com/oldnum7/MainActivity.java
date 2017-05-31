package com.oldnum7;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.oldnum7.mvp.BaseActivity;
import com.oldnum7.mvp.IMainPresenter;
import com.oldnum7.mvp.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<IMainView, IMainPresenter> implements IMainView {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public IMainPresenter createPresenter() {
        mMainPresenter = new MainPresenter();
        return mMainPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {
        mMainPresenter.getUsers(1, 10);
    }
}
