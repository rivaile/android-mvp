package com.oldnum7.di.component;

import com.oldnum7.ActivityScoped;
import com.oldnum7.business.UserActivity;
import com.oldnum7.di.module.MainPresenterModule;

import dagger.Component;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/16/14:36
 *       desc   :
 *       version: 1.0
 * </pre>
 */

@ActivityScoped
@Component(dependencies = AppComponent.class, modules = MainPresenterModule.class)
public interface MainComponent {
    //只能是指定的Activity....
    void inject(UserActivity activity);
}
