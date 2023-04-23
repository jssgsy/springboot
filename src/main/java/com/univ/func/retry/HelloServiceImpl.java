package com.univ.func.retry;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author univ 2023/4/12 11:07
 */
@Service
public class HelloServiceImpl {

//	@Retryable(value = RuntimeException.class, maxAttempts = 5, backoff = @Backoff(5000))

	/**
	 * 当所修饰方法抛出RuntimeException时尝试，默认尝试3次
	 *
	 * 注：总共就执行三次，Retry: count=0
	 */
	@Retryable(value = RuntimeException.class)
	public void sayHello() {
		System.out.println("===sayHello() first line ===");
		throw new IllegalStateException("o o");
	}
}
