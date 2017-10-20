package com.oldnum7.ui.login;

import com.oldnum7.androidlib.mvp.view.MvpView;
import com.oldnum7.data.entity.LoginEntity;


/**
 * <pre>
 *       author : denglin
 *       time   : 2017/05/31/14:11
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface IRegisterContract {

    interface View extends MvpView {

        void loginSuccess(LoginEntity loginEntity);

        void loginFail();

        void showRegisterFragment();
        
    }

    interface Presenter {

        void login(String userName, String pwd);
    }
}
