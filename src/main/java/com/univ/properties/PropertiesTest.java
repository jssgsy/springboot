package com.univ.properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author univ
 * @date 2019/12/13 8:55 PM
 * @description 属性文件的读取。讲的是将外部配置文件(如application.properties)中的配置项注入给java类；
 */
@Configuration

/**
 * 注意，因为这里使用的是AnnotationConfigApplicationContext，所以需要显示开启属性自动注入，
 * 如果是以spring boot的方式启动则不用指定，因为默认是开启的。
 *
 * 注：这里还能指定要显示实例的Bean(如@EnableConfigurationProperties(UnivDemo.class)会启动UnivDemo读取配置文件且会实例化其实例并将其存放到BeanFactory中)
 */
@EnableConfigurationProperties

/**
 * 指定配置文件配置。与@EnableConfigurationProperties一样，这里是以AnnotationConfigApplicationContext启动应用的，需要显示加上，否则不生效
 * 一般和@Configuration结合使用。(@Configuration相当于容器，在容器启动时引入配置文件)
 */
@PropertySource("classpath:application.properties")
public class PropertiesTest {

    /**
     * 上面已经使用@EnableConfigurationProperties和@Bean了，已经有了实例对象，
     * 但注意，只是BeanFactory中有此实例，并不会被注入给PropertiesTest！所以其实这里是null。重要！
     */
    @Resource
    private UnivDemo univDemo;

    /**
     * 直接从BeanFactory中获取UnivDemo实例
     */
    @Test
    public void fn1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PropertiesTest.class);
        UnivDemo univDemo = applicationContext.getBean(UnivDemo.class);
        System.out.println(univDemo);
    }

    /**
     * 直接使用这里注入的字段
     */
    @Test
    public void fn2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PropertiesTest.class);
        PropertiesTest propertyTest = applicationContext.getBean("propertiesTest", PropertiesTest.class);
        /**
         * 注意，这里是propertyTest.univDemo,而不是univDemo，
         * PropertiesTest只有一个实例，且在BeanFactory中。这是junit测试的方法！
         */
        System.out.println(propertyTest.univDemo);//UnivDemo(name=liuminglu, age=20)
        System.out.println(univDemo);//null

    }

    @Bean
    public UnivDemo univDemo() {
        return new UnivDemo();
    }

}

