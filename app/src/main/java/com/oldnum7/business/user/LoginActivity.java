package com.oldnum7.business.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import com.oldnum7.App;
import com.oldnum7.R;
import com.oldnum7.data.entity.LoginEntity;
import com.oldnum7.di.component.DaggerMainComponent;
import com.oldnum7.mvp.view.BaseLceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseLceActivity<ILoginContract.View, LoginPresenter> implements ILoginContract.View{
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

//    @BindView(R.id.et_pwd)
//    EditText mEtPwd;
//    @BindView(R.id.et_name)
//    EditText mEditName;
//    @BindView(R.id.btn_login)
//    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        ButterKnife.bind(this);
    }


    @NonNull
    @Override
    public LoginPresenter createPresenter() {

        DaggerMainComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
//                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
        return mPresenter;
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
                mPresenter.login(mEtName.getText().toString(),mEtPwd.getText().toString());

    }

    @Override
    public void loginSuccess(LoginEntity loginEntity) {

    }

    @Override
    public void loginFail() {

    }
}
