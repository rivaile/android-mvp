package com.oldnum7.base.mvp.delegate;

import android.app.Activity;
import android.os.Bundle;


/**
 * A delegate for Activities to attach them to MVP.
 * The following methods must be invoked from the corresponding Activities lifecycle methods.
 *
 * @author zuoqx
 * Created at 2016/8/2 17:30
*/

public interface IActivityDelegate {

    /**
     * This method must be called from {@link Activity#onCreate(Bundle)}.
     * This method internally creates the presenter and attaches the view to it.
     * @param bundle
     */
    void onCreate(Bundle bundle);

    /**
     * This method must be called from {@link Activity#onPostCreate(Bundle)}
     * @param savedInstanceState
     */
//    void onPostCreate(Bundle savedInstanceState);

    /**
     * This method must be called from {@link Activity#onStart()}
     */
    void onStart();

    /**
     * This method must be called from {@link Activity#onRestart()}
     */
    void onRestart();

    /**
     * This method must be called from {@link Activity#onResume()}
     */
    void onResume();

    /**
     * This method must be called from {@link Activity#onPause()}
     */
    void onPause();

    /**
     * This method must be called from {@link Activity#onStop()}
     */
    void onStop();

    /**
     * This method must be called from {@link Activity#onDestroy()}}.
     * This method internally detaches the view from presenter
     */
    void onDestroy();

    /**
     * This method must be called from {@link Activity#onContentChanged()}
     */
    void onContentChanged();

    /**
     * This method must be called from {@link Activity#onSaveInstanceState(Bundle)}
     * @param outState
     */
    void onSaveInstanceState(Bundle outState);

//    /**
//     * This method must be called from {@link FragmentActivity#onRetainCustomNonConfigurationInstance()}
//     * Don't forget to return the value returned by this delegate method
//     * @return
//     */
//    Object onRetainCustomNonConfigurationInstance();
//
//    /**
//     * @return the value returned from {@link ActivityDelegateCallback#onRetainNonCustomNonConfigurationInstance()}
//     */
//    Object getNonLastCustomNonConfigurationInstance();
}
