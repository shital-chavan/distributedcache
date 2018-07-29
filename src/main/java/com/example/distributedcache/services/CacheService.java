package com.example.distributedcache.services;

import com.example.distributedcache.entities.Cache;

public interface CacheService {

  String getValue(String key);

  Cache setValue(String key, String value);

  Cache synchronize(String key, String value);

  void synchronizeAll();
}
