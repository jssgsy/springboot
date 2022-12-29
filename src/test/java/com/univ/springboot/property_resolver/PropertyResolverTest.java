package com.univ.springboot.property_resolver;

import java.util.Properties;
import org.junit.Test;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * @author univ 2022/12/15 15:02
 */
public class PropertyResolverTest {


	@Test
	public void fn1() {
		PropertyPlaceholderHelper placeholderHelper = new PropertyPlaceholderHelper("${",
				"}", ":", true);
		Properties properties = new Properties();
		properties.put("k1", "v1");
		properties.put("k2", "v2");
		properties.put("k3", "v3");

		// 到properties中找key为k2(由${k2}解析得到其中的变量k2)的值；
		String r1 = placeholderHelper.replacePlaceholders("${k2}", properties::getProperty);
		System.out.println(r1);

	}





}
