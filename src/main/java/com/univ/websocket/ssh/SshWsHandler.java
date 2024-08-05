package com.univ.websocket.ssh;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author univ
 * date 2024/7/25
 */
@Configuration
@Slf4j
public class SshWsHandler extends AbstractWebSocketHandler {

    static String username = "root";
    static String password = "";
    static String host = "121.41.101.19";
    static int port = 22;

    Map<String, Session> sessionMap = new HashMap<>();

    Map<String, ChannelShell> channelMap = new HashMap<>();

    Map<String, PrintStream> printStreamMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("建立webSocket连接了, sessionId:{}", webSocketSession.getId());
        Session session = new JSch().getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        log.info("通过ssh正常连接到远程服务器了, webSocketSession:{}, userInfo:{}", webSocketSession.getId(), JSONObject.toJSONString(session.getUserInfo()));
        sessionMap.put(webSocketSession.getId(), session);

        ChannelShell channel = (ChannelShell) session.openChannel("shell");
        channel.setPty(true);
        // ssh的输入
        printStreamMap.put(webSocketSession.getId(), new PrintStream(channel.getOutputStream(), true));

        // 开启线程进行消费 ssh的输出
        new Thread(() -> {
            try {
                consumeSshOutput(channel, channel.getInputStream(), webSocketSession);
            } catch (IOException e) {
                log.error("获取ssh channel关联的输入流出现异常, webSocketSessionId:{}", webSocketSession.getId(), e);
                throw new RuntimeException(e);
            }
        }).start();

        channel.connect();
        log.info("成功获取ssh连接通道, webSocketSession:{}", webSocketSession.getId());
        channelMap.put(webSocketSession.getId(), channel);

    }

    private static void consumeSshOutput(Channel channel, InputStream in, WebSocketSession webSocketSession) {
        try {
            byte[] tmp = new byte[1024];
            StringBuilder builder = new StringBuilder();
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    builder.append((new String(tmp, 0, i)));
                }
                // 将此次读取到的数据输出
                if (StringUtils.isNotEmpty(builder.toString())) {
                    webSocketSession.sendMessage(new TextMessage(builder));
                    builder.setLength(0);
                }

                // 这里在等外面某人关闭，否则这里会一直阻塞
                if (channel.isClosed()) {
                    log.info("ssh channel被关闭了， webSocketSessionId:{}", webSocketSession.getId());
                    in.close();
                    break;
                }
            }
        } catch (Exception ex) {
            log.error("消费ssh输入流发生异常，webSocketSessionId:{}", webSocketSession.getId(), ex);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("收到消息了, sessionId: {}, message: {}", webSocketSession.getId(), payload);
        PrintStream printStream = printStreamMap.get(webSocketSession.getId());
        printStream.println(message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable exception) throws Exception {
        log.info("连接异常了, webSocketSession:{}, exception:{}", webSocketSession.getId(), exception.getMessage());
        closeSshSessionResource(webSocketSession.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) throws Exception {
        log.info("websocket连接关闭了, sessionId:{}, code:{}, reason:{}", webSocketSession.getId(), status.getCode(), status.getReason());
        closeSshSessionResource(webSocketSession.getId());
    }

    public void closeSshSessionResource(String webSocketSessionId) {
        log.info("websocket准确关闭SSH连接资源, webSocketSessionId:{}", webSocketSessionId);
        Session session = sessionMap.get(webSocketSessionId);
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        sessionMap.remove(webSocketSessionId);

        ChannelShell channelShell = channelMap.get(webSocketSessionId);
        if (channelShell != null && channelShell.isConnected()) {
            channelShell.disconnect();
        }
        channelMap.remove(webSocketSessionId);
        PrintStream printStream = printStreamMap.remove(webSocketSessionId);
        if (null != printStream) {
            printStream.close();
        }
    }

}
