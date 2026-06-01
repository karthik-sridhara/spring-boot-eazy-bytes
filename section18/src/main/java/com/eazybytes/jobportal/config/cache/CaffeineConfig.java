package com.eazybytes.jobportal.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {

    @Bean
    public CacheManager cacheManager() {

        CaffeineCache jobCache = new CaffeineCache(
            "jobs",
            Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(5000)
                .build()
        );

        CaffeineCache roleCache = new CaffeineCache(
            "roles",
            Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)
                .maximumSize(20)
                .build()
        );

        CaffeineCache companiesCache = new CaffeineCache(
                "companies",
                Caffeine.newBuilder()
                        .expireAfterWrite(600, TimeUnit.MINUTES)
                        .maximumSize(500)
                        .build()
        );



        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(
            List.of(
                jobCache,
                roleCache,
                companiesCache
            )
        );
        return simpleCacheManager;
    }
}
