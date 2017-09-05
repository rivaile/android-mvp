package com.oldnum7.business;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oldnum7.R;
import com.oldnum7.adapter.UserAdapter;
import com.oldnum7.App;
import com.oldnum7.di.component.DaggerMainComponent;
import com.oldnum7.di.module.MainPresenterModule;
import com.oldnum7.base.BaseActivity;

import java.util.List;

import butterknife.BindView;

public class UserActivity extends BaseActivity<MainPresenter> implements IMainContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mSrRefresh;

    private UserAdapter mUserAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }

    @Override
    protected void setPresenter() {
        DaggerMainComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void loadData() {

        showLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.subscribe();
            }
        }, 2000);

    }

    @Override
    public void setLoadingIndicator(final boolean active) {

        mSrRefresh.post(() -> mSrRefresh.setRefreshing(active));
    }


    @Override
    public void getUsers(List<T> users) {
        getStatusLayoutManager().showContent();

        mUserAdapter.setNewData(users);

        if (mSrRefresh.isRefreshing()) {//刷新
            mUserAdapter.setEnableLoadMore(true);
            mSrRefresh.setRefreshing(false);
            mUserAdapter.setNewData(users);

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
//        mUserAdapter.setOnLoadMoreListener(this, mRvList);
        mRvList.setAdapter(mUserAdapter);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadData(true);
    }

    @Override
    public void setPresenter(IMainContract.Presenter presenter) {

    }
}
