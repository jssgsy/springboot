package com.univ.func.retry;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author univ 2023/4/12 11:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableRetry
public class SpringRetryTest {

	@Resource
	private HelloServiceImpl helloService;

	@Test
	public void test() {
		helloService.sayHello();
	}

}
