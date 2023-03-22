package com.univ.annotation.ConfigurationProperties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 一般的属性配置类不要用@Component修饰，保证其pojo的纯粹性，可在外围需要时使用@EnableConfigurationProperties注入容器；
 *
 * @author univ
 * @date 2019/1/7 9:13 PM
 * @description
 */
//@Component
@ConfigurationProperties(prefix = "demo")
@Data
public class PropertyDemo {

    /**
     * 会到配置文件中找到univ.name的项
     */
    private String name;

    /**
     * 会到配置文件中找到univ.age的项
     */
    private Integer age;

    /**
     * 注意：在@ConfigurationProperties注解的类中@Value将不起作用
     * Note that contrary to @Value, SpEL expressions are not evaluated since property values are externalized.
     */
    @Value("univ.address")
    private String address;

}
