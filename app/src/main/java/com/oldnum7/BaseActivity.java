package com.oldnum7;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.oldnum7.status.StatusLayoutManager;

/**
 * <Pre>
 * author : denglin
 * time   : 2017/05/31/13:47
 * desc   :The base activity that every activity you create must extent it.
 * version: 1.0
 * </Pre>
 */
public class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {

    protected StatusLayoutManager statusLayoutManager;
    private LinearLayout mRootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        initVariables();
        initToolbar();
    }

    public void setContentView(@LayoutRes int layoutResID) {
//        setContentView(View.inflate(this, layoutResID, null));
        mRootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (mRootLayout == null) {
            return;
        }

        statusLayoutManager = StatusLayoutManager.newBuilder(this)
                .contentView(layoutResID)
                .emptyDataView(R.layout.activity_empty_data)
                .errorView(R.layout.activity_error)
                .loadingView(R.layout.activity_loading)
                .netWorkErrorView(R.layout.activity_networkerror)
                .build();

        mRootLayout.addView(statusLayoutManager.getRootLayout());

        initViews();
        loadData();

    }

    private void loadData() {

    }

    private void initViews() {

    }

    private void initVariables() {

    }

    private void initToolbar() {

    }

}
