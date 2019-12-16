package com.univ.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author univ
 * @date 2019/12/16 10:53 AM
 * @description
 * @ConfigurationProperties: 用类的属性来承接外部配置项(如application.properties)
 */

/**
 * 即可放置在要注入配置属项的类上，也可放在创建UnivDemo实例的@Bean方法上
 */
@Data
@ConfigurationProperties(prefix = "univ")
public class UnivDemo {

    private String name;

    private Integer age;
}
