package com.univ.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * @author univ
 * date 2024/6/25
 */
@Configuration
public class UnivWebSocketConfigurer implements WebSocketConfigurer {

    @Resource
    private UnivWebSocketHandler univWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(univWebSocketHandler, "/websocket/test")
                .setAllowedOrigins("*");
        // 这里还能添加拦截器，与处理普通web请求很类似
        // handlerRegistration.addInterceptors().setAllowedOrigins();
    }
}