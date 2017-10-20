package com.oldnum7.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
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
import butterknife.Unbinder;

public class RegisterFragment extends BaseAppFragment<ILoginContract.View, LoginPresenter> implements IRegisterContract.View {

    public static final String TAG = "RegisterFragment";
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_finish)
    Button mBtnFinish;
    Unbinder unbinder;


    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setUnBinder(ButterKnife.bind(this, view));

        return view;
    }

    @NonNull
    @Override
    public void createPresenter() {
        ActivityComponent component = getActivityComponent();
        Log.e(TAG, "ActivityComponent: "+component );

        if (component != null) {
            component.inject(this);
        }
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

    @OnClick(R.id.btn_finish)
    public void onViewClicked() {
        getBaseActivity().onFragmentDetached(TAG);
    }
}




