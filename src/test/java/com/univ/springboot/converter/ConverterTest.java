package com.univ.springboot.converter;

import org.junit.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * @author univ 2022/12/14 14:29
 */
public class ConverterTest {

	@Test
	public void t1() {
		ConversionService conversionService = new DefaultConversionService();
		Long s1 = conversionService.convert(12345, Long.class);

	}

}



class User {}

class User2 {}
