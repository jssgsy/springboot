package com.univ.annotation.ConfigurationProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 通过@EnableConfigurationProperties引入配置项类基本是标准做法，特别是能在spring与三方框架集成时看到
 * 	三方框架有自己的配置项，通过ConfigurationProperties指定前缀收集起来集中管理
 *
 * @author univ 2022/11/8 11:03
 */
@Configuration
@EnableConfigurationProperties(value = {PropertyDemo.class})
public class PropertyConfig {

	// 其它代码
}
