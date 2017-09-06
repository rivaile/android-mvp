package com.oldnum7.mvp.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oldnum7.R;
import com.oldnum7.mvp.persenter.BasePresenter;
import com.oldnum7.mvp.status.StatusLayoutManager;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/05/16:53
 *       desc   : 基础视图的相关封装... Loading-Content-Error (LCE) Activity.
 *       version: 1.0
 * </pre>
 */
public class BaseLceActivity<V extends LceView, P extends BasePresenter<V>> extends BaseMvpActivity<V, P> implements LceView {

    protected StatusLayoutManager mStatusLayoutManager;

    protected android.support.v7.widget.Toolbar toolbar;
    private ImageView mIvBack;
    private TextView mTvRight;
    protected TextView mTvTitle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        //toolbar view
        initToolbar();

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LinearLayout rootView = (LinearLayout) findViewById(R.id.root_layout);
        if (rootView == null) {
            return;
        }

        //lce  view
        mStatusLayoutManager = StatusLayoutManager.newBuilder(this).contentView(layoutResID)
                .emptyDataView(R.layout.empty_view)
                .errorView(R.layout.error_view)
                .loadingView(R.layout.loading_view)
                .netWorkErrorView(R.layout.networkerror_view)
                .build();

        rootView.addView(mStatusLayoutManager.getRootLayout(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // TODO: 2017/9/6 错误视图的重新加载...
//        mStatusLayoutManager.getRootLayout().findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onErrorViewClicked();
//            }
//        });
    }

    protected void initToolbar() {

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mIvBack = (ImageView) findViewById(R.id.iv_back);
            mIvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performBackBottonOnClick(v);
                }
            });

            mTvRight = (TextView) findViewById(R.id.tv_right);
            mTvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performRightTextOnClick(v);
                }
            });

            mTvTitle = (TextView) findViewById(R.id.tv_title);
            mTvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performTitleOnClick(v);
                }
            });
        }

        mToolbar = new Toolbar();
    }

    public class Toolbar {

        public void show(@NonNull boolean show) {
            toolbar.setVisibility(show == true ? View.VISIBLE : View.GONE);
        }

        public Toolbar showBackButton(@NonNull boolean show) {
            mIvBack.setVisibility(show == true ? View.VISIBLE : View.GONE);
            return this;
        }

        public Toolbar setBackIcon(@NonNull int resId) {
            mIvBack.setImageResource(resId);
            mIvBack.setVisibility(View.VISIBLE);
            return this;
        }

        public Toolbar setTitle(@NonNull String title) {
            mTvTitle.setText(title);
            return this;
        }

        public Toolbar setTitleColor(@NonNull int color) {
            mTvTitle.setTextColor(getResources().getColor(color));
            return this;
        }

        public Toolbar setRightText(@NonNull String title) {
            mTvRight.setText(title);
            mTvRight.setVisibility(View.VISIBLE);
            return this;
        }

        public Toolbar setBgColor(@NonNull int color) {
            toolbar.setBackgroundColor(color);
            return this;
        }
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        if (!pullToRefresh) {
            mStatusLayoutManager.showLoading();
        }
    }

    @Override
    public void showContent() {
        mStatusLayoutManager.showContent();
    }

    @Override
    public void showError(String msg, boolean pullToRefresh) {
        if (pullToRefresh) {
            showToast(msg);
        } else {
            mStatusLayoutManager.showError();
        }
    }

    // 返回按钮点击事件响应
    protected void performBackBottonOnClick(View view) {
        finish();
    }

    protected void performRightTextOnClick(View v) {
    }

    protected void performTitleOnClick(View v) {
    }

    protected void onErrorViewClicked() {

    }

}
