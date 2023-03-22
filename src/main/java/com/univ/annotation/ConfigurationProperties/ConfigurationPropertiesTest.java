package com.univ.annotation.ConfigurationProperties;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author univ 2023/3/22 10:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationPropertiesTest {

	/**
	 * 已经被注入到spring容器中了
	 *
	 * @see PropertyConfig
	 */
	@Resource
	private PropertyDemo propertyDemo;

	@Test
	public void test() {
		System.out.println(propertyDemo);
	}
}
