package com.univ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author univ
 * @date 2019/3/15 8:34 PM
 * @description 跨域设置 利用CorsFilter
 */
@Configuration
public class CorsConfig {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);    // 允许跨域
        corsConfiguration.addAllowedOrigin("*");    //允许任何域名
        corsConfiguration.addAllowedHeader("*");    //允许任何头
        corsConfiguration.addAllowedMethod("*");    //允许任何方法
        return corsConfiguration;
    }

    @Bean
    public CorsFilter getCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); //注册
        return new CorsFilter(source);
    }
}
