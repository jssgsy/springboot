package com.univ.websocket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用来测试给websocket客户端主动ntudd消息
 * @author univ
 * date 2024/6/24
 */
@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    @Resource
    private UnivWebSocketHandler univWebSocketHandler;

    @RequestMapping("/send")
    public void pushToWeb(String message) {
        univWebSocketHandler.sendMsg(message);
    }

}
