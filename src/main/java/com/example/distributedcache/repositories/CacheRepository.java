package com.example.distributedcache.repositories;

import com.example.distributedcache.entities.Cache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CacheRepository extends JpaRepository<Cache, Integer> {

    Cache findByKey(String key);
}
