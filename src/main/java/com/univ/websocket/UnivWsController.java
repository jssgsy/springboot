package com.univ.websocket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用来测试给websocket客户端主动发送消息
 * @author univ
 * date 2024/6/24
 */
@RestController
@RequestMapping("/websocket")
public class UnivWsController {

    @Resource
    private UnivWsHandler univWsHandler;

    @RequestMapping("/send")
    public void pushToWeb(String message) {
        univWsHandler.sendMsg(message);
    }

}
