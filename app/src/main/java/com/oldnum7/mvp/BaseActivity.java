package com.oldnum7.mvp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.oldnum7.R;
import com.oldnum7.status.StatusLayoutManager;

import butterknife.ButterKnife;

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
    protected LinearLayout mRootLayout;

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

        ButterKnife.bind(this);

        initViews();
        loadData();
        initEvent();
    }

    protected void initEvent() {

    }


    protected void loadData() {

    }

    protected void initViews() {

    }

    protected void initVariables() {

    }

    protected void initToolbar() {

    }

    public StatusLayoutManager getStatusLayoutManager() {
        return statusLayoutManager;
    }
}
