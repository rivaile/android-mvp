package com.oldnum7.ui;

import android.support.v4.widget.SwipeRefreshLayout;

import com.oldnum7.androidlib.base.BaseActivity;


public class UserActivity extends BaseActivity implements IMainContract.View, SwipeRefreshLayout.OnRefreshListener {
    @Override
    public void onRefresh() {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNetWorkError() {

    }

//    @BindView(R.id.rv_list)
//    RecyclerView mRvList;
//    @BindView(R.id.sr_refresh)
//    SwipeRefreshLayout mSrRefresh;
//
//    private UserAdapter mUserAdapter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main1);
//    }
//
//    @Override
//    protected void setPresenter() {
//        DaggerMainComponent.builder()
//                .appComponent(((BaseApplication) getApplication()).getAppComponent())
//                .mainPresenterModule(new ActivityModule(this))
//                .build()
//                .inject(this);
//    }
//
//    @Override
//    protected void loadData() {
//
//        showLoading();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPresenter.subscribe();
//            }
//        }, 2000);
//
//    }
//
//    @Override
//    public void setLoadingIndicator(final boolean active) {
//
//        mSrRefresh.post(() -> mSrRefresh.setRefreshing(active));
//    }
//
//
//    @Override
//    public void getUsers(List<T> users) {
//        getStatusLayoutManager().showContent();
//
//        mUserAdapter.setNewData(users);
//
//        if (mSrRefresh.isRefreshing()) {//刷新
//            mUserAdapter.setEnableLoadMore(true);
//            mSrRefresh.setRefreshing(false);
//            mUserAdapter.setNewData(users);
//
//        }
//    }
//
//    @Override
//    public void showLoading() {
//        getStatusLayoutManager().showLoading();
//    }
//
//    @Override
//    public void showError() {
//        getStatusLayoutManager().showError();
//
//    }
//
//    @Override
//    protected void initEvent() {
//
//
//        initAdapter();
//        mSrRefresh.setOnRefreshListener(this);
//    }
//
//    @Override
//    public void showNetWorkError() {
//        getStatusLayoutManager().showNetWorkError();
//    }
//
//    private void initAdapter() {
//        mRvList.setLayoutManager(new LinearLayoutManager(this));
//        mRvList.hasFixedSize();
//        mUserAdapter = new UserAdapter(R.layout.item_user, null);
////        mUserAdapter.setOnLoadMoreListener(this, mRvList);
//        mRvList.setAdapter(mUserAdapter);
//    }
//
//    @Override
//    public void onRefresh() {
//        mPresenter.loadData(true);
//    }
//
//    @Override
//    public void setPresenter(IMainContract.Presenter presenter) {
//
//    }
}
