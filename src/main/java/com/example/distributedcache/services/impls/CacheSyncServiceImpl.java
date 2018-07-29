package com.example.distributedcache.services.impls;

import com.example.distributedcache.repositories.CacheRepository;
import com.example.distributedcache.services.CacheSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CacheSyncServiceImpl implements CacheSyncService {

    @Autowired
    private CacheRepository cacheRepository;

    @Value("${client.sync.url}")
    private String clientUrl;

    @Override
    public void synchronizeCache(String key, String value) {
        log.info("Start synchronizing cache");
        try {
            // send the KV pair to other server through HTTP request
            sendToClient(key, value);
        } catch (RestClientException e) {
            log.error("Error in sending key-value pair to other server", e);
        }
        log.info("Done synchronizing cache with Client");
    }

    @Override
    public void synchronizeAll() {
        try {
            // read from cache, update all
            cacheRepository.findAll().forEach(cache->sendToClient(cache.getKey(), cache.getValue()));
        } catch (RestClientException e) {
            log.error("Scheduled sync attempt failed", e);
        }
    }

    private void sendToClient(String key, String value) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(String.format(clientUrl, key), value, String.class);
        log.info(result);
    }

}

