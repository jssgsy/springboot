package com.univ.config;

import com.univ.entity.PropertyDemo;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author univ 2022/11/8 11:03
 */
@Configuration
@EnableConfigurationProperties(PropertyDemo.class)
public class PropertyConfig {

}
