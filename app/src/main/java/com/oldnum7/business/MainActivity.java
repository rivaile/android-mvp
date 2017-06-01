package com.oldnum7.business;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oldnum7.R;
import com.oldnum7.adpter.UserAdapter;
import com.oldnum7.adpter.base.BaseQuickAdapter;
import com.oldnum7.data.UserEntity;
import com.oldnum7.mvp.BaseActivity;
import com.oldnum7.mvp.IMainPresenter;
import com.oldnum7.mvp.IMainView;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<IMainView, IMainPresenter> implements IMainView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mSrRefresh;

    private MainPresenter mMainPresenter;
    private UserAdapter mUserAdapter;

    private int mSince = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        showLoading();
        mMainPresenter.getUsers(mSince, 10);
    }

    @Override
    public void getUsers(List<UserEntity> users) {
        getStatusLayoutManager().showContent();

        if (mSrRefresh.isRefreshing()) {//刷新
            mUserAdapter.setEnableLoadMore(true);
            mSrRefresh.setRefreshing(false);
            mUserAdapter.setNewData(users);
        } else { //不是刷新...
            if (users.size()<10){
                mUserAdapter.loadMoreEnd(true);
            }
            mUserAdapter.addData(users);
            mUserAdapter.loadMoreComplete();
        }
    }

    @Override
    public void showLoading() {
        getStatusLayoutManager().showLoading();
    }

    @Override
    public void showError() {
        getStatusLayoutManager().showError();

    }

    @Override
    protected void initEvent() {
        initAdapter();
        mSrRefresh.setOnRefreshListener(this);
    }

    @Override
    public void showNetWorkError() {
        getStatusLayoutManager().showNetWorkError();
    }

    private void initAdapter() {
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.hasFixedSize();
        mUserAdapter = new UserAdapter(R.layout.item_user, null);
        mUserAdapter.setOnLoadMoreListener(this, mRvList);
        mRvList.setAdapter(mUserAdapter);
    }

    @Override
    public void onLoadMoreRequested() {
        mSince += 10;
        mMainPresenter.getUsers(mSince, 10);
    }

    @Override
    public void onRefresh() {
        mUserAdapter.setEnableLoadMore(false);
        mSince = 1;
        mMainPresenter.getUsers(mSince, 10);
    }
}
