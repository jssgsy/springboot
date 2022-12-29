package com.univ.springboot.property_source;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;

/**
 * @author univ 2022/12/29 10:54
 */
public class PropertySourceTest {

	@Test
	public void fn1() {
		MutablePropertySources propertySources = new MutablePropertySources();
		Map<String, Object> map = new HashMap<>();
		map.put("aaa", "aaxx");
		map.put("bbb", "${aaa}");
		map.put("ccc", "ccxx");
		PropertySource p1 = new MapPropertySource("map", map);
		propertySources.addLast(p1);
		PropertySourcesPropertyResolver propertyResolver = new PropertySourcesPropertyResolver(propertySources);
		System.out.println(propertyResolver.getProperty("bbb"));// aaxx
		System.out.println(propertyResolver.resolvePlaceholders("bbb"));// 没带占位符，直接返回，bbb
		System.out.println(propertyResolver.resolvePlaceholders("${aaa}"));// aaxx
	}

}
