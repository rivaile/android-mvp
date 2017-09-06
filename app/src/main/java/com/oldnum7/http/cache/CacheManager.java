/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oldnum7.http.cache;

import android.content.Context;

import com.oldnum7.http.cache.serializer.Serializer;
import com.oldnum7.http.exception.CacheException;
import com.oldnum7.utils.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * {@link ICache} implementation.
 */
@Singleton
public class CacheManager implements ICache {

    private static final String SETTINGS_FILE_NAME = "com.fernandocejas.android10.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "cache_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context mContext;
    private final File mCacheDir;
    private final Serializer mSerializer;
    private final FileManager mFileManager;
    private final ThreadExecutor mThreadExecutor;

    /**
     * Constructor of the class {@link ICache Impl}.
     *
     * @param context     A
     * @param serializer  {@link Serializer} for object serialization.
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    CacheManager(Context context, Serializer serializer,
                 FileManager fileManager, ThreadExecutor executor) {
        if (context == null || serializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.mContext = context.getApplicationContext();
        this.mCacheDir = this.mContext.getCacheDir();
        this.mSerializer = serializer;
        this.mFileManager = fileManager;
        this.mThreadExecutor = executor;
    }

    @Override
    public <T> Observable<T> get(String key, Class<T> clazz) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                final File userEntityFile = CacheManager.this.buildFile(key);
                final String fileContent = CacheManager.this.mFileManager.readFileContent(userEntityFile);
                final T obj = CacheManager.this.mSerializer.deserialize(fileContent, clazz);

                if (obj != null) {
                    emitter.onNext(obj);
                    emitter.onComplete();
                } else {
                    emitter.onError(new CacheException(key));
                }
            }
        });
    }


    @Override
    public <T> void put(String key, T value) {
        if (value != null) {
            final File userEntityFile = this.buildFile(key);
            if (!isCached(key)) {
                final String str = this.mSerializer.serialize(value, value.getClass());
                this.executeAsynchronously(new CacheWriter(this.mFileManager, userEntityFile, str));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(String key) {
        final File file = this.buildFile(key);
        return this.mFileManager.exists(file);
    }

    @Override
    public void remove(String key) {
        final File file = this.buildFile(key);
        if (file != null) {
            this.mFileManager.delete(file);
        }
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);
        if (expired) {
            this.evictAll();
        }
        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.mFileManager, this.mCacheDir));
    }


    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @param key The id user to build the file.
     * @return A valid file.
     */
    private File buildFile(String key) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.mCacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(key);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.mFileManager.writeToPreferences(this.mContext, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.mFileManager.getFromPreferences(this.mContext, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.mThreadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */

    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
