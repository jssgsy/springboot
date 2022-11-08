package com.univ.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 引入外部配置文件(@PropertySource)
 * 所谓外部配置文件：即相对于application-${xxx}等spring天生认识的配置文件
 *
 * 重点：必须与@Configuration一起使用，即不能通过外部@EnableConfigurationProperties的方式引入
 *
 * @author univ
 */
@Data
@ConfigurationProperties(prefix = "my")
@PropertySource(value = {"classpath:my.properties"})
@Configuration
public class MyProperty {
    private String name;
    private Integer age;
}
