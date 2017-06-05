package com.oldnum7.business;

import com.oldnum7.data.entity.UserEntity;
import com.oldnum7.mvp.BasePresenter;
import com.oldnum7.mvp.BaseView;

import java.util.List;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/05/31/14:11
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface IMainContract {

    interface View extends BaseView {
        void setLoadingIndicator(boolean active);

        void getUsers(List<UserEntity> users);

        void showLoading();

        void showError();

        void showNetWorkError();
    }

    interface Presenter extends BasePresenter<View> {

        void loadData(boolean forceUpdate);

        void getUsers(int since, int per_page);
    }
}
