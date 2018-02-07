package com.oldnum7.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.FragmentUtils;
import com.oldnum7.R;
import com.oldnum7.base.BaseAppActivity;
import com.oldnum7.data.entity.LoginEntity;
import com.oldnum7.ui.Fragment1;

import butterknife.ButterKnife;

/**
 * 需要体现模块化的思想...一个模块一个activity...一个功能一个Fragment
 * antivity 支持LCV view  fragment 暂不需要支持...
 */
public class LoginActivity extends BaseAppActivity<ILoginContract.View, LoginPresenter> implements ILoginContract.View {

//    @BindView(R.id.et_name)
//    EditText mEtName;
//    @BindView(R.id.et_pwd)
//    EditText mEtPwd;
//    @BindView(R.id.btn_login)
//    Button mBtnLogin;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        FragmentUtils.addFragment(getSupportFragmentManager(),  Fragment1.newInstance(),R.id.fl_login,false,true);
        setUnBinder(ButterKnife.bind(this));
    }

    @NonNull
    @Override
    public void createPresenter() {
        getActivityComponent().inject(this);
    }

//    @OnClick({R.id.btn_register, R.id.btn_login})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_login:
////                mPresenter.login(mEtName.getText().toString(), mEtPwd.getText().toString());
//                break;
//            case R.id.btn_register:
//
//                showFragment(R.id.fl_login, Fragment1.newInstance(), "Fragment1");
////                FragmentUtils.addFragment(getSupportFragmentManager(),  Fragment1.newInstance(),R.id.fl_login,false,true);
////                FragmentUtils.addFragment(getSupportFragmentManager(), Tab1Fragment.newInstance(),R.id.fl_login);
//                break;
//        }
//    }

    @Override
    public void loginSuccess(LoginEntity loginEntity) {

    }

    @Override
    public void loginFail() {

    }
}
