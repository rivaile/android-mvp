package com.oldnum7.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.oldnum7.R;
import com.oldnum7.status.StatusLayoutManager;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.ButterKnife;

/**
 * <Pre>
 * author : denglin
 * time   : 2017/05/31/13:47
 * desc   :The base activity that every activity you create must extent it.
 * version: 1.0
 * </Pre>
 */
public class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected StatusLayoutManager statusLayoutManager;
    protected LinearLayout mRootLayout;



    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

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
