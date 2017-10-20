package com.oldnum7.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.oldnum7.R;
import com.oldnum7.base.BaseAppFragment;
import com.oldnum7.data.entity.LoginEntity;
import com.oldnum7.di.component.ActivityComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends BaseAppFragment<ILoginContract.View, LoginPresenter> implements ILoginContract.View {

    public static final String TAG = "RegisterFragment";

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @BindView(R.id.btn_finish)
    Button mBtnFinish;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
        }
        return view;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_finish)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }


    @Override
    public void loginSuccess(LoginEntity loginEntity) {

    }

    @Override
    public void loginFail() {

    }

    @Override
    public void showRegisterFragment() {

    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(String msg, boolean pullToRefresh) {

    }
}
