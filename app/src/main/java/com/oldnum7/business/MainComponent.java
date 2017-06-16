package com.oldnum7.business;

import com.oldnum7.ActivityScoped;
import com.oldnum7.di.component.AppComponent;

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
    void inject(MainActivity activity);
}
