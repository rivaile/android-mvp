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
package com.oldnum7.http.cache.file;


import io.reactivex.Observable;

/**
 * An interface representing a user Cache.
 */
public interface ICache {
    /**
     * Gets an {@link Observable} which will emit a {@link T}.
     *
     * @param key The user id to retrieve data.
     */

//    Observable<Object> get(String key);
    <T> Observable<T> get(String key, Class<T> clazz);

    /**
     * Puts and element into the cache.
     *
     * @param value Element to insert in the cache.
     */
    <T> void put(String key, T value);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @param key The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(String key);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();

    /**
     * Evict the key of the cache.
     * @param key
     */
    void remove(String key);
}
