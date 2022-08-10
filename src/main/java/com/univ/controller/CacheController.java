package com.univ.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univ.service.CacheService;

/**
 * @author univ
 * 2022/8/10 5:22 下午
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private CacheService cacheService;

    @RequestMapping("/cache")
    public String cacheTest() {
        return cacheService.throughCache();
    }
}
