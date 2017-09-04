/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oldnum7.http.cache;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/02/17:28
 *       version: 1.0
 * </pre>
 */

public enum CacheMode {
    /** 按照HTTP协议的默认缓存规则，例如有304响应头时缓存 */
    DEFAULT,

    /** 不使用缓存 */
    NO_CACHE,

    /** 请求网络失败后，读取缓存 */
    REQUEST_FAILED_READ_CACHE,

    /** 如果缓存不存在才请求网络，否则使用缓存 */
    IF_NONE_CACHE_REQUEST,

    /** 先使用缓存，不管是否存在，仍然请求网络 */
    FIRST_CACHE_THEN_REQUEST,
}
