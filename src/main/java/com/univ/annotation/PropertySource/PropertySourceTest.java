package com.univ.annotation.PropertySource;

import javax.annotation.Resource;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 便于测试，注意要引入@ComponentScan
 * @author univ 2023/3/22 09:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertySourceTest {

	@Resource
	private Environment environment;

	/**
	 * 测试@PropertySource的本身含义
	 */
	@Test
	public void test1() {
		// 此时已经外部配置文件my.properties了，因此可以直接获取其中的配置项
		System.out.println(environment.getProperty("my.name"));
		System.out.println(environment.getProperty("my.age"));
	}

	/**
	 * 测试@PropertySource结合@ConfigurationProperties一起使用：通过@PropertySource引入了外部配置文件后，再通过@ConfigurationProperties进行读取
	 */
	@Test
	public void test2() {
		System.out.println(myConfiguration2);
	}

	@Autowired
	private MyConfiguration2 myConfiguration2;

}

/**
 * 这是@PropertySource的标准用法，仅仅只是用来引入外部配置文件到Environment(PropertySources)中
 */
@PropertySource("classpath:my.properties")
@Configuration
class MyConfiguration {
	// need nothing
}

/**
 * 结合@ConfigurationProperties使用，即通过@PropertySource引入了外部配置文件后，再通过@ConfigurationProperties进行读取
 */
@PropertySource("classpath:my.properties")
@Configuration
@ConfigurationProperties(prefix = "my")
@Data
class MyConfiguration2 {
	private String name;
	private Integer age;
}
