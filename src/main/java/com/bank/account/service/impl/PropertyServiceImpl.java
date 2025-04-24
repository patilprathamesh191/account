package com.bank.account.service.impl;

import com.bank.account.entity.Properties;
import com.bank.account.repository.PropertiesRepo;
import com.bank.account.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertiesRepo propertiesRepo;
    private CacheManager cacheManager;

    PropertyServiceImpl(PropertiesRepo propertiesRepo, CacheManager cacheManager)
    {
        this.propertiesRepo=propertiesRepo;
        this.cacheManager=cacheManager;
    }

    @Override
    @Cacheable("propertyCache")
    public List<Properties> getProperties() {
        log.info("getProperties");
        return propertiesRepo.findAll();
    }

    @Override
    public Object getProperties(String key) {
        return getProperty(key);
    }

    private String getPropertyFromCache(String key) {
        Cache cache = cacheManager.getCache("propertyCache");// Get your cache
        if (cache != null) {
            List<Properties> properties = cache.get(SimpleKey.EMPTY, List.class);  // Get the cached list

            if (properties != null) {
                // Find the property with the specific key
                for (Properties property : properties) {
                    if (property.getAppKey().equals(key)) {
                        log.info("key found in cache->{}",key);
                        return property.getAppValue();  // Return value if found
                    }
                }
            }
        }
        return null;  // Return null if key is not found in the cache
    }

    // Fallback to DB if the cache doesn't contain the key
    public String getProperty(String key) {
        String cachedValue = getPropertyFromCache(key);
        if (cachedValue != null) {
            return cachedValue;  // Return from cache if present
        }
        log.info("key not found->{}",key);
        // If not found in cache, fallback to DB
        return propertiesRepo.findByAppKey(key).getAppValue();
    }
}
