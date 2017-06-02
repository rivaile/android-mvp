package com.oldnum7.business;

import com.oldnum7.mvp.MvpPresenter;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/05/31/14:21
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface IMainPresenter extends MvpPresenter<IMainView> {
    void getUsers(int since, int per_page);
}
