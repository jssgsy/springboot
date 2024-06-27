package com.univ.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author univ
 * date 2024/6/26
 */
@Component
public class UnivHandShakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 将在websocket连接成功前调用(UnivWebSocketHandler#afterConnectionEstablished)
//        System.out.println("UnivHandShakeInterceptor#beforeHandshake, wsHandler: " + wsHandler);
        return true;
    }

    // 将在websocket连接成功前调用(UnivWebSocketHandler#afterConnectionEstablished)
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//        System.out.println("UnivHandShakeInterceptor#afterHandshake，wsHandler: " + wsHandler);
    }

}
