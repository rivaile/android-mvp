package com.oldnum7.http.cache;

import android.text.TextUtils;
import android.util.LruCache;


import io.reactivex.Observable;


public class MemoryManager implements ICache {
    private LruCache<String, Object> mLruCache;
    private static MemoryManager sInstance;

    private MemoryManager() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache(cacheSize);
    }

    public static MemoryManager getInstance() {
        if (sInstance == null) {
            synchronized (MemoryManager.class) {
                if (sInstance == null) {
                    sInstance = new MemoryManager();
                }
            }
        }
        return sInstance;
    }

    @Override
    public synchronized void put(String key, Object value) {
        if (TextUtils.isEmpty(key)) return;

        if (mLruCache.get(key) != null) {
            mLruCache.remove(key);
        }
        mLruCache.put(key, value);
    }

    @Override
    public boolean isCached(String key) {
        return mLruCache.get(key) != null;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {
        mLruCache.evictAll();
    }

    @Override
    public <T> Observable<T> get(String key, Class<T> clazz) {
        try {
            return Observable.just((T) mLruCache.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(String key) {
        if (mLruCache.get(key) != null) {
            mLruCache.remove(key);
        }
    }
}
