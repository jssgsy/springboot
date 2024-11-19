package com.univ.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author univ
 * date 2024/11/19
 */
@Service
@Slf4j
public class UnivSseService {

    /**
     * sse连接一般是个「短连接」，不要想着复用，实际工作中不要这样用
     * 要对sse的超时时间有深刻的认识：不是所谓的空闲时间，而是整个sse连接的存活时间
     */
    private final Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    public SseEmitter connect(String sessionId) throws IOException {
        if (sseEmitterMap.containsKey(sessionId)) {
            log.info("connect 连接已存在");
            return sseEmitterMap.get(sessionId);
        }
        // 对超时时间认识是核心
        // 超时时间：单位是毫秒；
        // 不传跟随所使用的web服务器，tomcat默认是30s；
        // -1：表示永不超时，不推荐
        // 要根据自己的业务特点设置恰当的超时时间
        SseEmitter emitter = new SseEmitter(60000L);
        sseEmitterMap.put(sessionId, emitter);
        emitter.onError(System.out::println);
        emitter.onCompletion(() -> {
            log.info("connect sse连接正常结束了，sessionId：{}", sessionId);
            sseEmitterMap.remove(sessionId);

        });
        emitter.onTimeout(() -> {
            log.info("connect sse连接超时了，sessionId：{}", sessionId);
            sseEmitterMap.remove(sessionId);
        });
        // 注意name
        emitter.send(SseEmitter.event().id("event_id").name("connect").data("sse连接成功"));
        return emitter;
    }

    public void send(String sessionId) {
        if (!sseEmitterMap.containsKey(sessionId)) {
            log.info("send ====非法请求====");
        }
        SseEmitter sseEmitter = sseEmitterMap.get(sessionId);
        // emitter一般在异步线程中发送数据，因为耗时会较长
       new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    // 模拟数据处理
                    Thread.sleep(1000);
                    sseEmitter.send(SseEmitter.event().id("id: " + i).name("msg").data("line: " + i));
                    // sseEmitter.send("line: " + i);
                }
                // 注意这里的使用范式
                sseEmitter.complete();
                sseEmitterMap.remove(sessionId);
            } catch (IOException | InterruptedException e) {
                log.info("send sse send出现异常了" + e);
                // 注意这里的使用范式
                sseEmitter.completeWithError(e);
                sseEmitterMap.remove(sessionId);
            }
        }).start();
       log.info("=======");
    }
}
