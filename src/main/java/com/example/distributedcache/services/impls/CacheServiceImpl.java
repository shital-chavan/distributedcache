package com.example.distributedcache.services.impls;

import com.example.distributedcache.entities.Cache;
import com.example.distributedcache.repositories.CacheRepository;
import com.example.distributedcache.services.CacheService;
import com.example.distributedcache.services.CacheSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

  @Autowired
  private CacheRepository cacheRepository;

  @Autowired
  private CacheSyncService cacheSyncService;

  @Override
  public String getValue(String key) {
    Cache cacheEntry = cacheRepository.findByKey(key);
    String value = cacheEntry != null ? cacheEntry.getValue() : null;
    log.info("for Key: {} found Value: {}", key, value);
    return value;
  }

  @Override
  public Cache setValue(String key, String value) {
    Cache cacheEntry = updateCache(key, value);
    log.info("Send async request to synchronize client cache");
    cacheSyncService.synchronizeCache(key, value);
    return cacheEntry;
  }

  @Override
  public Cache synchronize(String key, String value) {
    log.info("Synchronize the cache");
    return updateCache(key, value);
  }

  @Override
  public void synchronizeAll() {
    log.info("Synchronize the cache for all entries");
    cacheSyncService.synchronizeAll();
  }

  private Cache updateCache(String key, String value) {
    Cache cacheEntry = cacheRepository.findByKey(key);
    if(cacheEntry == null){
      cacheEntry = new Cache(key, value);
      log.info("Saved Key: {} with Value: {}", key, value);
    } else {
      cacheEntry.setValue(value);
      log.info("Updated Key: {} with Value: {}", key, value);
    }
    cacheRepository.save(cacheEntry);
    return cacheEntry;
  }


}
