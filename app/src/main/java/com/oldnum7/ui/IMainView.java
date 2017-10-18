package com.oldnum7.ui;

import com.oldnum7.data.entity.T;

import java.util.List;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/05/31/14:20
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface IMainView {

    void getUsers(List<T> users);

    void showLoading();

    void showError();

    void showNetWorkError();

}
