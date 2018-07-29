package com.example.distributedcache.services.impls;

import com.example.distributedcache.services.OnStartupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OnStartupServiceImpl implements OnStartupService {

    @Value("${client.syncAll.url}")
    private String clientSyncAllUrl;

    @Override
    public void synchronizeAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(clientSyncAllUrl, Void.class);
            log.info("Invoked sync all");
        } catch (RestClientException e) {
            log.error("Ignore the error when invoking sync all, may be this is the first server");
        }
    }
}
