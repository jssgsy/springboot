package com.univ.starter;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.univ.property.UnivProperty;

/**
 * @author univ
 * @date 2019/12/16 5:23 PM
 * @description 演示使用自定义starter
 */
@Component
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties
public class MyStarterTest {

    /**
     * UnivProperty是引用的starter中的类。
     */
    @Resource
    private UnivProperty univProperty;

    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyStarterTest.class);
        // 注意，此时(使用@Bean创建MyStarterTest实例)才会注入univProperty属性
        MyStarterTest bean = applicationContext.getBean(MyStarterTest.class);
        System.out.println(bean.univProperty);
    }

    @Bean
    public UnivProperty univProperty(){
        return new UnivProperty();
    }

}
