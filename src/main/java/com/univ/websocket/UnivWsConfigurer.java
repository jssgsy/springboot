package com.univ.websocket;

import com.univ.websocket.ssh.SshWsHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * @author univ
 * date 2024/6/25
 */
@Configuration
public class UnivWsConfigurer implements WebSocketConfigurer {

    @Resource
    private UnivWsHandler univWsHandler;

    @Resource
    private SshWsHandler sshWsHandler;

    @Resource
    private UnivHandShakeInterceptor univHandShakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(univWsHandler, "/websocket/test")
                .setAllowedOrigins("*")
                .addInterceptors(univHandShakeInterceptor);
        registry.addHandler(sshWsHandler, "/websocket/ssh").setAllowedOrigins("*");
        // 这里还能添加拦截器，与处理普通web请求很类似
        // handlerRegistration.addInterceptors().setAllowedOrigins();
    }
}
