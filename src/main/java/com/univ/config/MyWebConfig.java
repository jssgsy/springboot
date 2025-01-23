package com.univ.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author univ
 * date 2024/7/3
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/txt/**")
                .addResourceLocations("classpath:/mytxt/"); // 这里必须是一个有效目录，而不能是文件

        registry.addResourceHandler("/txt/b.txt")
                .addResourceLocations("classpath:/mytxt/"); // 这里必须是一个有效目录，而不能是文件

        // 如下配置无效，因为配置的ResourceLocations是一个文件而不是文件
        registry.addResourceHandler("/txt/hello.txt").addResourceLocations("classpath:/mytxt/a.txt");
    }
}
