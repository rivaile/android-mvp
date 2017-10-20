package com.oldnum7.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

import com.oldnum7.R;
import com.oldnum7.base.BaseAppActivity;
import com.oldnum7.data.entity.VersionEntity;
import com.oldnum7.ui.account.Tab3Fragment;
import com.oldnum7.ui.dashborard.Tab2Fragment;
import com.oldnum7.ui.home.Tab1Fragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppActivity<IMainContract.View, MainPresenter> implements IMainContract.View {
    
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private Tab1Fragment mTab1Fragment;
    private Tab2Fragment mTab2Fragment;
    private Tab3Fragment mTab3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));

        initBottomBar();

        getActivityComponent().inject(this);

        mPresenter.updateVersion("1.0.0");

    }

    protected void initBottomBar() {

        mTab1Fragment = new Tab1Fragment();
        mTab2Fragment = new Tab2Fragment();
        mTab3Fragment = new Tab3Fragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.content, mTab1Fragment, mTab1Fragment.getClass().getSimpleName());
        fragmentTransaction.add(R.id.content, mTab2Fragment, mTab2Fragment.getClass().getSimpleName());
        fragmentTransaction.add(R.id.content, mTab3Fragment, mTab3Fragment.getClass().getSimpleName());

        fragmentTransaction.hide(mTab1Fragment);
        fragmentTransaction.hide(mTab2Fragment);
        fragmentTransaction.hide(mTab3Fragment);

        fragmentTransaction.commit();

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (tabId) {
                    case R.id.tab1:
                        fragmentTransaction.show(mTab1Fragment);
                        fragmentTransaction.hide(mTab2Fragment);
                        fragmentTransaction.hide(mTab3Fragment);
                        break;
                    case R.id.tab2:
                        fragmentTransaction.hide(mTab1Fragment);
                        fragmentTransaction.show(mTab2Fragment);
                        fragmentTransaction.hide(mTab3Fragment);
                        break;
                    case R.id.tab3:
                        fragmentTransaction.hide(mTab1Fragment);
                        fragmentTransaction.hide(mTab2Fragment);
                        fragmentTransaction.show(mTab3Fragment);
                        break;
                    default:
                        fragmentTransaction.show(mTab1Fragment);
                        fragmentTransaction.hide(mTab2Fragment);
                        fragmentTransaction.hide(mTab3Fragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });

        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {

            }
        });
    }

    @Override
    public void updateVersion(VersionEntity entity) {

    }

    @Override
    public void showUpdateDialog() {

    }

    @Override
    public void showDownloadDialog() {

    }

    @Override
    public void installApk(String file) {

    }
}
