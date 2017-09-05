package com.oldnum7.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.oldnum7.R;
import com.oldnum7.mvp.BasePresenter;
import com.oldnum7.status.StatusLayoutManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import javax.inject.Inject;

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

    @Inject
    protected P mPresenter;

    protected StatusLayoutManager statusLayoutManager;
    protected LinearLayout mRootLayout;


//    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
//    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
//    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
//    @Override
//    public View onCreateView(String name, Context context, AttributeSet attrs) {
//        View view = null;
//        if (name.equals(LAYOUT_FRAMELAYOUT)) {
//            view = new AutoFrameLayout(context, attrs);
//        }
//
//        if (name.equals(LAYOUT_LINEARLAYOUT)) {
//            view = new AutoLinearLayout(context, attrs);
//        }
//
//        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
//            view = new AutoRelativeLayout(context, attrs);
//        }
//
//        if (view != null) return view;
//
//        return super.onCreateView(name, context, attrs);
//    }

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

    @SuppressWarnings("unchecked")
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    //-------------------------------------------------状态栏管理start--------------------------------------------------------//
    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }

    /** 子类可以重写决定是否使用透明状态栏 */
    protected boolean translucentStatusBar() {
        return false;
    }

    /** 设置状态栏颜色 */
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setStatusBarColor());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }

    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    /** 获取深主题色 */
    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }
    //-------------------------------------------------状态栏管理end--------------------------------------------------------//

}
