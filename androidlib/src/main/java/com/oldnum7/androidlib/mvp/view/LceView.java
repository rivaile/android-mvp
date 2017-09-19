package com.oldnum7.androidlib.mvp.view;

import android.support.annotation.UiThread;

/**
 * Loading-Content-Error (LCE)
 * 
 * @author denglin
 * Created at 2017/9/2 16:26
*/

public interface LceView extends BaseView{

    /**
     * Display a loading view while loading data in background.
     * The loading view must have the id = R.id.loadingView
     * @param pullToRefresh rue, if pull-to-refresh has been invoked loading.
     */
    @UiThread
    void showLoading(boolean pullToRefresh);

    /**
     * Show the content view.
     * The content view must have the id = R.id.contentView.
     */
    @UiThread
    void showContent();

    /**
     * Show the error view.
     * The error view must be a TextView with the id = R.id.errorView
     * @param e The Throwable that has caused this error
     * @param pullToRefresh true, if the exception was thrown during pull-to-refresh, otherwise
     * false.
     */
    @UiThread
    void showError(String msg, boolean pullToRefresh);

    /**
     * The data that should be displayed with showContent().
     * @param data
     */
//    @UiThread
//    void setData(M data);

    /**
     * Load the data. Typically invokes the presenter method to load the desired data.
     * Should not be called from presenter to prevent infinity loops. The method is declared
     * in the views interface to add support for view state easily.
     * @param pullToRefresh true, if triggered by a pull to refresh. Otherwise false.
     */
//    @UiThread
//    void loadData(boolean pullToRefresh);
}