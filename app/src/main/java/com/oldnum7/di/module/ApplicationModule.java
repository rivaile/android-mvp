package com.oldnum7.di.module;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/08/17:42
 *       desc   :
 *       version: 1.0
 * </pre>
 */
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }
    
    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    public Map<String, Object> provideGlobalVariable() {
        return new ArrayMap();
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
