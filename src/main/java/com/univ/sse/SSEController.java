package com.univ.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author univ
 * date 2024/11/18
 */
@RestController
@RequestMapping("/sse")
@Slf4j
public class SSEController {

    @Resource
    private UnivSseService univSseService;

    /**
     * 1. 返回值一定要是SseEmitter，这样就表示建立了长连接
     * 2. produces = MediaType.TEXT_EVENT_STREAM_VALUE：经验证，非必须
     *
     * @param sessionId 业务标识，即针对哪个会话
     */
    @GetMapping(value = "/connect"/*, produces = MediaType.TEXT_EVENT_STREAM_VALUE*/)
    public SseEmitter connect(@RequestParam("sessionId") String sessionId) throws IOException {
        return univSseService.connect(sessionId);
    }

    @GetMapping(value = "/send")
    public void send(@RequestParam("sessionId") String sessionId) {
        univSseService.send(sessionId);
    }
}
