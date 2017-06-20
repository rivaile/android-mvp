package com.oldnum7.business;

import android.support.v4.app.Fragment;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/07/14:25
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public abstract class LazyFragment extends Fragment {

    protected boolean isVisible;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }
}
