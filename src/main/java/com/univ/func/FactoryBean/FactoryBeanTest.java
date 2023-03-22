package com.univ.func.FactoryBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author univ 2023/3/22 10:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryBeanTest implements ApplicationContextAware {
	ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void test() {
		// 通过FactoryBean的名字获取到的实际上是Univ实例
		Univ univ = applicationContext.getBean("univFactoryBean", Univ.class);
		System.out.println(univ);

		// 加&表明要获取FactoryBean本身；
		UnivFactoryBean univFactoryBean = applicationContext.getBean("&univFactoryBean", UnivFactoryBean.class);
		System.out.println(univFactoryBean);
	}
}

/**
 * 仅仅只是一个非常普通的类，将由UnivFactoryBean导入给spring容器
 */
class Univ {
}

@Component
class UnivFactoryBean implements FactoryBean<Univ> {

	@Override
	public Univ getObject() throws Exception {
		return new Univ();
	}

	@Override
	public Class<?> getObjectType() {
		return Univ.class;
	}
}