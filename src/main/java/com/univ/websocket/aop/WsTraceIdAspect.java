package com.univ.websocket.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

/**
 * 给WebSocketHandler添加tracdId功能
 * @author univ
 * date 2024/6/27
 */
@Aspect
@Component
public class WsTraceIdAspect {

    @Pointcut("execution(* com.univ.websocket.UnivWsHandler.afterConnectionEstablished(org.springframework.web.socket.WebSocketSession))")
    public void afterConnectionEstablished() {}

    @Pointcut("execution(* com.univ.websocket.UnivWsHandler.handleMessage(org.springframework.web.socket.WebSocketSession, *))")
    public void handleMessage() {}

    @Pointcut("execution(* com.univ.websocket.UnivWsHandler.handleTransportError(org.springframework.web.socket.WebSocketSession, *))")
    public void handleTransportError() {}

    @Pointcut("execution(* com.univ.websocket.UnivWsHandler.afterConnectionClosed(org.springframework.web.socket.WebSocketSession, *))")
    public void afterConnectionClosed() {}

    @Around(value = "afterConnectionEstablished()")
    public Object aroundAfterConnectionEstablished(ProceedingJoinPoint joinPoint) {
        WebSocketSession session = (WebSocketSession) joinPoint.getArgs()[0];
        String traceId = UUID.randomUUID().toString();
        session.getAttributes().put("traceId", traceId);
        MDC.put("traceId", traceId);
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Around(value = "handleMessage()")
    public Object aroundHandleMessage(ProceedingJoinPoint joinPoint) {
        WebSocketSession session = (WebSocketSession) joinPoint.getArgs()[0];
        String traceId = (String) session.getAttributes().get("traceId");
        MDC.put("traceId", traceId);
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Around(value = "handleTransportError()")
    public Object aroundHandleTransportError(ProceedingJoinPoint joinPoint) {
        WebSocketSession session = (WebSocketSession) joinPoint.getArgs()[0];
        String traceId = (String) session.getAttributes().get("traceId");
        MDC.put("traceId", traceId);
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Around(value = "afterConnectionClosed()")
    public Object aroundAfterConnectionClosed(ProceedingJoinPoint joinPoint) {
        try {
            WebSocketSession session = (WebSocketSession) joinPoint.getArgs()[0];
            String traceId = (String) session.getAttributes().get("traceId");
            // 这里别忘了还要设值
            MDC.put("traceId", traceId);
            Object result = joinPoint.proceed();

            // 要放到最后
            MDC.remove("traceId");
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
