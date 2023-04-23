package com.univ.func.retry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author univ 2023/4/12 11:03
 */
@SpringBootApplication(scanBasePackages = {"com.univ"})
@EnableRetry
public class SpringRetryTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringRetryTest.class);

		HelloServiceImpl helloService = applicationContext.getBean(HelloServiceImpl.class);
		helloService.sayHello();
		applicationContext.close();
	}

}
