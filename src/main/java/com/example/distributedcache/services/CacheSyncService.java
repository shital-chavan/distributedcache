package com.example.distributedcache.services;

import org.springframework.scheduling.annotation.Async;

/**
 * Cache synchronize service
 */
public interface CacheSyncService {

    @Async
    void synchronizeCache(String key, String value);

    @Async
    void synchronizeAll();
}
