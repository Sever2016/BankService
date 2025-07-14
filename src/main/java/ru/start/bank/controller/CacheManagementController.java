package ru.start.bank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Контролер очищения кеша")
@RestController
@RequestMapping("/management")
public class CacheManagementController {

    private final CacheManager cacheManager;

    public CacheManagementController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Operation(
            summary = "Очищает кеш приложения"
    )
    @RequestMapping("/clear-cache")
    public void clearCaches(){
        cacheManager.getCacheNames().forEach(name->{
            Cache cache = cacheManager.getCache(name);
            if (cache!=null)cache.clear();
        });
    }
}
