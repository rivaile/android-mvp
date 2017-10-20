package com.oldnum7.ui.main;

import com.oldnum7.androidlib.http.callback.DialogHttpObserver;
import com.oldnum7.androidlib.mvp.persenter.BasePresenter;
import com.oldnum7.data.entity.VersionEntity;
import com.oldnum7.domain.usecase.UpdateCase;

import javax.inject.Inject;

/**
 * author : denglin
 * time   : 2017/05/31/13:54
 * desc   :
 * version: 1.0
 */
public class MainPresenter extends BasePresenter<IMainContract.View> implements IMainContract.Presenter {

    private final UpdateCase mUpdateCase;

    @Inject
    MainPresenter(UpdateCase updateCase) {
        mUpdateCase = updateCase;
    }

    @Override
    public void updateVersion(String clientVersion) {
        mUpdateCase.execute(new DialogHttpObserver<VersionEntity>() {
            @Override
            public void onNext(VersionEntity entity) {
                super.onNext(entity);
            }
        }, UpdateCase.Params.params(clientVersion));
    }
}
