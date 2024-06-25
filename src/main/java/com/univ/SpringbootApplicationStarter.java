package com.univ;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.Arrays;

/**
 * spring boot项目的启动类
 */

// 默认会扫描和注册与SpringbootApplicationStarter同目录及其子目录中的被@Component注解的bean
@SpringBootApplication(scanBasePackages = {"com.univ"})
@EnableScheduling   // 开启spring的定时任务功能
@EnableAsync    // 开启定时任务异步化功能(覆盖默认的任务串行)
@EnableCaching  // 开启缓存
@Slf4j
@MapperScan(basePackages = {"com.univ.mapper"})
@EnableWebSocket
public class SpringbootApplicationStarter {

	public static void main(String[] args) {
		ConfigurableApplicationContext application = SpringApplication
				.run(SpringbootApplicationStarter.class, args);
		Environment env = application.getEnvironment();
		String[] activeProfiles = env.getActiveProfiles();
		String port = env.getProperty("server.port");
		port = (StringUtils.isEmpty(port) ? "8080" : port);
		String path = env.getProperty("server.servlet.context-path");
		log.info("\n----------------------------------------------------------\n\t" +
				"Application springboot is running! Access URLs:\n\t" +
				"active profiles are :" + Arrays.toString(activeProfiles) + "\n\t" +
				"Local: \t\thttp://localhost:" + port + path + "/\n\t" +
				"----------------------------------------------------------");
	}
}
