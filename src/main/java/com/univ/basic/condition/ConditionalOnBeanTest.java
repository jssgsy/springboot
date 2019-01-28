package com.univ.basic.condition;

import org.junit.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author univ
 * @date 2019/1/28 3:55 PM
 * @description 演示 @ConditionalOnBean
 * 注意：
 *  1. @ConditionalOnBean表示的是BeanFactory到目前为止是否有了此类；
 *  2. 指定的方式有多种，如下面的根据class或者bean的名称，且都可以为多个；
 *  3. @ConditionalOnBean会依赖于不同bean的初始化顺序，要小心；
 */
@Configuration
public class ConditionalOnBeanTest {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalOnBeanTest.class);
        A a = applicationContext.getBean("a", A.class);
        System.out.println(a);

        A a1 = applicationContext.getBean("a1", A.class);
        System.out.println(a1);

        A a2 = applicationContext.getBean("a2", A.class);
        System.out.println(a2);
    }

    /**
     * 不用任何前置条件创建a实例
     * @return
     */
    @Bean(name = "a")
    public A getA() {
        return new A();
    }

    /**
     * 只有存在(BeanFactory中)一个类型为A的bean时才会再创建一个
     * @return
     */
    @Bean(name = "a1")
    @ConditionalOnBean(A.class)
    public A getA1() {
        return new A();
    }

    /**
     * 只有存在名称为a1的bean时都会创建类型为A的bean a2
     * @return
     */
    @Bean(name = "a2")
    @ConditionalOnBean(name = "a1")
    public A getA2() {
        return new A();
    }

}

class A {}

class B {}