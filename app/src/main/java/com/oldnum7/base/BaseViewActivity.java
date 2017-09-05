package com.oldnum7.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.oldnum7.R;
import com.oldnum7.base.status.StatusLayoutManager;
import com.oldnum7.base.mvp.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/05/16:53
 *       desc   : 基础视图的相关封装...
 *       version: 1.0
 * </pre>
 */
public class BaseViewActivity<P extends BasePresenter> extends BaseMvpActivity<P> {

    @Inject
    protected P mPresenter;

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

        setPresenter();
        initViews();
        loadData();
        initEvent();
    }

    protected void setPresenter() {

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
