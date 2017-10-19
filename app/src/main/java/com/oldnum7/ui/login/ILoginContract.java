package com.oldnum7.ui.login;

import com.oldnum7.androidlib.mvp.persenter.BasePresenter;
import com.oldnum7.androidlib.mvp.view.LceView;
import com.oldnum7.data.entity.LoginEntity;


/**
 * <pre>
 *       author : denglin
 *       time   : 2017/05/31/14:11
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface ILoginContract {

    interface View extends LceView {
        //        void setLoadingIndicator(boolean active);

////        void getUsers(List<T> users);

//        void showLoading();

//        void showError();

        //        void showNetWorkError();
        void loginSuccess(LoginEntity loginEntity);

        void loginFail();
    }

    abstract class Presenter extends BasePresenter<View> {

//        abstract void loadData(boolean forceUpdate);
//
//        abstract void getUsers(int since, int per_page);
//
//        abstract void getUsers();

        protected abstract void login(String userName, String pwd);
    }
}
