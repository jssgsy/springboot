package com.univ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * spring boot项目的启动类
 */

// 默认会扫描和注册与SpringbootApplicationStarter同目录及其子目录中的被@Component注解的bean
@SpringBootApplication(scanBasePackages = {"com.univ"})
@EnableScheduling   // 开启spring的定时任务功能
@EnableAsync    // 开启定时任务异步化功能(覆盖默认的任务串行)
public class SpringbootApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplicationStarter.class, args);
	}
}
