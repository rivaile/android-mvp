package com.oldnum7.androidlib.mvp.delegate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;


/**
 * A delegate for Fragments to attach them to MVP.
 * The following methods must be invoked from the corresponding Fragments lifecycle methods:
 *
 * @author denglin
 * Created at 2016/8/2 18:19
*/

public interface IFragmentDelegate {

    /**
     * Must be called from {@link Fragment#onCreate(Bundle)}
     * @param saved
     */
    void onCreate(Bundle saved);

    /**
     * Must be called from {@link Fragment#onViewCreated(View, Bundle)}
     * @param view
     * @param savedInstanceState
     */
    void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    /**
     * Must be called from {@link Fragment#onActivityCreated(Bundle)}
     * @param savedInstanceState
     */
    void onActivityCreated(Bundle savedInstanceState);


    /**
     * Must be called from {@link Fragment#onResume()}
     */
    void onResume();

    /**
     * Must be called from {@link Fragment#onAttach(Activity)}
     * @param activity
     */
    void onAttach(Context activity);

    /**
     * Must be called from {@link Fragment#onStart()}
     */
    void onStart();

    /**
     * Must be called from {@link Fragment#onPause()}
     */
    void onPause();

    /**
     * Must be called from {@link Fragment#onStop()}
     */
    void onStop();

    /**
     * Must be called from {@link Fragment#onSaveInstanceState(Bundle)}
     * @param outState
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * Must be called from {@link Fragment#onDestroyView()}
     */
    void onDestroyView();

    /**
     * Must be called from {@link Fragment#onDetach()}
     */
    void onDetach();

    /**
     * Must be called from {@link Fragment#onDestroy()}
     */
    void onDestroy();

}
