package ru.start.bank.configuration;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class CacheManagementController {

    private final CacheManager cacheManager;

    public CacheManagementController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @RequestMapping("/clear-cache")
    public void clearCaches(){
        cacheManager.getCacheNames().forEach(name->{
            Cache cache = cacheManager.getCache(name);
            if (cache!=null)cache.clear();
        });
    }
}
