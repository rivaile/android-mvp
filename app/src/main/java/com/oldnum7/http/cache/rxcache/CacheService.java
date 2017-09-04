package com.oldnum7.http.cache.rxcache;

import com.oldnum7.data.entity.UserEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/08/15:41
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface CacheService {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<UserEntity>>> getUsersCache(Observable<List<UserEntity>> users, EvictProvider evictDynamicKey);
}
