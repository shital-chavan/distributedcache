package com.example.distributedcache.controllers;

import com.example.distributedcache.exceptions.NotFoundException;
import com.example.distributedcache.services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    public String getValue(@PathVariable("key") String key) throws NotFoundException {
        String value = cacheService.getValue(key);
        if (value == null) {
            throw new NotFoundException();
        }
        return value;
    }

    @RequestMapping(value = "/set/{key}", method = RequestMethod.POST)
    public void setValue(@PathVariable("key") String key, @RequestBody String value) {
        cacheService.setValue(key, value);
    }

    @RequestMapping(value = "/synchronize/{key}", method = RequestMethod.POST)
    public String synchronize(@PathVariable("key") String key, @RequestBody String value) {
        return cacheService.synchronize(key, value).getKey();
    }

    @RequestMapping(value = "/synchronize", method = RequestMethod.GET)
    public void synchronizeAll() {
        cacheService.synchronizeAll();
    }

}
