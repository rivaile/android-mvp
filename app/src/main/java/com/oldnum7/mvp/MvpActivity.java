package com.oldnum7.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * author : denglin
 * time   : 2017/05/31/13:48
 * desc   : An Activity that uses an {@link MvpPresenter} to implement a Model-View-Presenter
 * version: 1.0
 */
public class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity implements MvpView {

    protected P presenter;

    @NonNull
    public P createPresenter() {
        return null;
    }

    @NonNull
    public P getPresenter() {
        return presenter;
    }


    public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }

    @NonNull
    public V getMvpView() {
        return (V) this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (createPresenter() != null) {
            setPresenter(createPresenter());
            getPresenter().attachView(getPresenter().getView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }
}
