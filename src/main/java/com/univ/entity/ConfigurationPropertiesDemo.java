package com.univ.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author univ
 * @date 2019/1/7 9:13 PM
 * @description
 */
@Component  // 既然要能被扫描到，那就必不可少@Component修饰
@ConfigurationProperties(prefix = "univ")   // prefix指定的值就是配置项的前缀
public class ConfigurationPropertiesDemo {

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
    private String address1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }
}
