package com.univ.starter;

import com.univ.property.UnivProperty;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author univ
 * @date 2019/12/16 5:23 PM
 * @description 演示使用自定义starter
 */
@Component
@PropertySource("classpath:application.yml")
@EnableConfigurationProperties
public class MyStarterTest {

    /**
     * UnivProperty是引用的starter中的类。
     * 注：因为starter中引入的UnivProperty实例名是univProperty，所以下面@Bean使用了"univProperty2"，否则会有两个一样名字的实例，冲突
     */
    @Resource(name = "univProperty2")
    private UnivProperty univProperty;

    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyStarterTest.class);
        // 注意，此时(使用@Bean创建MyStarterTest实例)才会注入univProperty属性
        MyStarterTest bean = applicationContext.getBean(MyStarterTest.class);
        System.out.println(bean.univProperty);
    }

    @Bean("univProperty2")
    public UnivProperty univProperty(){
        return new UnivProperty();
    }

}
