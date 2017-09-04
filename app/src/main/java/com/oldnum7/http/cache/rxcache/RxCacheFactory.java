package com.oldnum7.http.cache.rxcache;

import com.oldnum7.base.App;

import java.io.File;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

import static com.oldnum7.Constants.APP_RXCACHE;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/08/16:02
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class RxCacheFactory {
    private static final RxCacheFactory ourInstance = new RxCacheFactory();
    private RxCache mRxCache;

    public static RxCacheFactory getInstance() {
        return ourInstance;
    }

    private RxCacheFactory() {
        createRetrofit();
    }

    private void createRetrofit() {

        File cacheFile = new File(App.getmContext().getCacheDir(), APP_RXCACHE);
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        mRxCache = new RxCache.Builder()
                .persistence(cacheFile, new GsonSpeaker());
    }

    public <T> T createCacheService(Class<T> clazz) {
        return mRxCache.using(clazz);
    }

}
