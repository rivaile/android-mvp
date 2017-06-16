package com.oldnum7.business;

import dagger.Component;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/16/14:36
 *       desc   :
 *       version: 1.0
 * </pre>
 */


@Component(modules = MainPresenterModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
