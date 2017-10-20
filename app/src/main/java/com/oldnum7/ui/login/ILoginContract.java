package com.oldnum7.ui.login;

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

        void loginSuccess(LoginEntity loginEntity);

        void loginFail();

        
    }

    interface Presenter {

        void login(String userName, String pwd);
    }
}
