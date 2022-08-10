package com.univ.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.univ.service.CacheService;

/**
 * @author univ
 * 2022/8/10 5:09 下午
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Cacheable(cacheNames = "throughCache")
    @Override
    public String throughCache() {
        System.out.println("第一次会走这里，缓存生效期间走缓存");
        return "ok";
    }
}
