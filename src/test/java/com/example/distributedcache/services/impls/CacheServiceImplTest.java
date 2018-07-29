package com.example.distributedcache.services.impls;

import com.example.distributedcache.entities.Cache;
import com.example.distributedcache.repositories.CacheRepository;
import com.example.distributedcache.services.CacheSyncService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CacheServiceImplTest {

    @InjectMocks
    private CacheServiceImpl cacheService;

    @Mock
    private CacheRepository cacheRepository;

    @Mock
    private CacheSyncService cacheSyncService;

    @Test
    public void testGetValue() {

        //build
        String key = "key";
        String value = "value";
        Cache cache = new Cache("key", "value");

        //mock
        Mockito.when(cacheRepository.findByKey(key)).thenReturn(cache);

        //call
        String result = cacheService.getValue(key);

        //assert
        Assert.assertEquals("Expected result", value, result);

    }

}
