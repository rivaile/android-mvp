package com.oldnum7.ui.main;


import com.oldnum7.androidlib.mvp.persenter.BasePresenter;
import com.oldnum7.androidlib.mvp.view.LceView;
import com.oldnum7.data.entity.VersionEntity;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/05/31/14:11
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface IMainContract {

    interface View extends LceView {

        void updateVersion(VersionEntity entity);

        void showUpdateDialog();

        void showDownloadDialog();

        void installApk(String file);
    }

    abstract class Presenter extends BasePresenter<View> {

        abstract void updateVersion(String clientVersion);

    }
}
