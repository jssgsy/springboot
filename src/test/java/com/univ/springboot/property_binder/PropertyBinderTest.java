package com.univ.springboot.property_binder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.junit.Test;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author univ 2022/12/16 17:29
 */
public class PropertyBinderTest {


	@Test
	public void fn1() throws IOException {
		StandardEnvironment environment = new StandardEnvironment();
		DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		Resource resource = defaultResourceLoader.getResource("classpath:application.properties");
		List<PropertySource<?>> propertySourceList = new PropertiesPropertySourceLoader().load("properties", resource);
		propertySourceList.forEach(propertySource -> environment.getPropertySources().addLast(propertySource));

		UnivData univData = new UnivData();
		Binder binder = Binder.get(environment);
		ResolvableType type = ResolvableType.forClass(UnivData.class);

//		Bindable<?> target = Bindable.of(type).withExistingValue(univData);
		// 这样也可以，底层会调用ResolvableType.forClass(UnivData.class)
//		Bindable<?> target = Bindable.of(UnivData.class).withExistingValue(univData);
		// 还能省略
		Bindable<?> target = Bindable.of(UnivData.class);

		BindResult<?> bindResult = binder.bind("univ", target);
		System.out.println(bindResult.get());

	}

	@Test
	public void fn2() {
		MultiValueMap<String, Integer> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.put("aaa", Arrays.asList(1, 2, 3));
		multiValueMap.add("bbb", 4);
		List<Integer> aaa = multiValueMap.get("bbb");
		System.out.println(aaa);
	}

}

@Data
class UnivData {
	private String name;
	private Integer age;
}

class U2 extends UnivData {}