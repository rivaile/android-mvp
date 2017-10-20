package com.oldnum7.ui.login;

import com.oldnum7.data.entity.LoginEntity;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/10/20/11:10
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class UserManager {

    public static UserManager getInstance() {
        return new UserManager();
    }

    private UserManager() {

    }

    public boolean isLogin() {
        return false;
    }

    public String getToken() {
        return "";
    }

    public void updateUserState(LoginEntity loginEntity) {

    }

    public void deleteUserState() {
    }
}
