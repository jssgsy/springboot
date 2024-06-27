package com.univ.websocket;

import com.univ.func.retry.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实际中直接继承AbstractWebSocketHandler即可
 * @author univ
 * date 2024/6/25
 */
@Component
@Slf4j
public class UnivWsHandler implements WebSocketHandler {

    // 可以直接注入其它spring类
    @Resource
    private HelloServiceImpl helloService;

    private List<WebSocketSession> sessionList = new ArrayList<>();

    // 是原生@OnOpen的封装
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("websocket连接成功了, sessionId：{}", session.getId());
        sessionList.add(session);
    }

    // 是原生@OnMessage的封装
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        helloService.goodMorning();
        if (message instanceof TextMessage) {
            String payload = ((TextMessage) message).getPayload();
            log.info("收到客户端消息了, msg：{}", payload);
            sendMsg(payload);
        }
    }

    // 是原生@OnError的封装
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 省略
        log.info("===handleTransportError===");
    }

    // 是原生@OnClose的封装
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 省略
        log.info("===afterConnectionClosed===");
    }

    // 是否支持分片消息，用于发送大内容消息时
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMsg(String msg) {
        log.info("开始给客户端发送消息：{}", msg);
        for (WebSocketSession webSocketSession : sessionList) {
            if (webSocketSession.isOpen()) {
                try {
                    webSocketSession.sendMessage(new TextMessage(msg));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
