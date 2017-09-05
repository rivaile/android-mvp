package com.oldnum7.base.mvp.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.oldnum7.base.mvp.BasePresenter;
import com.oldnum7.base.mvp.BaseView;


/**
 * The MvpDelegate callback that will be called from FragmentDelegate or ViewGroupMvpDelegate.
 * This interface must be implemented by all Fragment or android.view.View. Please note that
 * Activties need a special callback ActivityDelegateCallback.
 *
 * @param <V> The type of MvpView
 * @param <P> The type of MvpPresenter
 * @author zuoqx
 * Created at 2016/8/2 16:42
*/

public interface DelegateCallback<V extends BaseView, P extends BasePresenter<V>> {

    /**
     * Create a presenter instance.
     * @return the created presenter instance.
     */
    @NonNull
    P createPresenter();

    /**
     * Get the presenter. If null is returned, then a internally a new presenter instance gets
     * created by calling createPresenter().
     * @return
     */
    P getPresenter();

    /**
     * Sets the presenter instance.
     * @param presenter
     */
    void setPresenter(P presenter);

    /**
     * Get the MvpView for the presenter.
     * @return The view associated with the presenter.
     */
    V getMvpView();

    /**
     * Indicate whether the retain instance feature is enabled by this view or not.
     * @return true if the view has  enabled retaining, otherwise false.
     * @see #setRetainInstance(boolean)
     */
    boolean isRetainInstance();

    /**
     * Mark this instance as retaining. This means that the feature of a retaining instance is
     * enabled.
     * @param retainingInstance true if retaining instance feature is enabled, otherwise false
     */
//    void setRetainInstance(boolean retainingInstance);

    /**
     * Indicates whether or not the the view will be retained during next screen orientation change.
     * This boolean flag is used for {@link MvpPresenter#detachView(boolean)}
     * as parameter. Usually you should take {@link Activity#isChangingConfigurations()} into
     * account. The difference between {@link #shouldInstanceBeRetained()} and {@link
     * #isRetainInstance()} is that {@link #isRetainInstance()} indicates that retain instance
     * feature is enabled or disabled while {@link #shouldInstanceBeRetained()} indicates if the
     * view is going to be destroyed permanently and hence should no more be retained (i.e. Activity
     * is finishing and not just screen orientation changing)
     * @return true if the instance should be retained, otherwise false.
     */
//    boolean shouldInstanceBeRetained();

}
