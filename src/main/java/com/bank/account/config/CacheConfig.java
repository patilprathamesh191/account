package com.bank.account.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {


    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager("propertyCache");
        manager.setCaffeine(
                Caffeine.newBuilder()
                        .maximumSize(10000)
                        .recordStats() // optional for metrics
        );
        return manager;
    }

}
