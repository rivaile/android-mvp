package com.oldnum7.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.oldnum7.R;
import com.oldnum7.base.BaseAppActivity;
import com.oldnum7.data.entity.LoginEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseAppActivity<ILoginContract.View, LoginPresenter> implements ILoginContract.View {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUnBinder(ButterKnife.bind(this));

        getActivityComponent().inject(this);
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mPresenter.login(mEtName.getText().toString(), mEtPwd.getText().toString());
    }

    @Override
    public void loginSuccess(LoginEntity loginEntity) {

    }

    @Override
    public void loginFail() {

    }
}
